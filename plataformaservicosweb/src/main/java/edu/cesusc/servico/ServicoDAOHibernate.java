package edu.cesusc.servico;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;



public class ServicoDAOHibernate implements ServicoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	public void buscar(Servico servico){
		this.session.e
	}

	public void salvar(Servico servico) {
		this.session.save(servico);
	}

	public void atualizar(Servico servico) {
		this.session.update(servico);
	}

	public void excluir(Servico servico) {
		this.session.delete(servico);
	}

	public Servico carregar(Integer id_servico) {
		return (Servico) this.session.get(Servico.class, id_servico);
	}

	public List<Servico> listar() {
		return this.session.createCriteria(Servico.class).list();
	}


	
}



