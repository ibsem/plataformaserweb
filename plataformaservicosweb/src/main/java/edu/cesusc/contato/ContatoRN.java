package edu.cesusc.contato;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import edu.cesusc.contato.Contato;
import edu.cesusc.contato.ContatoDAO;
import edu.cesusc.seguranca.util.DAOFactory;
import edu.cesusc.servico.Servico;

public class ContatoRN {

	private ContatoDAO contatoDAO;
	
	private static synchronized Session openSession() {
	
	Configuration conf = new Configuration();
    conf.configure();
    ServiceRegistry serviceRegistry = new
     ServiceRegistryBuilder().applySettings
     (conf.getProperties()).getBootstrapServiceRegistry();        
     SessionFactory sessionFactory = 
      conf.buildSessionFactory(serviceRegistry);
     
     Session session = sessionFactory.openSession();
     
     return session;
	}

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
	public List<Contato> buscarContato(String parametros) {
		 Session session = openSession();
		 FullTextSession FullTextSessionfullTextSession = Search.getFullTextSession(session);
		 SharedSessionContract fullTextSession = null;
		 fullTextSession.beginTransaction();
		  String parametroPesquisa = parametros ;
		  QueryBuilder queryBuilder = ((FullTextSession) fullTextSession).getSearchFactory()
		     .buildQueryBuilder().forEntity(Contato.class).get();
		   
		  org.apache.lucene.search.Query luceneQuery = 
		   queryBuilder.keyword().onFields("nome").matching
		    (parametroPesquisa).createQuery();
		  org.hibernate.Query hibernateQuery =
				  ((FullTextSession) fullTextSession).createFullTextQuery(luceneQuery, Contato.class);
				  List<Contato> resultado = hibernateQuery.list();
		return resultado;
	}
}
	
