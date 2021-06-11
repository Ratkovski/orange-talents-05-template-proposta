package br.com.zupacademy.ratkovski.proposta.controller;

import br.com.zupacademy.ratkovski.proposta.dto.BiometriaDto;
import br.com.zupacademy.ratkovski.proposta.modelo.Biometria;
import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.repository.BiometriaRepository;
import br.com.zupacademy.ratkovski.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("cartao/{uuid}/biometrias")
    public ResponseEntity<?> cadastra(@PathVariable("uuid")String uuid, @RequestBody @Valid BiometriaDto request, UriComponentsBuilder builder){
        Optional<Cartao> cartao = cartaoRepository.findByUuid(uuid);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Biometria biometria = request.toModel(cartao.get());
        biometriaRepository.save(biometria);


        URI uri = builder.path("biometria/{uuid}").build(biometria.getUuid());
        return ResponseEntity.created(uri).build();

    }
}
