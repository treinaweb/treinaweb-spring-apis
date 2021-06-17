package br.com.treinaweb.twprojetos.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CargoDTO {

    @NotNull
    @Size(min = 3, max = 40)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
