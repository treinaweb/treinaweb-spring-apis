package br.com.treinaweb.twprojetos.api.controles;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.dto.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.hateoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.api.hateoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.servicos.ProjetoServico;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoControleApi {

    @Autowired
    private ProjetoServico projetoServico;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private PagedResourcesAssembler<Projeto> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Projeto>> buscarTodos(Pageable paginacao) {
        Page<Projeto> projetos = projetoServico.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(projetos, projetoAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Projeto> buscarPorId(@PathVariable Long id) {
        Projeto projeto = projetoServico.buscarPorId(id);

        return projetoAssembler.toModel(projeto);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Projeto> cadastrar(@RequestBody @Valid ProjetoDTO projetoDTO) {
        Projeto projeto = projetoServico.cadastrar(projetoDTO);

        return projetoAssembler.toModel(projeto);
    }

    @PutMapping("/{id}")
    public EntityModel<Projeto> atualizar(@RequestBody @Valid ProjetoDTO projetoDTO, @PathVariable Long id) {
        Projeto projeto = projetoServico.atualizar(projetoDTO, id);

        return projetoAssembler.toModel(projeto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        projetoServico.excluirPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/equipe")
    public CollectionModel<EntityModel<Funcionario>> buscarEquipe(@PathVariable Long id) {
        List<Funcionario> equipe = projetoServico.buscarPorId(id).getEquipe();

        return funcionarioAssembler.toCollectionModel(equipe);
    }

    @PatchMapping("/{id}/equipe")
    public CollectionModel<EntityModel<Funcionario>> atualizarEquipe(
        @PathVariable Long id, @RequestBody @Valid EquipeDTO equipeDTO
    ) {
        List<Funcionario> equipe = projetoServico.atualizarEquipe(equipeDTO, id);

        return funcionarioAssembler.toCollectionModel(equipe);
    }

}
