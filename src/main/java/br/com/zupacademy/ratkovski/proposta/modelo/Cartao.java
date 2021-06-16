package br.com.zupacademy.ratkovski.proposta.modelo;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Cartao {
    @Id
    /*ignore as partes comentadas pois comecei achando que precisava gravar todos os dados do swagger apresentava depois vi poderia ser apenas alguns mas deixei o que já havia criado caso nescessite add algo a mais pra frente*/
    private String id;
    private String uuid = UUID.randomUUID().toString();

    @NotNull
    private LocalDateTime emitidoEm = LocalDateTime.now();
    @NotBlank
    private String titular;


    /**
    
    @OneToMany(mappedBy = "cartao",fetch = FetchType.EAGER)
    private Set<Carteira> carteiras = new HashSet<Carteira>();

     @OneToMany(mappedBy = "cartao",fetch = FetchType.EAGER)
     private Set<Parcela> parcela = new HashSet<Parcela>();
         */
    private BigDecimal limite;
    /*

     @OneToMany(mappedBy = "cartao",fetch = FetchType.EAGER)
     private Set<Renegociacao> renegociacao  = new HashSet<Renegociacao>();

     @OneToMany(mappedBy = "cartao",fetch = FetchType.EAGER)
     private Set<Vencimento> vencimento = new HashSet<Vencimento>();
           **/

    private int vencimento;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao = StatusCartao.ATIVO;

    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<AvisoViagem> avisoViagem = new ArrayList<>();





  @Deprecated
  public Cartao(){}


    public Cartao(String id,
                  LocalDateTime emitidoEm,
                  String titular,
                  BigDecimal limite,
                  int vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
    }

  

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public int getVencimento() {
        return vencimento;
    }


    public boolean bloqueado(){
        return statusCartao.equals(StatusCartao.BLOQUEADO);
    }



public void bloqueio(String ipClient, String userAgent){
    this.statusCartao = StatusCartao.BLOQUEADO;
    addBloqueio(new Bloqueio(ipClient,userAgent,this));

}

public void addBloqueio(Bloqueio bloqueio){
    bloqueios.add(bloqueio);

}
    public void addAvisoViagem(AvisoViagem aviso){
        this.avisoViagem.add(aviso);

    }


}