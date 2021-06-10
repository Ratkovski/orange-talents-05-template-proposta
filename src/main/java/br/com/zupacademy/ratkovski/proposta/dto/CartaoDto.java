package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class CartaoDto {

private String id;
private LocalDateTime emitidoEm;
private String titular;
private BigDecimal limite;
private VencimentoResponseDto vencimento;
private String idProposta;

    public CartaoDto(String id,
                     LocalDateTime emitidoEm,
                     String titular,
                     BigDecimal limite,
                     VencimentoResponseDto vencimento,
                     String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
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

    public VencimentoResponseDto getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
    public Cartao toModel(){
       // Proposta proposta = propostaRepository.findById(idProposta).get();
        return new Cartao(id, emitidoEm,titular,limite,vencimento.getDia());

    }
}
