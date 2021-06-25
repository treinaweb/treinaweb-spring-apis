package br.com.treinaweb.twprojetos.api.controles;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.docs.CargoControleApiDoc;
import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.api.hateoas.CargoAssembler;
import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.servicos.CargoServico;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControleApi implements CargoControleApiDoc {

    @Autowired
    private CargoServico cargoServico;

    @Autowired
    private CargoAssembler cargoAssembler;

    @Autowired
    private PagedResourcesAssembler<Cargo> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cargo>> buscarTodos(Pageable paginacao) {
        Page<Cargo> cargos = cargoServico.buscarTodos(paginacao);

        return pagedResourcesAssembler.toModel(cargos, cargoAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cargo> buscarPorId(@PathVariable Long id) {
        Cargo cargo = cargoServico.buscarPorId(id);

        return cargoAssembler.toModel(cargo);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Cargo> cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
        Cargo cargo = cargoServico.cadastrar(cargoDTO);

        return cargoAssembler.toModel(cargo);
    }

    @PutMapping("/{id}")
    public EntityModel<Cargo> atualizar(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        Cargo cargo = cargoServico.atualizar(cargoDTO, id);

        return cargoAssembler.toModel(cargo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        cargoServico.excluirPorId(id);

        return ResponseEntity.noContent().build();
    }

}
