package br.com.zupacademy.ratkovski.proposta.dto;

import br.com.zupacademy.ratkovski.proposta.modelo.AvisoViagem;
import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequestDto {

    @NotBlank
    private String destino;
    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate terminoViagem;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemRequestDto(String destino) {

        this.destino = destino;
    }


    public void setTerminoViagem(LocalDate terminoViagem) {

        this.terminoViagem = terminoViagem;
    }


    public AvisoViagem toModel(String ipCliente, String userAgent, Cartao cartao) {
        return new AvisoViagem(cartao,destino,terminoViagem,ipCliente,userAgent);
    }
}
