package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.dto.AnaliseDadosRequestDto;
import br.com.zupacademy.ratkovski.proposta.dto.AnaliseDadosResponseDto;
import br.com.zupacademy.ratkovski.proposta.dto.PropostaDto;
import br.com.zupacademy.ratkovski.proposta.feing.ConsultaDadosSolicitante;
import br.com.zupacademy.ratkovski.proposta.metricas.MetricasProposta;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import br.com.zupacademy.ratkovski.proposta.modelo.StatusProposta;
import br.com.zupacademy.ratkovski.proposta.repository.PropostaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
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

    @Autowired
    private ConsultaDadosSolicitante consultaDadosSolicitante;

    @Autowired
    private MetricasProposta metricasProposta;

    @PostMapping(value = "/propostas")
    @Transactional /*aparentemente com o reposytory não precisa @Transactional mas vou deixar por convenção*/
    ResponseEntity<?> register(@RequestBody @Valid PropostaDto request,
                               UriComponentsBuilder builder) throws JsonProcessingException {

        /* validação para verificar se existe um documento igual no banco antes de salvar*/
        if (propostaRepository.existsByDocumento(request.getdocumento())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta registrada para o solicitante com este documento:  " + request.getdocumento());

        }

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        /*aqui atualiza o status no banco conforme o que o metodo retornar*/
        validaDadosNaApi(proposta);
        propostaRepository.save(proposta);

        /*desta forma esconde o id */
        URI uriLocation = builder.path("propostas/{uuid}").build(proposta.getUuid());
        return ResponseEntity.created(uriLocation).build();
    }

    /*método para verificar o que a api retornou e setar no status o resultado*/

    private void validaDadosNaApi(Proposta proposta)
            throws  JsonProcessingException {
        try {
            consultaDadosSolicitante.analisa(new AnaliseDadosRequestDto(proposta));
            proposta.setStatus(StatusProposta.ELEGIVEL);

        } catch (FeignException ex) {
            //String conteudo = ex.contentUTF8();
            AnaliseDadosResponseDto responseDto = new ObjectMapper().readValue(ex.contentUTF8(), AnaliseDadosResponseDto.class);

            if (ex.status() == HttpStatus.UNPROCESSABLE_ENTITY.value() &&
                    responseDto.getResultadoSolicitacao().equals("COM_RESTRICAO")) {
                metricasProposta.contaPropostaNaoElegivel();
                proposta.setStatus(StatusProposta.NAO_ELEGIVEL);

            }

        }

        // return ResponseEntity.created(uriComponentsBuilder.path("propostas/{id}").buildAndExpand(proposta.getId()).toUri()).body(proposta);

    }
}


