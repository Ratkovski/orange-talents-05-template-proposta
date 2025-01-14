package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.dto.AvisoViagemFeingRequestDto;
import br.com.zupacademy.ratkovski.proposta.dto.AvisoViagemRequestDto;
import br.com.zupacademy.ratkovski.proposta.feing.ApiCartaoFeing;
import br.com.zupacademy.ratkovski.proposta.modelo.AvisoViagem;
import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.repository.AvisoViagemRepository;
import br.com.zupacademy.ratkovski.proposta.repository.CartaoRepository;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ApiCartaoFeing apiCartaoFeing;
    @Autowired
    private Tracer tracer;

    @PostMapping("/cartoes/{uuid}/viagem")
    public ResponseEntity<?> cadaviso(@PathVariable("uuid") String uuid,
                                      @RequestBody @Valid AvisoViagemRequestDto request,
                                      HttpServletRequest http) {


        Span activeSpan = tracer.activeSpan();
        String userEmail = activeSpan.getBaggageItem("user.email");
        activeSpan.setBaggageItem("user.email", userEmail);
        activeSpan.setTag("tag.teste","testando tag");
        activeSpan.log("testando o log");





        Cartao cartao = cartaoRepository.findByUuid(uuid)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Este cartão não existe no sistema"));

      if (cartao.bloqueado()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão está bloqueado no sistema");
        }

        String ipCliente = http.getRemoteAddr();
        if(!StringUtils.hasText(ipCliente)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ip não foi localizado");

        }
        String userAgent = http.getHeader("User-Agent");
        if(!StringUtils.hasText(ipCliente)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Header User-Agent não localizado");

        }

       /* String ipCliente = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");*/
        AvisoViagem aviso = request.toModel(ipCliente,userAgent,cartao);

        AvisoViagemFeingRequestDto requestFeing = new AvisoViagemFeingRequestDto(aviso.getDestino(),aviso.getTerminoViagem());
        try{
            apiCartaoFeing.avisoViagem(cartao.getId(),requestFeing);
            cartao.addAvisoViagem(aviso);
            cartaoRepository.save(cartao);
            return ResponseEntity.ok().body("Aviso de viagem cadastrado com sucesso!");
        } catch (FeignException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi cadastrar o aviso da viagem");

        }



    }
}
