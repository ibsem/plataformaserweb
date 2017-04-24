package edu.cesusc.contato;

import java.util.List;

import edu.cesusc.contato.Contato;

public interface ContatoDAO  {

	public void inclui(Contato contato);
	public void alterar(Contato contato);
	public void excluir(Contato contato);
	public Contato carregar(Integer id_contato);
	public List<Contato> listar();
	public void salvar(Contato contato);
	public void exibir(Contato contato);
	
	
}
