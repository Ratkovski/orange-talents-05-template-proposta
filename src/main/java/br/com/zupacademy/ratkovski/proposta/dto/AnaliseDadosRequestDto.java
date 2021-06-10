package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.config.validation.CPForCNPJ;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;

import javax.validation.constraints.NotBlank;

public class AnaliseDadosRequestDto {
    @NotBlank
    @CPForCNPJ
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    public AnaliseDadosRequestDto(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getUuid();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
