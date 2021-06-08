package br.com.zupacademy.ratkovski.proposta.feing;

import br.com.zupacademy.ratkovski.proposta.dto.AnaliseDadosRequestDto;
import br.com.zupacademy.ratkovski.proposta.dto.AnaliseDadosResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*"localhost:9999/api"*/
@FeignClient(name = "ConsultaDadosSolicitante", url = "${api.consultadadossolicitante}")
public interface ConsultaDadosSolicitante {

/*value ="/solicitacao"*/
@RequestMapping(method = RequestMethod.POST,value ="${api.requested}")
AnaliseDadosResponseDto analisa(AnaliseDadosRequestDto request);



}
