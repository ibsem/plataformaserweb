package edu.cesusc.servico;

import java.util.List;

import edu.cesusc.seguranca.usuario.Usuario;
import edu.cesusc.seguranca.util.DAOFactory;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ServicoRN {
	private ServicoDAO servicoDAO;

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
	public ServicoRN() {
		this.servicoDAO = DAOFactory.criarServicoDAO();
	}

	public Servico carregar(Integer codigo) {
		return this.servicoDAO.carregar(codigo);
	}

	public void salvar(Servico servico) {
		Integer codigo = servico.getId_servico();
		if (codigo == null || codigo == 0) {
			this.servicoDAO.salvar(servico);
		} else {
			this.servicoDAO.atualizar(servico);
		}
	}

	public void excluir(Servico servico) {
		this.servicoDAO.excluir(servico);
	}

	public List<Servico> listar() {
		return this.servicoDAO.listar(null);
	}

	public List<Servico> buscarServico(String parametros) {
		 Session session = openSession();
		 FullTextSession FullTextSessionfullTextSession = Search.getFullTextSession(session);
		 SharedSessionContract fullTextSession = null;
		 fullTextSession.beginTransaction();
		  String parametroPesquisa = parametros ;
		  QueryBuilder queryBuilder = ((FullTextSession) fullTextSession).getSearchFactory()
		     .buildQueryBuilder().forEntity(Servico.class).get();
		   
		  org.apache.lucene.search.Query luceneQuery = 
		   queryBuilder.keyword().onFields("nome").matching
		    (parametroPesquisa).createQuery();
		  org.hibernate.Query hibernateQuery =
				  ((FullTextSession) fullTextSession).createFullTextQuery(luceneQuery, Servico.class);
				  List<Servico> resultado = hibernateQuery.list();
		return resultado;
	}
	public List<Servico> listar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
