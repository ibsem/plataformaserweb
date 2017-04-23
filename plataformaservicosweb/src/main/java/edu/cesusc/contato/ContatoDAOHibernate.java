package edu.cesusc.contato;

import java.util.List;

import org.hibernate.Session;

import edu.cesusc.contato.Contato;



public class ContatoDAOHibernate implements ContatoDAO {	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	public void incluiContato (Contato contato) {
		this.session.save(contato);
	}
	public void alterarContato(Contato contato) {
		this.session.update(contato);
	}
	public void excluirContato(Contato contato){
		this.session.delete(contato);
	}
		public List<Contato> listar() {
			return this.session.createCriteria(Contato.class).list();
	}
		public Contato carregar(Integer id_contato) {
			return (Contato) this.session.get(Contato.class, id_contato);
		}
		@Override
		public void inclui(Contato contato) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void alterar(Contato contato) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void excluir(Contato contato) {
			// TODO Auto-generated method stub
			
		}
	
}
