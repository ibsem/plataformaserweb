package edu.cesusc.web;


import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.cesusc.contato.Contato;
import edu.cesusc.contato.ContatoDAO;
import edu.cesusc.contato.ContatoRN;




@ManagedBean(name = "contatoBean")
@RequestScoped
public class ContatoBean {
	private Contato contato = new Contato();
	private List<Contato> lista;
	private String destinoSalvar;
	

	public String salvar() {
		ContatoRN contatoRN = new ContatoRN();
		contatoRN.salvar(this.contato);
		return this.destinoSalvar;
	}
	public String excluir() {
		ContatoRN contatoRN = new ContatoRN();
		contatoRN.excluir(this.contato);
		this.lista = null;
		return null;
	}


	public List<Contato> getLista() {
		if (this.lista == null) {
			ContatoDAO contato = (ContatoDAO) new Contato();
			this.lista = contato.listar();
		}
		return this.lista;
	}
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}


	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

}
