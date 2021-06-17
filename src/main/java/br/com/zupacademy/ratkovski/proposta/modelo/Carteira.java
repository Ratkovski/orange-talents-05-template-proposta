package br.com.zupacademy.ratkovski.proposta.modelo;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();

    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;


    // private LocalDateTime associadaEm = LocalDateTime.now();


    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public Carteira() {
    }

    public Carteira(String email,
                    TipoCarteira carteira,
                    Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public Cartao getCartao() {
        return cartao;
    }
}

