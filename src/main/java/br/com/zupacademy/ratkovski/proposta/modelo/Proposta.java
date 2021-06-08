package br.com.zupacademy.ratkovski.proposta.modelo;


import br.com.zupacademy.ratkovski.proposta.config.validation.CPForCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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

    @Enumerated(EnumType.STRING)
   private StatusProposta status;

    @Deprecated
    public Proposta(){}

    public Proposta(String documento,
                   @NotBlank @Email String email,
                   @NotBlank String nome,
                   @NotBlank String endereco,
                   @NotNull @PositiveOrZero BigDecimal salario) {

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;

    }


    public String getUuid() {
        return uuid;
    }



    public String getdocumento() {
        return documento;
    }

    public String getnome() {
        return nome;
    }
    public void setStatus(StatusProposta status) {
        this.status = status;
    }




}
