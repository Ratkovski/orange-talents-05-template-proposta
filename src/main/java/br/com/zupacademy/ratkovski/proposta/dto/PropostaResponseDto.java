package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import br.com.zupacademy.ratkovski.proposta.modelo.StatusProposta;

import java.math.BigDecimal;

public class PropostaResponseDto {

    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private StatusProposta status;

    private Cartao cartao;

    public PropostaResponseDto(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.cartao = proposta.getCartao();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }
}


