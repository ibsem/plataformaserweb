package edu.cesusc.categoria;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Categoria {
	
	@Id
	@GeneratedValue
	private Integer id_categoria;
	private String nome;
	private String descricao;
	private List servicos;
	
	@org.hibernate.annotations.NaturalId
	
	public Integer getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the servicos
	 */
	public List getServicos() {
		return servicos;
	}
	/**
	 * @param servicos the servicos to set
	 */
	public void setServicos(List servicos) {
		this.servicos = servicos;
	}

	
	
	

}
