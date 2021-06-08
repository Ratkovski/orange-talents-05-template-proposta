package br.com.zupacademy.ratkovski.proposta.feing;

import br.com.zupacademy.ratkovski.proposta.dto.AnaliseDadosRequestDto;
import br.com.zupacademy.ratkovski.proposta.dto.AnaliseDadosResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ConsultaDadosSolicitante", url = "localhost:9999/api")
public interface ConsultaDadosSolicitante {


@RequestMapping(method = RequestMethod.POST,value ="/solicitacao")
AnaliseDadosResponseDto analisa(AnaliseDadosRequestDto request);



}
