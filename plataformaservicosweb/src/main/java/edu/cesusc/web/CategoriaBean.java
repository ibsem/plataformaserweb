package edu.cesusc.web;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import edu.cesusc.categoria.Categoria;
import edu.cesusc.categoria.CategoriaDAO;
import edu.cesusc.categoria.CategoriaRN;



@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean {
	
	
private Categoria categoria = new Categoria();
private List<Categoria> lista;
private String destinoSalvar;

public String salvar() {
	CategoriaRN categoriaRN = new CategoriaRN();
	categoriaRN.salvar(this.categoria);
	return this.destinoSalvar;
}

public String excluir() {
	CategoriaRN categoriaRN = new CategoriaRN();
	categoriaRN.excluir(this.categoria);
	this.lista = null;
	return null;
	
}
public List<Categoria> getLista() {
	if (this.lista == null) {
		CategoriaDAO categoria = (CategoriaDAO) new Categoria();
		this.lista = categoria.listar();
	}
	return this.lista;
}
public Categoria getCategoria() {
	return categoria;
}

public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}


public String getDestinoSalvar() {
	return destinoSalvar;
}

public void setDestinoSalvar(String destinoSalvar) {
	this.destinoSalvar = destinoSalvar;
}


	
}
