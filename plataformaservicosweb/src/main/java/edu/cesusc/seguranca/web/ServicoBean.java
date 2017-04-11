package edu.cesusc.seguranca.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.cesusc.seguranca.usuario.Usuario;
import edu.cesusc.seguranca.usuario.UsuarioRN;
import edu.cesusc.servico.Servico;
import edu.cesusc.servico.ServicoDAO;

@ManagedBean(name = "servicoBean")
@RequestScoped
public class ServicoBean {
	private Servico servico = new Servico();
	private List<Servico> lista;
	private String destinoSalvar;
	

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		return this.destinoSalvar;
	}

	public String excluir() {
		Servico usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}


	public List<Servico> getLista() {
		if (this.lista == null) {
			ServicoDAO servico = (ServicoDAO) new Servico();
			this.lista = servico.listar();
		}
		return this.lista;
	}
	

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}


	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	

}
