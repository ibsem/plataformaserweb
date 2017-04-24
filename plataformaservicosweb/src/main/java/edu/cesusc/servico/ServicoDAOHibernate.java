package edu.cesusc.servico;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import edu.cesusc.servico.Servico;

public class ServicoDAOHibernate implements ServicoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public void buscar(String nomeServico){
		String hql = "select servico from Servico s where s.nome = :nomeServico";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("nomeServico", nomeServico);
		return;
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

	@Override
	public void mostrar(Servico servico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserir(Servico servico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Servico servico) {
		// TODO Auto-generated method stub
		
	}

	
}

