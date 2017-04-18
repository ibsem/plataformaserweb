package edu.cesusc.servico;

import java.util.List;
import edu.cesusc.seguranca.util.DAOFactory;;

public class ServicoRN {
	private ServicoDAO servicoDAO;

	public ServicoRN() {
		this.servicoDAO = DAOFactory.criarServicoDAO();
	}

	public Servico carregar(Integer codigo) {
		return this.servicoDAO.carregar(codigo);
	}

	public void salvar(Servico servico) {
		Integer codigo = servico.getId_servico();
		if (codigo == null || codigo == 0) {
			this.servicoDAO.salvar(servico);
		} else {
			this.servicoDAO.atualizar(servico);
		}
	}

	public void excluir(Servico servico) {
		this.servicoDAO.excluir(servico);
	}

	public List<Servico> listar() {
		return this.servicoDAO.listar();
	}
}
