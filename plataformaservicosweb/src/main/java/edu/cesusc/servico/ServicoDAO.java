package edu.cesusc.servico;

<<<<<<< HEAD
public class ServicoDAO {
=======
import java.util.List;

import edu.cesusc.servico.Servico;


	public interface ServicoDAO {
		public void mostrar(Servico servico);
		public void buscar(String nomeServico);
		public void atualizar(Servico servico);
		public void inserir(Servico servico);
		public void alterar(Servico servico);;
		public void salvar(Servico servico);
		public void excluir(Servico servico);
		public Servico carregar(Integer id_servico);
		public List<Servico> listar();
>>>>>>> branch 'master' of https://github.com/ibsem/plataformaservicosweb.git

}
