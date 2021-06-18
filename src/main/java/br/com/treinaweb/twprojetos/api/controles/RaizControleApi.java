package br.com.treinaweb.twprojetos.api.controles;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.hateoas.RaizModel;

@RestController
@RequestMapping("/api/v1")
public class RaizControleApi {

    @GetMapping
    public RaizModel raiz() {
        RaizModel raizModel = new RaizModel();

        Link cargosLink = linkTo(methodOn(CargoControleApi.class).buscarTodos(null))
            .withRel("cargos")
            .withType("GET");

        raizModel.add(cargosLink);

        return raizModel;
    }

}
