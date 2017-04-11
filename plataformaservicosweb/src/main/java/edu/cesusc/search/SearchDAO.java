package edu.cesusc.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import edu.cesusc.search.Conteudo;
import edu.cesusc.search.ConteudoDTO;
import edu.cesusc.search.ModelMapperUtil;
import edu.cesusc.search.PersistenceUtil;

public class SearchDAO {
// buscador	
	public static SearchDAO dao = null;
	private Logger log = Logger.getLogger(SearchDAO.class);

	public SearchDAO(){
		super();
	}
	
	public static SearchDAO getInstance(){
		if(dao == null){
			dao = new SearchDAO();
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(dao.getSession());
			try {
				fullTextEntityManager.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				fullTextEntityManager.clear();
				fullTextEntityManager.close();
			}
		}
		return dao;
	}
	
	public List search(String term){
		List result = null;
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(getSession());
		try{
			QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Conteudo.class).get();
			Query fullTextQuery = fullTextEntityManager .createFullTextQuery(
					qb.keyword()
						.fuzzy()
							.withThreshold(0.8f)
							.withPrefixLength(1)					  
					  .onFields("titulo", "palavasChave")
					  .matching(term)
					  .createQuery(), Conteudo.class);
			List resultTmp = fullTextQuery.setMaxResults(30).getResultList();
			if(!resultTmp.isEmpty()){
				result = new ArrayList<>();
				for(Object obj : resultTmp){
					result.add(ModelMapperUtil.toDTO(obj, ConteudoDTO.class));
				}
			}
		}catch(Exception e){
			log.error("at search(term)", e);
			log.error("term: " + term);
		}finally{
			fullTextEntityManager.clear();
			fullTextEntityManager.close();
		}
		return result;
	}
	
	protected EntityManager getSession(){
		return PersistenceUtil.getSessionFactory().createEntityManager();
	}
	
	
}
