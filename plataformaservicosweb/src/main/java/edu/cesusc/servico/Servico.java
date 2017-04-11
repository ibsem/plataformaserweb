package edu.cesusc.servico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Servico implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id_servico;
	private Integer id_usuario;
	private String nome;
	@org.hibernate.annotations.NaturalId 
	private Integer avaliacao;
	private Date data_avaliacao;
	
	
	
	public Integer getId_servico() {
		return id_servico;
	}
	public void setId_servico(Integer id_servico) {
		this.id_servico = id_servico;
	}
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Date getData_avaliacao() {
		return data_avaliacao;
	}
	public void setData_avaliacao(Date data_avaliacao) {
		this.data_avaliacao = data_avaliacao;
	}
	
}
