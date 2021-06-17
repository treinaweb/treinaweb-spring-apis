package br.com.treinaweb.twprojetos.api.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.servicos.CargoServico;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControleApi {

    @Autowired
    private CargoServico cargoServico;

    @GetMapping
    public List<Cargo> buscarTodos() {
        return cargoServico.buscarTodos();
    }

}
