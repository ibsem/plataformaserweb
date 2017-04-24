package edu.cesusc.contato;

import java.util.List;

import edu.cesusc.contato.Contato;
import edu.cesusc.contato.ContatoDAO;
import edu.cesusc.seguranca.util.DAOFactory;

public class ContatoRN {

	private ContatoDAO contatoDAO;

	public ContatoRN() {
		this.contatoDAO = DAOFactory.criarContatoDAO();
	}

	public Contato carregar(Integer codigo) {
		return this.contatoDAO.carregar(codigo);
	}

	public void salvar(Contato contato) {
		Integer codigo = contato.getId_contato();
		if (codigo == null || codigo == 0) {
			this.contatoDAO.salvar(contato);
		} else {
			this.contatoDAO.exibir(contato);
		}
	}

	public void excluir(Contato servico) {
		this.contatoDAO.excluir(servico);
	}

	public List<Contato> listar() {
		return this.contatoDAO.listar();
	}

}
	
