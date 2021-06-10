package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;


@Embeddable
public class Vencimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dia;
    private LocalDateTime dataDeCriacao = LocalDateTime.now();


    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Vencimento(){}

    public Vencimento(Integer dia,
                      LocalDateTime dataDeCriacao,
                      Cartao cartao) {
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}


