package managedbean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import util.JSFUtil;

import bean.Aluno;

import dao.AlunoDao;

@ManagedBean
@SessionScoped
public class AlunoMB {

	Aluno aluno;
	AlunoDao alunoDao;
	ArrayList<Aluno> listaAluno;
	String paginaDestino;
	private int alterar;
	private int radioPesquisa;
	private String pesquisaVar;
	private int exibirPesquisa;

	public AlunoMB() {
		aluno = new Aluno();
		alunoDao = new AlunoDao();
		listaAluno = new ArrayList<Aluno>();
		alterar = 0;
		radioPesquisa = 1;
		exibirPesquisa = 0;
	}

	public void enviarImagem(FileUploadEvent event) {
		byte[] img = event.getFile().getContents();
		aluno.setFoto(img);
	}

	public String cadastroAlunos() {
		paginaDestino = "CadastroAlunos.xhtml";
		alterar = 0;
		aluno = new Aluno();
		return paginaDestino;
	}

	public String preparaAlteracao() {
		paginaDestino = "CadastroAlunos.xhtml";
		alterar = 1;
		return paginaDestino;
	}

	public String pesquisaAlunos() {
		paginaDestino = "PesquisarAlunos.xhtml";
		listaAluno.clear();
		pesquisaVar = "";
		exibirPesquisa = 0;
		radioPesquisa = 1;
		return paginaDestino;
	}
	
	public String voltarPesquisa() {
		paginaDestino = "PesquisarAlunos.xhtml";
		return paginaDestino;
	}

	public void incluir() {
		try {

			if (aluno.getNome().isEmpty() || aluno.getNomeUsuario().isEmpty()
					|| aluno.getRa().toString().isEmpty()
					|| aluno.getSenha().isEmpty()) {
				JSFUtil.addWarnMessage("Todos campos devem ser preenchidos.");
			} else {
				boolean encontrou = alunoDao.buscarPorNomeUsuarioAluno(aluno
						.getNomeUsuario());
				if (encontrou == false) {
					alunoDao.incluirAluno(aluno);
					JSFUtil.addInfoMessage("Aluno incluído com sucesso!");
					aluno = new Aluno();
				} else {
					JSFUtil.addInfoMessage("Erro ao incluir - Nome de usuário já está sendo usado.");
				}
			}

		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao incluir.");
			e.printStackTrace();
		}
	}

	public void alterar() {
		try {
			// boolean encontrou = alunoDao.buscarPorNomeUsuarioAluno(aluno
			// .getNomeUsuario());

			// if (encontrou == false) {
			if (aluno.getNome().isEmpty() || aluno.getNomeUsuario().isEmpty()
					|| aluno.getRa().toString().isEmpty()
					|| aluno.getSenha().isEmpty()) {
				JSFUtil.addWarnMessage("Todos campos devem ser preenchidos.");
			} else {
				alunoDao.alterarAluno(aluno);
				JSFUtil.addInfoMessage("Aluno alterado com sucesso!");
				aluno = new Aluno();
				alterar = 0;
			}
			// } else {
			// JSFUtil.addInfoMessage("Erro ao alterar - Nome de usuário já está sendo usado.");
			// }

		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao alterar.");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			alunoDao.excluirAluno(aluno);
			JSFUtil.addInfoMessage("Aluno excluído com sucesso!");
			listaAluno = new ArrayList<Aluno>();
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao excluir");
			e.printStackTrace();
		}
	}

	public void consultarTudo() {
		try {
			listaAluno = alunoDao.consultarTudoAluno();
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao consultar.");
			e.printStackTrace();
		}
	}

	/*public void consultarPorId() {
		try {
			aluno = alunoDao.consultarAlunoPorId(aluno.getIdAluno());
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao consultar.");
			e.printStackTrace();
		}
	}*/

	public void pesquisa() {
		exibirPesquisa = 1;
		if (pesquisaVar.isEmpty()) {
			consultarTudo();
		} else {
			listaAluno.clear();
			if (radioPesquisa == 1) {
				consultarPorNome();
			} else if (radioPesquisa == 2) {
				consultarPorRa();
			}
		}
	}

	/*public void consultarPorNomeUsuario() {
		try {
			listaAluno = alunoDao.consultarAlunoPorNomeUsuario(pesquisaVar);
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao consultar.");
			e.printStackTrace();
		}
	}*/

	public void consultarPorNome() {
		try {
			listaAluno = alunoDao.consultarAlunoPorNome(pesquisaVar);
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao consultar.");
			e.printStackTrace();
		}
	}
	
	public void consultarPorRa() {
		try {
			listaAluno = alunoDao.consultarAlunoPorRA(Integer.parseInt(pesquisaVar));
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao consultar.");
			e.printStackTrace();
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public AlunoDao getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public ArrayList<Aluno> getListaAluno() {
		return listaAluno;
	}

	public void setListaAluno(ArrayList<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}

	public int getAlterar() {
		return alterar;
	}

	public void setAlterar(int alterar) {
		this.alterar = alterar;
	}

	public int getRadioPesquisa() {
		return radioPesquisa;
	}

	public void setRadioPesquisa(int radioPesquisa) {
		this.radioPesquisa = radioPesquisa;
	}

	public String getPesquisaVar() {
		return pesquisaVar;
	}

	public void setPesquisaVar(String pesquisaVar) {
		this.pesquisaVar = pesquisaVar;
	}

	public int getExibirPesquisa() {
		return exibirPesquisa;
	}

	public void setExibirPesquisa(int exibirPesquisa) {
		this.exibirPesquisa = exibirPesquisa;
	}

}