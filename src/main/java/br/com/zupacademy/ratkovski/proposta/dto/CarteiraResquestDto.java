package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import br.com.zupacademy.ratkovski.proposta.modelo.Carteira;
import br.com.zupacademy.ratkovski.proposta.modelo.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraResquestDto {

    @NotBlank
    @Email
    private String email;
    @NotNull
    private String carteira;


    public CarteiraResquestDto(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;

    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao) {
        return  new Carteira(email, TipoCarteira.valueOf(carteira),cartao);
    }
}


