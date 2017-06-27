package edu.cesusc.servico;

import java.util.List;

import edu.cesusc.seguranca.usuario.Usuario;
import edu.cesusc.servico.Servico;


	public interface ServicoDAO {
		public void salvar(Servico servico);
		public void excluir(Servico servico);
		public Servico carregar(Integer servico);
		public List<Servico> listar(Usuario usuario);
		public void atualizar(Servico servico);
}
