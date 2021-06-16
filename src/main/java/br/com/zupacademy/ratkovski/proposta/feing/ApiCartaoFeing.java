package br.com.zupacademy.ratkovski.proposta.feing;

import br.com.zupacademy.ratkovski.proposta.dto.BloqueioCartoaFeingRequestDto;
import br.com.zupacademy.ratkovski.proposta.dto.BloqueioCartoaFeingResponseDto;
import br.com.zupacademy.ratkovski.proposta.dto.CartaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AssociaCartao", url = "${api.cartao}")
public interface ApiCartaoFeing {



   @RequestMapping(method = RequestMethod.GET, path = "${api.buscacartao}?idProposta={idProposta}")
   CartaoDto associaCartao(@PathVariable("idProposta") String idProposta) ;

 /*   @GetMapping("${api.buscacartao}?idProposta={idProposta}")
    CartaoDto associaCartao(@RequestParam(name = ("idProposta") String idProposta);

*/

   @RequestMapping(method = RequestMethod.POST, path = "${api.buscacartao}/{id}/bloqueios")
   BloqueioCartoaFeingResponseDto bloqueio(@PathVariable("id") String id, BloqueioCartoaFeingRequestDto request) ;
}