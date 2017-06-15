package edu.cesusc.categoria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="categoria")
public class Categoria implements Serializable{
	private static final long serialVersionUID = 8450056169116620498L;
	@Id
	@GeneratedValue
	@Column(name = "id_categoria")
	private Integer id_categoria;
	@Column(name ="nome", updatable = false)
	private String nome;
	@Column(name ="descricao", updatable = false)
	private String descricao;
	
	@ManyToMany
	@JoinTable(name = "categorias_servico", joinColumns = { @JoinColumn( name = "id_categoria",
		referencedColumnName = "id_categoria")}, inverseJoinColumns = { @JoinColumn(name="id_servico")})
	
	
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
