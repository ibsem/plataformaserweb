package edu.cesusc.contato;

import java.util.List;

import edu.cesusc.contato.Contato;
import edu.cesusc.servico.Servico;

public interface ContatoDAO  {
	
	public void salvar(Contato contato);
	public void excluir(Contato contato);
	public Contato carregar(Integer id_contato);
	public List<Contato> listar(Servico servico);
}
