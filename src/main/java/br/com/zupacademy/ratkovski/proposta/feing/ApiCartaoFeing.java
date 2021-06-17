package br.com.zupacademy.ratkovski.proposta.feing;

import br.com.zupacademy.ratkovski.proposta.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AssociaCartao", url = "${api.cartao}")
public interface ApiCartaoFeing {


//associa cartao a proposta
   @RequestMapping(method = RequestMethod.GET, path = "${api.buscacartao}?idProposta={idProposta}")
   CartaoDto associaCartao(@PathVariable("idProposta") String idProposta) ;

 /*   @GetMapping("${api.buscacartao}?idProposta={idProposta}")
    CartaoDto associaCartao(@RequestParam(name = ("idProposta") String idProposta);

*/
//bloqueios cartao
   @RequestMapping(method = RequestMethod.POST, path = "${api.buscacartao}/{id}/bloqueios")
   BloqueioCartoaFeingResponseDto bloqueio(@PathVariable("id") String id, BloqueioCartoaFeingRequestDto request) ;

//avisos viagem
   @RequestMapping(method = RequestMethod.POST, path = "${api.buscacartao}/{id}/avisos")
   AvisoViagemFeingResponseDto avisoViagem(@PathVariable("id") String id, AvisoViagemFeingRequestDto request) ;

   //carteiras
   @RequestMapping(method = RequestMethod.POST, path = "${api.buscacartao}/{id}/carteiras")
   CarteiraResultado addcarteira(@PathVariable("id") String id, CarteiraRequestFeingDto request) ;
}


