package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
/*ainda não esta sendo usada*/
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @NotBlank
    private String sistemaResponsavel;
    /*aqui poderia ser um enum?*/
    private Boolean ativo;

    @NotBlank
    @Valid
    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public Bloqueio(){}

    public Bloqueio(LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo, Cartao cartao) {
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.cartao = cartao;
    }
}
