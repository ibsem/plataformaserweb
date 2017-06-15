/*
 * Código-fonte do livro "Programação Java para a Web"
 * Autores: Décio Heinzelmann Luckow <decioluckow@gmail.com>
 *          Alexandre Altair de Melo <alexandremelo.br@gmail.com>
 *
 * ISBN: 978-85-7522-238-6
 * http://www.javaparaweb.com.br
 * http://www.novatec.com.br/livros/javaparaweb
 * Editora Novatec, 2010 - todos os direitos reservados
 *
 * LICENÇA: Este arquivo-fonte está sujeito a Atribuição 2.5 Brasil, da licença Creative Commons,
 * que encontra-se disponível no seguinte endereço URI: http://creativecommons.org/licenses/by/2.5/br/
 * Se você não recebeu uma cópia desta licença, e não conseguiu obtê-la pela internet, por favor,
 * envie uma notificação aos seus autores para que eles possam enviá-la para você imediatamente.
 *
 *
 * Source-code of "Programação Java para a Web" book
 * Authors: Décio Heinzelmann Luckow <decioluckow@gmail.com>
 *          Alexandre Altair de Melo <alexandremelo.br@gmail.com>
 *
 * ISBN: 978-85-7522-238-6
 * http://www.javaparaweb.com.br
 * http://www.novatec.com.br/livros/javaparaweb
 * Editora Novatec, 2010 - all rights reserved
 *
 * LICENSE: This source file is subject to Attribution version 2.5 Brazil of the Creative Commons
 * license that is available through the following URI:  http://creativecommons.org/licenses/by/2.5/br/
 * If you did not receive a copy of this license and are unable to obtain it through the web, please
 * send a note to the authors so they can mail you a copy immediately.
 *
 */
package edu.cesusc.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import edu.cesusc.servico.Servico;
import edu.cesusc.servico.ServicoRN;
import edu.cesusc.seguranca.usuario.Usuario;
import edu.cesusc.seguranca.usuario.UsuarioRN;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

	private Usuario	   usuarioLogado	= null;
	private Servico	      servicoAtiva	  = null;
	private Locale	      localizacao	  = null;
	
	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())) {

			if (login != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				this.servicoAtiva = null;

				String[] info = this.usuarioLogado.getIdioma().split("_");
				Locale locale = new Locale(info[0], info[1]);
				context.getViewRoot().setLocale(locale);
			}
		}
		return usuarioLogado;
	}

	public Servico getServicoAtiva() {
		if (this.servicoAtiva == null) {
			Usuario usuario = this.getUsuarioLogado();

			ServicoRN servicoRN = new ServicoRN();
			
			if (this.servicoAtiva == null) {
				List<Servico> servicos = servicoRN.listar(usuario);
				if (servicos != null) {
					for (Servico servico : servicos) {
						this.servicoAtiva = servico;
						break;
					}
				}
			}
		}
		return this.servicoAtiva;
	}

	public void setServicoAtiva(ValueChangeEvent event) {

		Integer servico = (Integer) event.getNewValue();

		ServicoRN servicoRN = new ServicoRN();
		this.servicoAtiva = servicoRN.carregar(servico);
	}

	public Locale getLocaleUsuario() {
		if (this.localizacao == null) {
			Usuario usuario = this.getUsuarioLogado();
			String idioma = usuario.getIdioma();
			String[] info = idioma.split("_");
			this.localizacao = new Locale(info[0], info[1]);
		}
		return this.localizacao;
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}
}
