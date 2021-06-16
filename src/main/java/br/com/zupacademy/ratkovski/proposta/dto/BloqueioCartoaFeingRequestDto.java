package br.com.zupacademy.ratkovski.proposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioCartoaFeingRequestDto {
    public String sistemaResponsavel;

    @JsonCreator
    public BloqueioCartoaFeingRequestDto(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;

    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
