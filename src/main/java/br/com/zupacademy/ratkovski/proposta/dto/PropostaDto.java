package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.config.validation.CPForCNPJ;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaDto {
    @NotBlank
    @CPForCNPJ
    private String document;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    @Positive
    private BigDecimal salary;

    public PropostaDto(String document,
                       String email,
                       String name,
                       String address,
                       BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposta toModel() {
        return new Proposta(document,email,name,address,salary);
    }
}

