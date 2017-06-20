package edu.cesusc.categoria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import edu.cesusc.seguranca.util.DAOFactory;
import edu.cesusc.servico.Servico;
import edu.cesusc.categoria.Categoria;
import edu.cesusc.categoria.CategoriaDAO;

public class CategoriaRN {
	
	private CategoriaDAO categoriaDAO;

		public CategoriaRN() {
			this.categoriaDAO = DAOFactory.criarCategoriaDAO();
		}

		public Categoria carregar(Integer codigo) {
			return this.categoriaDAO.carregar(codigo);
		}

		public void salvar(Categoria categoria) {
			Integer codigo = categoria.getId_categoria();
			if (codigo == null || codigo == 0) {
				this.categoriaDAO.salvar(categoria);
			} else {
				this.categoriaDAO.exibir(categoria);
			}
		}

		public void excluir(Categoria servico) {
			this.categoriaDAO.excluir(servico);
		}

		public List<Categoria> listar() {
			return this.categoriaDAO.listar();
		}
		public List<Categoria> buscarCategoria(String parametros) {
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
					  ((FullTextSession) fullTextSession).createFullTextQuery(luceneQuery, Categoria.class);
					  List<Categoria> resultado = hibernateQuery.list();
			return resultado;
		}

		private Session openSession() {
			// TODO Auto-generated method stub
			return null;
		}

}
