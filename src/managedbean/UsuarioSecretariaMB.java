package managedbean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioSecretariaDao;
import bean.UsuarioSecretaria;

import javax.servlet.http.HttpSession;

import util.JSFUtil;

@ManagedBean(name="usuarioSecretariaMB")
@SessionScoped
public class UsuarioSecretariaMB {

	private UsuarioSecretaria usuarioSecretaria = new UsuarioSecretaria();
	private UsuarioSecretariaDao usuarioSecretariaDao = new UsuarioSecretariaDao();
	private String nomeUsuario;
	private String senha;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String efetuarLogin() {
		String paginaDestino = null;
		try {
			usuarioSecretariaDao = new UsuarioSecretariaDao();
			usuarioSecretaria = usuarioSecretariaDao.buscarPorNomeUsuarioSecretaria(nomeUsuario);
		} catch (SQLException e) {
			System.out.println("ERRO");
			e.printStackTrace();
		}

		if (!(usuarioSecretaria == null) && senha.equals(usuarioSecretaria.getSenha())) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
			session.setAttribute("currentUser", usuarioSecretaria);
			
			UsuarioSecretaria us = (UsuarioSecretaria) session.getAttribute("currentUser");
			System.out.println(us.getNome());
			paginaDestino = "MenuPrincipal.xhtml";
		} else {
			JSFUtil.addInfoMessage("Nome de usuário ou senha incorretos");
			paginaDestino = "login.xhtml";
		}
		return paginaDestino;
	}
	
	public String fecharSessao() {
		String paginaDestino = "login.xhtml";
		usuarioSecretaria = new UsuarioSecretaria();
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute("currentUser", null);
		
		nomeUsuario = "";
		senha = "";
		
		return paginaDestino;
	}
}
