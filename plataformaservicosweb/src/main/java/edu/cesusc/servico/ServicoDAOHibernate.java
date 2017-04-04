package edu.cesusc.servico;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.cesusc.seguranca.usuario.Usuario;

public class ServicoDAOHibernate implements ServicoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
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

	public Servico carregar(Integer id_Servico) {
		return (Servico) this.session.get(Servico.class, id_Servico);
	}

	public List<Servico> listar() {
		return this.session.createCriteria(Servico.class).list();
	}

}
