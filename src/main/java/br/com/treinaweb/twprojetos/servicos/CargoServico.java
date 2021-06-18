package br.com.treinaweb.twprojetos.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.api.mapeadores.CargoMapeador;
import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.excecoes.CargoNaoEncontradoException;
import br.com.treinaweb.twprojetos.excecoes.CargoPossuiFuncionariosException;
import br.com.treinaweb.twprojetos.repositorios.CargoRepositorio;
import br.com.treinaweb.twprojetos.repositorios.FuncionarioRepositorio;

@Service
public class CargoServico {

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;

    @Autowired
    private CargoMapeador cargoMapeador;

    public List<Cargo> buscarTodos() {
        return cargoRepositorio.findAll();
    }

    public Page<Cargo> buscarTodos(Pageable paginacao) {
        return cargoRepositorio.findAll(paginacao);
    }

    public Cargo buscarPorId(Long id) {
        Cargo cargoEncontrado = cargoRepositorio.findById(id)
            .orElseThrow(() -> new CargoNaoEncontradoException(id));

        return cargoEncontrado;
    }

    public Cargo cadastrar(Cargo cargo) {
        return cargoRepositorio.save(cargo);
    }

    public Cargo cadastrar(CargoDTO cargoDTO) {
        Cargo cargo = cargoMapeador.converterParaEntidade(cargoDTO);

        return cargoRepositorio.save(cargo);
    }

    public Cargo atualizar(Cargo cargo, Long id) {
        buscarPorId(id);

        return cargoRepositorio.save(cargo);
    }

    public Cargo atualizar(CargoDTO cargoDTO, Long id) {
        buscarPorId(id);

        Cargo cargo = cargoMapeador.converterParaEntidade(cargoDTO);
        cargo.setId(id);

        return cargoRepositorio.save(cargo);
    }

    public void excluirPorId(Long id) {
        Cargo cargoEncontrado = buscarPorId(id);

        if (funcionarioRepositorio.findByCargo(cargoEncontrado).isEmpty()) {
            cargoRepositorio.delete(cargoEncontrado);
        } else {
            throw new CargoPossuiFuncionariosException(id);
        }

    }

}
