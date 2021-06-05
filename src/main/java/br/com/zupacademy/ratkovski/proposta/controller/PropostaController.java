package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.dto.PropostaDto;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import br.com.zupacademy.ratkovski.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping(value = "/propostas")
    @Transactional
    ResponseEntity<?> register(@RequestBody  @Valid PropostaDto request,
                               UriComponentsBuilder uriComponentsBuilder) {
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        URI uriLocation = uriComponentsBuilder.path("propostas/{uuid}").build(proposta.getUuid());
        return ResponseEntity.created(uriLocation).build();
      //  return ResponseEntity.created(uriComponentsBuilder.path("propostas/{id}").buildAndExpand(request.getId()).toUri()).body(proposta);

    }

}
