package br.com.zupacademy.ratkovski.proposta.dto;

import java.time.LocalDate;

public class AvisoViagemFeingRequestDto {
    private String destino;
    private LocalDate validoAte;

    public AvisoViagemFeingRequestDto(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
