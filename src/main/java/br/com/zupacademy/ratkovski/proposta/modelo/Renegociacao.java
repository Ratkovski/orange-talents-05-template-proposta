package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
/*ainda não esta sendo usada*/
public class Renegociacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @NotBlank
    @Valid
    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public Renegociacao(){}

    public Renegociacao(Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao, Cartao cartao) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }
}
