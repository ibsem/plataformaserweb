package edu.cesusc.categoria;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Categoria {
	
	@Id
	@GeneratedValue(generator="increment")
	@Column
	private Integer id_categoria;
	@Column
	private String nome;
	@Column
	private String descricao;
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

	
	
	

}
