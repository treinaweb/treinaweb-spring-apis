package br.com.treinaweb.twprojetos.api.mapeadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.servicos.ClienteServico;
import br.com.treinaweb.twprojetos.servicos.FuncionarioServico;

@Component
public class ProjetoMapeador {

    @Autowired
    private FuncionarioServico funcionarioServico;

    @Autowired
    private ClienteServico clienteServico;

    public Projeto converterParaEntidade(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();

        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setOrcamento(projetoDTO.getOrcamento());
        projeto.setGastos(projetoDTO.getGastos());

        projeto.setCliente(clienteServico.buscarPorId(projetoDTO.getClienteId()));
        projeto.setLider(funcionarioServico.buscarPorId(projetoDTO.getLiderId()));

        return projeto;
    }

}
