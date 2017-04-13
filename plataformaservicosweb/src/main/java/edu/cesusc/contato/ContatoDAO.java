package edu.cesusc.contato;

import java.util.List;

public interface ContatoDAO  {

	public void inclui(Contato contato);
	public void alterar(Contato contato);
	public void excluir(Contato contato);
	public List<Contato> listar();
	
	
}
