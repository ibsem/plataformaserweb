package edu.cesusc.search;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


@Entity
@Table (name="site_conteudo_materia")
@Indexed(index= "indexes/conteudos")
public class Conteudo implements Serializable{
 
	public static final int TIPO_ARTIGO = 0;
	public static final int TIPO_ARQUIVO = 1;
	public static final int TIPO_LINK = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int idTipo;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String titulo;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String palavasChave;
	
	private String linkConteudo;
	private Date dataInicial;
	private Date dataFinal;
	private String funciEditor;
	private String funciRevisor;
	private Date dataAtualizacao;
	private String imagem;
	private Boolean visivel;
	private Boolean visivelHome;
	private Boolean visivelPortal;
	private Boolean liberadoHome;
	private Boolean liberadoPortal;
	private String  link;

	
	public Conteudo() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getLinkConteudo() {
		return linkConteudo;
	}
	public void setLinkConteudo(String linkConteudo) {
		this.linkConteudo = linkConteudo;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Boolean getVisivel() {
		return visivel;
	}
	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}
	public String getPalavasChave() {
		return palavasChave;
	}
	public void setPalavasChave(String palavasChave) {
		this.palavasChave = palavasChave;
	}
	public Boolean getVisivelHome() {
		return visivelHome;
	}
	public void setVisivelHome(Boolean visivelHome) {
		this.visivelHome = visivelHome;
	}
	public Boolean getVisivelPortal() {
		return visivelPortal;
	}
	public void setVisivelPortal(Boolean visivelPortal) {
		this.visivelPortal = visivelPortal;
	}
	public Boolean getLiberadoHome() {
		return liberadoHome;
	}
	public void setLiberadoHome(Boolean liberadoHome) {
		this.liberadoHome = liberadoHome;
	}
	public Boolean getLiberadoPortal() {
		return liberadoPortal;
	}
	public void setLiberadoPortal(Boolean liberadoPortal) {
		this.liberadoPortal = liberadoPortal;
	}
	
	public String getDataInicialStr() {
		try{
			return new SimpleDateFormat("dd/MM/yyyy").format(this.dataInicial);
		}catch(Exception e){
			return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		}
	}
	public void setDataInicialStr(String dataInicial) {
		try{
			this.dataInicial = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
		}catch(Exception e){
			this.dataInicial = new Date();
		}
	}
	public String getDataFinalStr() {
		try{
			return new SimpleDateFormat("dd/MM/yyyy").format(this.dataFinal);
		}catch(Exception e){
			return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		}
	}
	public void setDataFinalStr(String dataFinal) {
		try{
			this.dataFinal = new SimpleDateFormat("dd/MM/yyyy").parse(dataFinal);
		}catch(Exception e){
			this.dataFinal = new Date();
		}
	}

	public String getLink(){
		switch (idTipo) {
			case TIPO_ARQUIVO:
					return "/conteudo/arquivos/" + getLinkConteudo();
			case TIPO_ARTIGO:
					return "ExibirMateria.do?idMateria=" + getId();
			case TIPO_LINK:
					return getLinkConteudo();
			default:
				return "";
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Conteudo other = (Conteudo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conteudo [id=" + id + ", idTipo=" + idTipo + ", titulo="
				+ titulo + ", palavasChave=" + palavasChave + ", linkConteudo="
				+ linkConteudo + ", dataInicial=" + dataInicial
				+ ", dataFinal=" + dataFinal + ", funciEditor=" + funciEditor
				+ ", funciRevisor=" + funciRevisor + ", dataAtualizacao="
				+ dataAtualizacao + ", imagem=" + imagem + ", visivel="
				+ visivel + ", visivelHome=" + visivelHome + ", visivelPortal="
				+ visivelPortal + ", liberadoHome=" + liberadoHome
				+ ", liberadoPortal=" + liberadoPortal + "]";
	}
	
}
