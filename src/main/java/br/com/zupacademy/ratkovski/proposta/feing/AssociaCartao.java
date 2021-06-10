package br.com.zupacademy.ratkovski.proposta.feing;

import br.com.zupacademy.ratkovski.proposta.dto.CartaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AssociaCartao", url = "${api.associacartao}")
public interface AssociaCartao {



   @RequestMapping(method = RequestMethod.GET, path = "${api.buscacartao}?idProposta={idProposta}")
   CartaoDto associaCartao(@PathVariable("idProposta") String idProposta) ;

 /*   @GetMapping("${api.buscacartao}?idProposta={idProposta}")
    CartaoDto associaCartao(@RequestParam(name = ("idProposta") String idProposta);

*/
}