package edu.cesusc.categoria;

import java.util.List;

import edu.cesusc.categoria.Categoria;


public interface CategoriaDAO {

	public void exibir(Categoria categoria);
	public void inserir(Categoria categoria);
	public void altera(Categoria categoria);
	public void salvar(Categoria categoria);
	public void excluir(Categoria categoria);	
	public Categoria carregar(Integer id_categoria);
	public List<Categoria> listar();
	
		
}
