package br.com.zupacademy.ratkovski.proposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioCartoaFeingResponseDto {

    private String resultado;

    @JsonCreator
    public BloqueioCartoaFeingResponseDto(String resultado) {

        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
