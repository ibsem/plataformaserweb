package edu.cesusc.categoria;

import java.util.List;

import org.hibernate.Session;



import edu.cesusc.categoria.Categoria;



public class CategoriaDAOHibernate implements CategoriaDAO {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void inserir(Categoria categoria) {
		this.session.save(categoria);
		}

	public void salvar(Categoria categoria) {
		this.session.save(categoria);
	}
	@Override
	public void altera(Categoria categoria) {
		this.session.update(categoria);
		
	}

	@Override
	public void excluir(Categoria categoria) {
		this.session.delete(categoria);
		
	}
	public Categoria carregar(Integer id_categoria) {
		return (Categoria) this.session.get(Categoria.class, id_categoria);
	}

	@Override
	public List<Categoria> listar() {
		return this.session.createCriteria(Categoria.class).list();
	
	}

	@Override
	public void exibir(Categoria categoria) {
		// TODO Auto-generated method stub
		
	}
	
}
