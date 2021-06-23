package br.com.treinaweb.twprojetos.api.hateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.entidades.Projeto;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        // TODO Auto-generated method stub

    }

}
