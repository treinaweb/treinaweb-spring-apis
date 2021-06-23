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

        Link editarLink = linkTo(methodOn(ProjetoControleApi.class).atualizar(null, id))
            .withSelfRel()
            .withType("PUT");

        Link excluirLink = linkTo(methodOn(ProjetoControleApi.class).excluirPorId(id))
            .withSelfRel()
            .withType("DELETE");

        Link equipeLink = linkTo(methodOn(ProjetoControleApi.class).buscarEquipe(id))
            .withRel("equipe")
            .withType("GET");

        Link atualizarEquipeLink = linkTo(methodOn(ProjetoControleApi.class).atualizarEquipe(id, null))
            .withRel("equipe")
            .withType("PATCH");

        resource.add(liderLink, clienteLink, selfLink, editarLink, excluirLink, equipeLink, atualizarEquipeLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = linkTo(methodOn(ProjetoControleApi.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }

}
