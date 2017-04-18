package edu.cesusc.contato;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


public class Contato implements  Serializable {
	@Id
	@GeneratedValue
	private Integer id_contato;
	private String nome;
	private String email;
	private String telefone;
	@org.hibernate.annotations.NaturalId
	
	
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
