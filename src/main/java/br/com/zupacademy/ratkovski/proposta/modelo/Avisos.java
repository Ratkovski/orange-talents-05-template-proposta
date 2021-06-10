package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

//@Entity
/*ainda não esta sendo usada*/
public class Avisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();


    @Future
    private LocalDate validoAte;
    private String destino;

    @NotBlank
    @Valid
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Avisos(){}

    public Avisos(LocalDate validoAte, String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }
}
