package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controles.ClienteControleApi;
import br.com.treinaweb.twprojetos.api.controles.FuncionarioControleApi;
import br.com.treinaweb.twprojetos.api.controles.ProjetoControleApi;
import br.com.treinaweb.twprojetos.entidades.Projeto;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        Long clienteId = resource.getContent().getCliente().getId();
        Long liderId = resource.getContent().getLider().getId();
        Long id = resource.getContent().getId();

        Link liderLink = linkTo(methodOn(FuncionarioControleApi.class).buscarPorId(liderId))
            .withRel("lider")
            .withType("GET");

        Link clienteLink = linkTo(methodOn(ClienteControleApi.class).buscarPorId(clienteId))
            .withRel("cliente")
            .withType("GET");

        Link selfLink = linkTo(methodOn(ProjetoControleApi.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        resource.add(liderLink, clienteLink, selfLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = linkTo(methodOn(ProjetoControleApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }

}
