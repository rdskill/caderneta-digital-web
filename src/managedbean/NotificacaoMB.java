package managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.JSFUtil;
import bean.Notificacao;
import bean.UsuarioSecretaria;
import dao.NotificacaoDao;

@ManagedBean
@SessionScoped
public class NotificacaoMB {

	private Notificacao notificacao;
	private NotificacaoDao notificacaoDao;
	private ArrayList<Notificacao> listaNotificacaos;
	private UsuarioSecretaria usuarioSecretaria;
	private int escolhaRadio;
	private int alterar;
	private String pesquisaVar;
	private Date dataPesquisa;
	private int exibirPesquisa;

	String paginaDestino;
	
	public NotificacaoMB() {		
		notificacao = new Notificacao();
		notificacaoDao = new NotificacaoDao();
		listaNotificacaos = new ArrayList<Notificacao>();
		usuarioSecretaria = new UsuarioSecretaria();
		alterar = 1;
		escolhaRadio = 1;
		exibirPesquisa = 0;
		
		/*
		 * Os comandos abaixo colhem informações sobre o usuário atual 
		 * logado no sistema para serem usadas nos metodos desta classe
		 */
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		usuarioSecretaria = (UsuarioSecretaria) session.getAttribute("currentUser");
		notificacao.setIdUsuarioSecretaria(usuarioSecretaria);
		
	}
	
	
	// Método responsável para direcionar a página para a tela CadastroNotificacoes.xhtml
	public String cadastroNotificacoes() {
		paginaDestino = "CadastroNotificacoes.xhtml";
		notificacao = new Notificacao();
		notificacao.setIdUsuarioSecretaria(usuarioSecretaria);
		alterar = 0;
		return paginaDestino;
	}

	// Método responsável para direcionar a página para a tela PesquisarNotificacoes.xhtml
	public String pesquisaNotificacoes() {
		paginaDestino = "PesquisarNotificacoes.xhtml";
		pesquisaVar = "";
		exibirPesquisa = 0;
		escolhaRadio = 1;
		listaNotificacaos.clear();
		return paginaDestino;
	}
	
	// Método responsável para direcionar a página para a tela CadastroNotificacoes.xhtml quando é necessário alterar algum registro
	public String prepararAlteracao() {
		paginaDestino = "CadastroNotificacoes.xhtml";
		alterar = 1;
		return paginaDestino;
	}
	
	public String voltarPesquisa() {
		paginaDestino = "PesquisarNotificacoes.xhtml";
		return paginaDestino;
	}
	
	public void incluir() {
		try {
			if (notificacao.getTitulo().isEmpty() || notificacao.getDataEvento().toString().isEmpty() || notificacao.getConteudo().isEmpty()) {
				JSFUtil.addWarnMessage("Todos campos devem ser preenchidos.");
			} else {
				notificacaoDao.incluirNotificacao(notificacao);
				JSFUtil.addInfoMessage("Notificação incluída com sucesso.");
				notificacao = new Notificacao();
				notificacao.setIdUsuarioSecretaria(usuarioSecretaria);
			}
			
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao incluir.");
			e.printStackTrace();
		}
	}

	public void alterar() {
		try {
			if (notificacao.getTitulo().isEmpty() || notificacao.getDataEvento().toString().isEmpty() || notificacao.getConteudo().isEmpty()) {
				JSFUtil.addWarnMessage("Todos campos devem ser preenchidos.");
			} else {
				notificacaoDao.alterarNotificacao(notificacao);
				JSFUtil.addInfoMessage("Alteração efetuada com sucesso.");
				notificacao = new Notificacao();
				alterar = 0;
			}
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao alterar.");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			notificacaoDao.excluirNotificacao(notificacao);
			JSFUtil.addInfoMessage("Notificação excluida com sucesso!.");
			listaNotificacaos = new ArrayList<Notificacao>();
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao excluir.");
			e.printStackTrace();
		}
	}
	
	
	public void pesquisa() {
		exibirPesquisa = 1;
		if (pesquisaVar.isEmpty()) {
			consultarTudo();
		} else {
			if (escolhaRadio == 1) {
				consultarPorTitulo();
			} else if (escolhaRadio == 2) {
				consultarPorData();
			}
		}
	}

	public void consultarTudo() {
		try {
			listaNotificacaos = notificacaoDao.consultarTudoNotificacao();
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao consultar.");
			e.printStackTrace();
		}
	}
	
	public void consultarPorTitulo() {
		try {
			listaNotificacaos = notificacaoDao.consultarNotificacaoPorTitulo(pesquisaVar);
		} catch (Exception e) {
			JSFUtil.addInfoMessage("Erro ao consultar");
			e.printStackTrace();
		}
	}
	
	public void consultarPorData() {
		try {
			listaNotificacaos = notificacaoDao.consultarNotificacaoPorData(pesquisaVar);
		} catch (Exception e) {
			JSFUtil.addInfoMessage("Erro ao consultar");
			e.printStackTrace();
		}
	}

	public Notificacao getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(Notificacao notificacao) {
		this.notificacao = notificacao;
	}

	public ArrayList<Notificacao> getListaNotificacaos() {
		return listaNotificacaos;
	}

	public void setListaNotificacaos(ArrayList<Notificacao> listaNotificacaos) {
		this.listaNotificacaos = listaNotificacaos;
	}
	

	public int getEscolhaRadio() {
		return escolhaRadio;
	}

	public void setEscolhaRadio(int escolhaRadio) {
		this.escolhaRadio = escolhaRadio;
	}

	public int getAlterar() {
		return alterar;
	}

	public void setAlterar(int alterar) {
		this.alterar = alterar;
	}


	public String getPesquisaVar() {
		return pesquisaVar;
	}


	public void setPesquisaVar(String pesquisaVar) {
		this.pesquisaVar = pesquisaVar;
	}


	public Date getDataPesquisa() {
		return dataPesquisa;
	}


	public void setDataPesquisa(Date dataPesquisa) {
		this.dataPesquisa = dataPesquisa;
	}


	public int getExibirPesquisa() {
		return exibirPesquisa;
	}


	public void setExibirPesquisa(int exibirPesquisa) {
		this.exibirPesquisa = exibirPesquisa;
	}

}
