package br.com.zupacademy.ratkovski.proposta.modelo;


import br.com.zupacademy.ratkovski.proposta.config.security.Cripto;

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
  //  @CPForCNPJ
    @Convert(converter = Cripto.class)
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

    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

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

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }



}
