package edu.cesusc.servico;

import java.util.List;

import edu.cesusc.seguranca.usuario.Usuario;


	public interface ServicoDAO {
		public void mostrar(Servico servico);
		public void Buscar(Usuario usuario);
		public void atualizar(Servico servico);
		public void inserir(Servico servico);
		public void alterar(Servico servico);;
		public void salvar(Servico servico);
		public void excluir(Servico servico);
		public Servico carregar(Integer id_servico);
		public List<Servico> listar();

}
