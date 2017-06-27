package edu.cesusc.servico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import edu.cesusc.seguranca.usuario.Usuario;

@Entity
@Table(name = "servico")
public class Servico implements Serializable{
	private static final long serialVersionUID = -2551563805909304215L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_servico")
	private Integer id_servico;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
		
	@Column(name = "nome")
	private String nome;
	@Column(name = "palavraschave")
	private String palavraschave;
	@Column(name = "avaliacao")
	private Integer avaliacao;
	
	@Column(name = "data_avaliacao")
	private Date data_avaliacao;
	
		
	
	public Integer getId_servico() {
		return id_servico;
	}
	public void setId_servico(Integer id_servico) {
		this.id_servico = id_servico;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPalavraschave() {
		return palavraschave;
	}
	public void setPalavraschave(String palavraschave) {
		this.palavraschave = palavraschave;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + ((data_avaliacao == null) ? 0 : data_avaliacao.hashCode());
		result = prime * result + ((id_servico == null) ? 0 : id_servico.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		if (avaliacao == null) {
			if (other.avaliacao != null)
				return false;
		} else if (!avaliacao.equals(other.avaliacao))
			return false;
		if (data_avaliacao == null) {
			if (other.data_avaliacao != null)
				return false;
		} else if (!data_avaliacao.equals(other.data_avaliacao))
			return false;
		if (id_servico == null) {
			if (other.id_servico != null)
				return false;
		} else if (!id_servico.equals(other.id_servico))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
	
}
