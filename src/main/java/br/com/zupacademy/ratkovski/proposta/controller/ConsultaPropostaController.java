package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.dto.PropostaResponseDto;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import br.com.zupacademy.ratkovski.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class ConsultaPropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @GetMapping(value = "/propostas/{uuid}")
    public ResponseEntity<PropostaResponseDto> consulta(@PathVariable("uuid")String uuid){

        Optional<Proposta> consultaProposta = propostaRepository.findByUuid(uuid);

        if (consultaProposta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada no sistema");

        }
        PropostaResponseDto proposta = new PropostaResponseDto(consultaProposta.get());
        return ResponseEntity.ok(proposta);

    }


}