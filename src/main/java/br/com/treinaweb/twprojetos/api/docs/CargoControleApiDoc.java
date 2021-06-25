package br.com.treinaweb.twprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.api.excecoes.ApiErro;
import br.com.treinaweb.twprojetos.api.excecoes.ValidacaoApiErro;
import br.com.treinaweb.twprojetos.entidades.Cargo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(description = "Cargo Controller", tags = "Cargos")
public interface CargoControleApiDoc {

    @ApiOperation(value = "Listar todos os cargos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem de cargos realizada com sucesso")
    })
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pagina", dataType = "int", paramType = "query", defaultValue = "1", value = "Número da página que deseja recuperar (1..N)"),
        @ApiImplicitParam(name = "tamanho", dataType = "int", paramType = "query", defaultValue = "2", value = "Número de elementos por página."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Criterio de ordenação no formato: propriedade(,asc|desc).")
    })
    CollectionModel<EntityModel<Cargo>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar cargo por ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo encontrado com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    EntityModel<Cargo> buscarPorId(Long id);

    @ApiOperation(value = "Cadastrar cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cargo cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidacaoApiErro.class)
    })
    EntityModel<Cargo> cadastrar(CargoDTO cargoDTO);

    @ApiOperation(value = "Atualizar cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidacaoApiErro.class),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    EntityModel<Cargo> atualizar(CargoDTO cargoDTO, Long id);

    @ApiOperation(value = "Excluir cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Cargo excluido com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado")
    })
    ResponseEntity<?> excluirPorId(Long id);

}
