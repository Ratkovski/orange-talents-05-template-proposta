package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

//@Entity
/*ainda não esta sendo usada*/
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    @PositiveOrZero
    private Integer quantidade;
    private BigDecimal valor;

    @NotBlank
    @Valid
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela(){}


    public Parcela(Integer quantidade,
                   BigDecimal valor,
                   Cartao cartao) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = cartao;
    }
}
