package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/cartao/{uuid}/bloqueio")
    public ResponseEntity<?> bloquear(@PathVariable String uuid, HttpServletRequest http) {
        Optional<Cartao> cartaoExist = cartaoRepository.findByUuid(uuid);
        if (cartaoExist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este cartão não existe no sistema");


        }
        Cartao cartao = cartaoExist.get();

        if (cartao.bloqueado()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
        }

        String ipCliente = http.getRemoteAddr();
        if(!StringUtils.hasText(ipCliente)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "O ip não foi localizado");

        }
        String userAgent = http.getHeader("User-Agent");
        if(!StringUtils.hasText(ipCliente)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Header User-Agent não localizado");

        }


       /* String ipClient = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");*/
        cartao.bloqueio(ipCliente, userAgent);
        cartaoRepository.save(cartao);
        return ResponseEntity.ok().body("Cartão bloqueado com sucesso!");
    }
}

