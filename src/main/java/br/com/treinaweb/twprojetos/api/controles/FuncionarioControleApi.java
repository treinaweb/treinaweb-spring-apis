package br.com.treinaweb.twprojetos.api.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.hateoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.servicos.FuncionarioServico;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioControleApi {

    @Autowired
    private FuncionarioServico funcionarioServico;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private PagedResourcesAssembler<Funcionario> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Funcionario>> buscarTodos(Pageable paginacao) {
        Page<Funcionario> funcionarios = funcionarioServico.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(funcionarios, funcionarioAssembler);
    }

}
