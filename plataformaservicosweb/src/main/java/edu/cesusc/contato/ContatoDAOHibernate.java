package edu.cesusc.contato;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import edu.cesusc.contato.Contato;
import edu.cesusc.servico.Servico;



public class ContatoDAOHibernate implements ContatoDAO {	
	private Session session;
	public void setSession(Session session) {
		this.session = session;
	}
	public void salvar(Contato contato) {
		this.session.saveOrUpdate(contato);
	}
	public void excluir(Contato contato){
		this.session.delete(contato);
	}
	public Contato carregar(Integer id_contato) {
			return (Contato) this.session.get(Contato.class, id_contato);
		}
	public List<Contato> listar(Servico servico) {
		Criteria criteria = this.session.createCriteria(Contato.class);
		criteria.add(Restrictions.eq("servico", servico));
		return criteria.list();
}
}
