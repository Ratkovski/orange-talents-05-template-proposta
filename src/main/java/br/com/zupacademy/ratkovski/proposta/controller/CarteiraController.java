package br.com.zupacademy.ratkovski.proposta.controller;


import br.com.zupacademy.ratkovski.proposta.dto.CarteiraRequestFeingDto;
import br.com.zupacademy.ratkovski.proposta.dto.CarteiraResquestDto;
import br.com.zupacademy.ratkovski.proposta.feing.ApiCartaoFeing;
import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.modelo.Carteira;
import br.com.zupacademy.ratkovski.proposta.modelo.StatusCartao;
import br.com.zupacademy.ratkovski.proposta.modelo.TipoCarteira;
import br.com.zupacademy.ratkovski.proposta.repository.CartaoRepository;
import br.com.zupacademy.ratkovski.proposta.repository.CarteiraRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CarteiraController {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ApiCartaoFeing apiCartaoFeing;


    @PostMapping("/cartoes/{uuid}/carteiras")
    public ResponseEntity<?> cadcarteira(@PathVariable("uuid") String uuid,
                                         @RequestBody @Valid CarteiraResquestDto request, UriComponentsBuilder builder) {
        Optional<Cartao> cartaoExist = cartaoRepository.findByUuid(uuid);
        if (cartaoExist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este cartão não existe no sistema");

        }
        Cartao cartao = cartaoExist.get();
        /*preciso melhorar isso*/
        if (cartaoExist.equals(TipoCarteira.PAYPAL)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão já esta associado a está carteira");

        }
        if (cartaoExist.equals(TipoCarteira.SAMSUNG_PAY)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão já esta associado a está carteira");

        }
        if (cartaoExist.equals(StatusCartao.BLOQUEADO)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
        }

        Carteira carteira = request.toModel(cartao);
        Optional<Carteira> carteiraExist = carteiraRepository.findByCarteiraAndCartaoId(carteira.getCarteira(), carteira.getCartao().getId());
        if (carteiraExist.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

       CarteiraRequestFeingDto requestFeingDto = new CarteiraRequestFeingDto(cartao.getId(),carteira.getCarteira().toString());
        try{
            apiCartaoFeing.addcarteira(cartao.getId(), requestFeingDto);
            carteiraRepository.save(carteira);
        }catch (FeignException ex){

          //  System.out.println(ex);
            return ResponseEntity.unprocessableEntity().build();
        }
        URI path =  builder.path("/cartoes/{uuid}/carteiras/{id}").build(cartao.getUuid(),carteira.getCartao().getId());
        return  ResponseEntity.created(path).build();

        }

    }


