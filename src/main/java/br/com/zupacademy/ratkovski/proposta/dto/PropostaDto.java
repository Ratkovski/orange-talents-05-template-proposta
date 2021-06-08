package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.config.validation.CPForCNPJ;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaDto {
    @NotBlank
    @CPForCNPJ
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @PositiveOrZero
    private BigDecimal salario;

    public PropostaDto(String documento,
                       String email,
                       String nome,
                       String endereco,
                       BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(documento,email,nome,endereco,salario);
    }


    public String getdocumento() {
        return documento;
    }


}


