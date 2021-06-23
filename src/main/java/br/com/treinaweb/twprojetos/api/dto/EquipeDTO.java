package br.com.treinaweb.twprojetos.api.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquipeDTO {

    @NotNull
    @NotEmpty
    private List<Long> equipe;

    public List<Long> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Long> equipe) {
        this.equipe = equipe;
    }

}
