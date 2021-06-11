package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    @Lob

    private String fingerprint;
    private LocalDateTime associadaEm = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public String getUuid() {
        return uuid;
    }
}
