package br.com.zupacademy.ratkovski.proposta.modelo;


import br.com.zupacademy.ratkovski.proposta.config.validation.CPForCNPJ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
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
    @PositiveOrZero
    private BigDecimal salary;

    @Deprecated
    public Proposta(){}

    public Proposta(String document,
                   @NotBlank @Email String email,
                   @NotBlank String name,
                   @NotBlank String address,
                   @NotNull @PositiveOrZero BigDecimal salary) {

        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;

    }

    public String getUuid() {
        return uuid;
    }


}
