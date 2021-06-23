package br.com.treinaweb.twprojetos.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twprojetos.api.dto.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.mapeadores.ProjetoMapeador;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.excecoes.ProjetoNaoEncontradoException;
import br.com.treinaweb.twprojetos.repositorios.ProjetoRepositorio;

@Service
public class ProjetoServico {

    @Autowired
    private ProjetoRepositorio projetoRepositorio;

    @Autowired
    private FuncionarioServico funcionarioServico;

    @Autowired
    private ProjetoMapeador projetoMapeador;

    public List<Projeto> buscarTodos() {
        return projetoRepositorio.findAll();
    }

    public Page<Projeto> buscarTodos(Pageable paginacao) {
        return projetoRepositorio.findAll(paginacao);
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepositorio.findById(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    public Projeto cadastrar(Projeto projeto) {
        return projetoRepositorio.save(projeto);
    }

    public Projeto cadastrar(ProjetoDTO projetoDTO) {
        Projeto projeto = projetoMapeador.converterParaEntidade(projetoDTO);

        return projetoRepositorio.save(projeto);
    }

    public Projeto atualizar(Projeto projeto, Long id) {
        buscarPorId(id);

        return projetoRepositorio.save(projeto);
    }

    public Projeto atualizar(ProjetoDTO projetoDTO, Long id) {
        buscarPorId(id);

        Projeto projeto = projetoMapeador.converterParaEntidade(projetoDTO);
        projeto.setId(id);

        return projetoRepositorio.save(projeto);
    }

    public List<Funcionario> atualizarEquipe(EquipeDTO equipeDTO, Long id) {
        Projeto projeto = buscarPorId(id);

        List<Funcionario> equipe = new ArrayList<>();

        equipeDTO.getEquipe().forEach(funcionarioId -> {
            Funcionario funcionario = funcionarioServico.buscarPorId(funcionarioId);

            equipe.add(funcionario);
        });

        projeto.setEquipe(equipe);
        projetoRepositorio.save(projeto);

        return equipe;
    }

    public void excluirPorId(Long id) {
        Projeto projetoEncontrado = buscarPorId(id);

        projetoRepositorio.delete(projetoEncontrado);
    }

}
