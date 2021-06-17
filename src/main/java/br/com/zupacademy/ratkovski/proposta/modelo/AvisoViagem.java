package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
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
    @FutureOrPresent
    private LocalDate terminoViagem;
    private LocalDateTime avisadoEm = LocalDateTime.now();
    private String ipCliente;
    private String userAgent;




    @Deprecated
    public AvisoViagem(){}

    public AvisoViagem(Cartao cartao,
                       String destino,
                       LocalDate terminoViagem,
                       String ipCliente,
                       String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.terminoViagem = terminoViagem;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }
}

