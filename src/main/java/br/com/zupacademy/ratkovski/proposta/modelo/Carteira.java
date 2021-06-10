package br.com.zupacademy.ratkovski.proposta.modelo;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
/*ainda não esta sendo usada*/
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();

    @Email
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public Carteira(){}

    public Carteira(String email, LocalDateTime associadaEm, String emissor, Cartao cartao) {
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
        this.cartao = cartao;
    }
}

