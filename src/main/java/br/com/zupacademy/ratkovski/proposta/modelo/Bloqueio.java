package br.com.zupacademy.ratkovski.proposta.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity

public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgent;

    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
 /*   @NotBlank
    private String sistemaResponsavel;
  */


    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public Bloqueio(){}

    public Bloqueio(String ipCliente, String userAgent, Cartao cartao) {
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }


}
