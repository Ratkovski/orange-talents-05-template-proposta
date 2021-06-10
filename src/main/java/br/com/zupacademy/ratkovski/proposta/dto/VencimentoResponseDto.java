package br.com.zupacademy.ratkovski.proposta.dto;

import java.time.LocalDateTime;

public class VencimentoResponseDto {

    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoResponseDto(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
