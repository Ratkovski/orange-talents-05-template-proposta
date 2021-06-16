package br.com.zupacademy.ratkovski.proposta.modelo;

import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne
    private Cartao cartao;
    private String destino;
    @Future
    private LocalDate terminoViagem;
    private LocalDateTime avisadoEm = LocalDateTime.now();
    private String ipCliente;
    




    @Deprecated
    public Avisos(){}

    public Avisos(LocalDate validoAte, String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }
}
