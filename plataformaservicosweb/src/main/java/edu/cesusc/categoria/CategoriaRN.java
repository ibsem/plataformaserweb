package edu.cesusc.categoria;

import java.util.List;

import edu.cesusc.seguranca.util.DAOFactory;
import edu.cesusc.categoria.Categoria;
import edu.cesusc.categoria.CategoriaDAO;

public class CategoriaRN {
	
	private CategoriaDAO categoriaDAO;

		public CategoriaRN() {
			this.categoriaDAO = DAOFactory.criarCategoriaDAO();
		}

		public Categoria carregar(Integer codigo) {
			return this.categoriaDAO.carregar(codigo);
		}

		public void salvar(Categoria categoria) {
			Integer codigo = categoria.getId_categoria();
			if (codigo == null || codigo == 0) {
				this.categoriaDAO.salvar(categoria);
			} else {
				this.categoriaDAO.exibir(categoria);
			}
		}

		public void excluir(Categoria servico) {
			this.categoriaDAO.excluir(servico);
		}

		public List<Categoria> listar() {
			return this.categoriaDAO.listar();
		}

}
