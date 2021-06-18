package br.com.zupacademy.ratkovski.proposta.controller;


import br.com.zupacademy.ratkovski.proposta.dto.CartaoDto;
import br.com.zupacademy.ratkovski.proposta.feing.ApiCartaoFeing;
import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import br.com.zupacademy.ratkovski.proposta.repository.CartaoRepository;
import br.com.zupacademy.ratkovski.proposta.repository.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoAssociaProposta {


    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private ApiCartaoFeing associaCartao;


    /*O LoggerFactory é uma classe de utilitário que produz Loggers para várias APIs de log*/
 Logger log = LoggerFactory.getLogger(CartaoAssociaProposta.class);

/*Define o valor no local */
 //@Scheduled(fixedDelay = 2000)
 /*define o valor na variavel na applications.properties*/
 @Scheduled(fixedDelayString = "${periodo.cartao}" )

    public void associaCartao() {
        List<Proposta> propostas = propostaRepository.findAllElegivel();

        for (Proposta proposta : propostas) {
            try {
                CartaoDto cartaoDto = associaCartao.associaCartao(proposta.getUuid());

                Cartao cartao = cartaoDto.toModel();
                cartaoRepository.save(cartao);
                proposta.setCartao(cartao);
                propostaRepository.save(proposta);

            } catch (FeignException ex) {
                log.warn(ex.getMessage());
                log.error(ex.getMessage());
                /*escreve no console o erro*/
                ex.printStackTrace();


        }
    }
}


}
