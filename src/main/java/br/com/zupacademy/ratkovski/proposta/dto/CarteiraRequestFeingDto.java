package br.com.zupacademy.ratkovski.proposta.dto;

public class CarteiraRequestFeingDto {

    private String email;
    private String carteira;

    public CarteiraRequestFeingDto(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
