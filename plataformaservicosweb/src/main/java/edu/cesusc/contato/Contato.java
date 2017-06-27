package edu.cesusc.contato;


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
@Table(name="contato")
public class Contato implements  Serializable {
	private static final long serialVersionUID = 8787285092181384093L;
	@Id
	@GeneratedValue
	@Column(name = "id_contato")
	private Integer id_contato;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "telefone")
	private String telefone;
	
	@ManyToMany
	@JoinTable(name = "contatos_servico", joinColumns = { @JoinColumn( name = "id_contato",
		referencedColumnName = "id_contato")}, inverseJoinColumns = { @JoinColumn(name="id_servico")})
	
	
	
	public Integer getId_contato() {
		return id_contato;
	}
	public void setId_contato(Integer id_contato) {
		this.id_contato = id_contato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
}
