package edu.cesusc.contato;

import java.util.List;

import edu.cesusc.contato.Contato;
import edu.cesusc.contato.ContatoDAO;
import edu.cesusc.seguranca.util.DAOFactory;
import edu.cesusc.servico.Servico;

public class ContatoRN {

	private ContatoDAO contatoDAO;

	public ContatoRN() {
		this.contatoDAO = DAOFactory.criarContatoDAO();
	}

	public List<Contato> listar(Servico servico) {
		return this.contatoDAO.listar(servico);
	}
	
	public Contato carregar(Integer codigo) {
		return this.contatoDAO.carregar(codigo);
	}

	public void salvar(Contato contato) {
			this.contatoDAO.salvar(contato);
	}

	public void excluir(Contato contato) {
		this.contatoDAO.excluir(contato);
	}
	
}
	
