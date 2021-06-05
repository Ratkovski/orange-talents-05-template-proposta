package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.dto.PropostaDto;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import br.com.zupacademy.ratkovski.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping(value = "/propostas")
    @Transactional /*aparentemente com o reposytory não precisa @Transactional mas vou deixar por convenção*/
    ResponseEntity<?> register(@RequestBody  @Valid PropostaDto request,
                               UriComponentsBuilder builder) {

        /* validação para verificar se existe um documento igual no banco antes de salvar*/
if(propostaRepository.existsByDocument(request.getDocument())){
    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Já existe uma proposta registrada para o solicitante com este documento:  "+ request.getDocument());

}

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        /*desta forma esconde o id */
       URI uriLocation = builder.path("propostas/{uuid}").build(proposta.getUuid());
      return ResponseEntity.created(uriLocation).build();


       // return ResponseEntity.created(uriComponentsBuilder.path("propostas/{id}").buildAndExpand(proposta.getId()).toUri()).body(proposta);

    }

}
