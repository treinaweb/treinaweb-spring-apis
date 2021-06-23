package br.com.treinaweb.twprojetos.api.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.hateoas.ClienteAssembler;
import br.com.treinaweb.twprojetos.api.hateoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entidades.Cliente;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.servicos.ClienteServico;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteControleApi {

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ClienteAssembler clienteAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cliente>> buscarTodos(Pageable paginacao) {
        Page<Cliente> clientes = clienteServico.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(clientes, clienteAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteServico.buscarPorId(id);

        return clienteAssembler.toModel(cliente);
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> buscarProjetos(@PathVariable Long id) {
        List<Projeto> projetos = clienteServico.buscarPorId(id).getProjetos();

        return projetoAssembler.toCollectionModel(projetos);
    }

}
