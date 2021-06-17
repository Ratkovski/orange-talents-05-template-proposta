package br.com.zupacademy.ratkovski.proposta.dto;

public class CarteiraResultado {
    private String resultado;
    private String id;

    public CarteiraResultado(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
