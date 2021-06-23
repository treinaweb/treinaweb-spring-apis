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

import br.com.treinaweb.twprojetos.api.hateoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.api.hateoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.servicos.FuncionarioServico;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioControleApi {

    @Autowired
    private FuncionarioServico funcionarioServico;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private PagedResourcesAssembler<Funcionario> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Funcionario>> buscarTodos(Pageable paginacao) {
        Page<Funcionario> funcionarios = funcionarioServico.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(funcionarios, funcionarioAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Funcionario> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioServico.buscarPorId(id);

        return funcionarioAssembler.toModel(funcionario);
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> buscarProjetos(@PathVariable Long id) {
        List<Projeto> projetos = funcionarioServico.buscarPorId(id).getProjetos();

        return projetoAssembler.toCollectionModel(projetos);
    }

}
