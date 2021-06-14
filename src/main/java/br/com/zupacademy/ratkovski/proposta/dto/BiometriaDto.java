package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.config.validation.IsBase64;
import br.com.zupacademy.ratkovski.proposta.modelo.Biometria;
import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BiometriaDto {
    @NotBlank
    @IsBase64
   @JsonProperty
    private String fingerprint;

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerprint,cartao);
    }
}
