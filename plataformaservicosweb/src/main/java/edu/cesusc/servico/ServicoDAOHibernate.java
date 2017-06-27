package edu.cesusc.servico;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import edu.cesusc.seguranca.usuario.Usuario;
import edu.cesusc.servico.Servico;


public class ServicoDAOHibernate implements ServicoDAO {

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void excluir(Servico servico) {
		this.session.delete(servico);
	}

	@Override
	public void salvar(Servico servico) {
		this.session.saveOrUpdate(servico);
	}

	@Override
	public Servico carregar(Integer servico) {
		return (Servico) this.session.get(Servico.class, servico);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> listar(Usuario usuario) {
		Criteria criteria = this.session.createCriteria(Servico.class);
		return criteria.list();
	}

	@Override
	public void atualizar(Servico servico) {
		// TODO Auto-generated method stub
		
	}
}

