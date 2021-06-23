package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controles.ClienteControleApi;
import br.com.treinaweb.twprojetos.entidades.Cliente;

@Component
public class ClienteAssembler implements SimpleRepresentationModelAssembler<Cliente> {

    @Override
    public void addLinks(EntityModel<Cliente> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(ClienteControleApi.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        Link projetosLink = linkTo(methodOn(ClienteControleApi.class).buscarProjetos(id))
            .withRel("projetos")
            .withType("GET");

        resource.add(selfLink, projetosLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cliente>> resources) {
        Link selfLink = linkTo(methodOn(ClienteControleApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }

}
