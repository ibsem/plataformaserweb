package edu.cesusc.servico;

public interface ServicoDAO {
	public void carregar (Integer id_Servico);
	public void salvar(Servico servico);
	public void atualizar(Servico servico);
	public void excluir(Servico servico);
	public void listar();
}


