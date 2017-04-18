package edu.cesusc.seguranca.util;
import edu.cesusc.seguranca.usuario.*;
import edu.cesusc.servico.ServicoDAO;
import edu.cesusc.servico.ServicoDAOHibernate;
public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO; 
	}
	public static ServicoDAO criarServicoDAO() {
		ServicoDAOHibernate servicoDAO = new ServicoDAOHibernate();
		servicoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return servicoDAO; 
	}
	
}
