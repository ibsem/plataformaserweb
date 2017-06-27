package edu.cesusc.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import edu.cesusc.servico.Servico;
import edu.cesusc.servico.ServicoDAO;
import edu.cesusc.servico.ServicoRN;
import edu.cesusc.web.util.ContextoUtil;

@ManagedBean(name = "servicoBean")
@RequestScoped
public class ServicoBean {
	private Servico servico = new Servico();
	private List<Servico> lista;
	private String destinoSalvar;
	


	public List<Servico> buscarServico(String parametros) {
		ServicoRN servicoRN = new ServicoRN();
		this.lista = servicoRN.buscarServico(parametros);
		return this.lista;
	}
	
	public String salvar() {
		ServicoRN servicoRN = new ServicoRN();
		servicoRN.salvar(this.servico);
		return this.destinoSalvar;
	}

	public String excluir() {
		ServicoRN servicoRN = new ServicoRN();
		servicoRN.excluir(this.servico);
		this.lista = null;
		return null;
	}

	
	public List<Servico> getLista() { 
		if (this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();

			ServicoRN servicoRN = new ServicoRN();
			this.lista = servicoRN.listar(contextoBean.getUsuarioLogado());
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
