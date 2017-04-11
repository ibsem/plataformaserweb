package edu.cesusc.search;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ConteudoDTO implements Serializable{

	private long id;
	private int idTipo;
	private String titulo;
	private String palavasChave;
	private String linkConteudo;
	private Date dataInicial;
	private Date dataFinal;
	private String funciEditor;
	private String funciRevisor;
	private Date dataAtualizacao;
	private String imagem;
	private boolean visivel;

	//private Categoria categoria;
	//Detail of Categoria
	private long categoriaIdCategoria;
	private String categoriaTitulo;
	private String categoriaIcone;
	
	//Conteudo xml
	private String textoConteudo;

	
	private String link;
	
	public ConteudoDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPalavasChave() {
		return palavasChave;
	}
	public void setPalavasChave(String palavasChave) {
		this.palavasChave = palavasChave;
	}
	public String getLinkConteudo() {
		return linkConteudo;
	}
	public void setLinkConteudo(String linkConteudo) {
		this.linkConteudo = linkConteudo;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getFunciEditor() {
		return funciEditor;
	}
	public void setFunciEditor(String funciEditor) {
		this.funciEditor = funciEditor;
	}
	public String getFunciRevisor() {
		return funciRevisor;
	}
	public void setFunciRevisor(String funciRevisor) {
		this.funciRevisor = funciRevisor;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public long getCategoriaIdCategoria() {
		return categoriaIdCategoria;
	}
	public void setCategoriaIdCategoria(long categoriaIdCategoria) {
		this.categoriaIdCategoria = categoriaIdCategoria;
	}
	public String getCategoriaTitulo() {
		return categoriaTitulo;
	}
	public void setCategoriaTitulo(String categoriaTitulo) {
		this.categoriaTitulo = categoriaTitulo;
	}
	public String getCategoriaIcone() {
		return categoriaIcone;
	}
	public void setCategoriaIcone(String categoriaIcone) {
		this.categoriaIcone = categoriaIcone;
	}
	public String getTextoConteudo() {
		return textoConteudo;
	}
	public void setTextoConteudo(String textoConteudo) {
		this.textoConteudo = textoConteudo;
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
