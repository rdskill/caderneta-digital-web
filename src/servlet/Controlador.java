package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Aluno;
import bean.Frequencia;
import bean.FrequenciaTotalizada;
import bean.Leciona;
import bean.Matricula;
import bean.Nota;
import bean.Notificacao;
import bean.Professor;
import bean.TipoNota;

import dao.AlunoDao;
import dao.DataLecionaDao;
import dao.FrequenciaDao;
import dao.LecionaDao;
import dao.MatriculaDao;
import dao.NotaDao;
import dao.NotificacaoDao;
import dao.ProfessorDao;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("parametro").equals("loginAluno")) {
			try {
				AlunoDao alunoDAO = new AlunoDao();
				Aluno aluno = new Aluno();
				PrintWriter out = response.getWriter();
				aluno.setNomeUsuario(request.getParameter("nomeUsuario"));
				aluno.setSenha(request.getParameter("senha"));

				aluno = alunoDAO.buscarAlunoPorUsuarioSenha(aluno);

				String resposta = aluno.getIdAluno() + "." + aluno.getNome()
						+ "." + aluno.getNomeUsuario() + "." + aluno.getRa();

				out.print(resposta);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}

		}

		if (request.getParameter("parametro").equals("loginProfessor")) {
			try {
				ProfessorDao professorDAO = new ProfessorDao();
				Professor professor = new Professor();
				PrintWriter out = response.getWriter();
				professor.setNomeUsuario(request.getParameter("nomeUsuario"));
				professor.setSenha(request.getParameter("senha"));

				professor = professorDAO
						.buscarProfessorPorUsuarioSenha(professor);

				String resposta = professor.getIdProfessor() + "."
						+ professor.getNome() + "."
						+ professor.getNomeUsuario();

				if (professor != null) {
					FrequenciaDao frequenciaDao = new FrequenciaDao();
					frequenciaDao.criarFrequenciasDaAula(professor);
				}

				out.print(resposta);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}

		}

		if (request.getParameter("parametro").equals("carregaLeciona")) {
			String resposta = "";
			try {
				LecionaDao lecionaDao = new LecionaDao();
				Professor professor = new Professor();
				PrintWriter out = response.getWriter();
				professor.setIdProfessor(Integer.parseInt(request
						.getParameter("idProfessor")));

				ArrayList<Leciona> materias = lecionaDao
						.buscarDicipinasPorIdProfessor(professor);
				// ArrayList<Leciona> materiasNovas = new ArrayList<Leciona>();

				DataLecionaDao dataLecionaDao = new DataLecionaDao();
				materias = dataLecionaDao.buscarLecionaPorIdEData(materias);

				for (int i = 0; i < materias.size(); i++) {
					resposta += materias.get(i).getIdLeciona().toString();
					resposta += ".";
					resposta += materias.get(i).getIdDisciplina()
							.getIdDisciplina().getNome();
					resposta += "/n";
				}

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}
		}

		if (request.getParameter("parametro").equals("carregaListaChamada")) {
			String resposta = "";
			try {
				Leciona leciona = new Leciona();
				MatriculaDao matriculaDao = new MatriculaDao();

				PrintWriter out = response.getWriter();

				leciona.setIdLeciona(Integer.parseInt((request
						.getParameter("idLeciona"))));

				ArrayList<Matricula> listaChamada = matriculaDao
						.buscarPorIdLeciona(leciona);

				for (int i = 0; i < listaChamada.size(); i++) {
					resposta += listaChamada.get(i).getIdAluno().getNome();
					resposta += ".";
					resposta += listaChamada
							.get(i)
							.getFrequenciaList()
							.get(listaChamada.get(i).getFrequenciaList().size() - 1)
							.getIdFrequencia();
					resposta += ".";
					resposta += listaChamada
							.get(i)
							.getFrequenciaList()
							.get(listaChamada.get(i).getFrequenciaList().size() - 1)
							.getSituacao();
					resposta += ".";
					resposta += listaChamada
							.get(i)
							.getFrequenciaList()
							.get(listaChamada.get(i).getFrequenciaList().size() - 1)
							.getIdMatricula().getIdMatricula();
					resposta += "/n";
				}

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}

		}

		if (request.getParameter("parametro").equals("lancarFrequenciaAluno")) {
			String resposta = "";
			try {
				FrequenciaDao frequenciaDao = new FrequenciaDao();

				PrintWriter out = response.getWriter();

				String situacaoAluno = request.getParameter("situacaoAluno");
				String idFrequencia = request.getParameter("idFrequencia");
				String idMatricula = request.getParameter("idMatricula");
				Date data = new Date();

				Frequencia frequencia = new Frequencia();
				Matricula matricula = new Matricula();
				matricula.setIdMatricula(Integer.parseInt(idMatricula));
				frequencia.setSituacao(situacaoAluno);
				frequencia.setIdFrequencia(Integer.parseInt(idFrequencia));
				frequencia.setIdMatricula(matricula);
				frequencia.setData(data);
				frequenciaDao.incluirOuAlterarFrequencia(frequencia);

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}

		}

		if (request.getParameter("parametro").equals("receberNotificacoes")) {
			String resposta = "";
			try {
				NotificacaoDao notificacaoDao = new NotificacaoDao();

				PrintWriter out = response.getWriter();
				ArrayList<Notificacao> listaNotificacao = notificacaoDao
						.consultarTudoNotificacao();

				for (int i = 0; i < listaNotificacao.size(); i++) {
					resposta += listaNotificacao.get(i).getTitulo();
					resposta += "/p";
					resposta += listaNotificacao.get(i).getConteudo();
					resposta += "/p";
					resposta += listaNotificacao.get(i).getData();
					resposta += "/p";
					resposta += listaNotificacao.get(i).getDataEvento();
					resposta += "/n";
				}

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}

		}

		if (request.getParameter("parametro").equals("buscarMateriaAluno")) {
			String resposta = "";
			try {
				PrintWriter out = response.getWriter();

				String idAluno = request.getParameter("idAluno");
				Aluno aluno = new Aluno();
				aluno.setIdAluno(Integer.parseInt(idAluno));

				MatriculaDao matriculaDao = new MatriculaDao();
				ArrayList<Matricula> listMateria = matriculaDao
						.buscarPorIdAluno(aluno);

				for (int i = 0; i < listMateria.size(); i++) {
					resposta += listMateria.get(i).getIdMatricula();
					resposta += ".";
					resposta += listMateria.get(i).getIdLeciona()
							.getIdDisciplina().getIdDisciplina().getNome();
					resposta += "/n";
				}

				out.print(resposta);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (request.getParameter("parametro").equals("buscarFrequenciaNota")) {
			String resposta = "";
			try {
				PrintWriter out = response.getWriter();

				String idAluno = request.getParameter("idMatricula");
				Matricula matricula = new Matricula();
				matricula.setIdMatricula(Integer.parseInt(idAluno));

				FrequenciaDao frequenciaDao = new FrequenciaDao();
				FrequenciaTotalizada frequenciaTotalizada = frequenciaDao
						.somarFaltas(matricula);

				NotaDao notaDao = new NotaDao();
				ArrayList<Nota> listaNota = notaDao
						.buscarNotaPorMatricula(matricula);

				if (listaNota.size() == 1) {
					resposta += listaNota.get(0).getNota();
					resposta += "/n";
					resposta += "-";
					resposta += "/n";
					resposta += "-";
					resposta += "/n";
				} else if (listaNota.size() == 2) {
					for (int i = 0; i < listaNota.size(); i++) {
						resposta += listaNota.get(i).getNota();
						resposta += "/n";
					}
					resposta += "-";
					resposta += "/n";
				} else if (listaNota.size() == 3) {
					for (int i=0;i<listaNota.size();i++) {
						resposta += listaNota.get(i).getNota();
						resposta += "/n";
					}
				} else {
					resposta += "-/n-/n-/n";
				}

				resposta += frequenciaTotalizada.getFaltas();
				resposta += "/n";
				resposta += frequenciaTotalizada.getPercentualFrequencia();

				out.print(resposta);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (request.getParameter("parametro").equals("carregaLecionaInteiro")) {
			String resposta = "";
			try {
				LecionaDao lecionaDao = new LecionaDao();
				Professor professor = new Professor();
				PrintWriter out = response.getWriter();
				professor.setIdProfessor(Integer.parseInt(request
						.getParameter("idProfessor")));

				ArrayList<Leciona> materias = lecionaDao
						.buscarDicipinasPorIdProfessor(professor);

				for (int i = 0; i < materias.size(); i++) {
					resposta += materias.get(i).getIdLeciona().toString();
					resposta += ".";
					resposta += materias.get(i).getIdDisciplina()
							.getIdDisciplina().getNome();
					resposta += "/n";
				}

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}
		}

		if (request.getParameter("parametro").equals(
				"carregaChamadaNotaFrequencia")) {
			String resposta = "";
			try {
				PrintWriter out = response.getWriter();
				Leciona leciona = new Leciona();
				leciona.setIdLeciona(Integer.parseInt((request
						.getParameter("idLeciona"))));

				MatriculaDao matriculaDao = new MatriculaDao();

				ArrayList<Matricula> listaMatricula = new ArrayList<Matricula>();
				listaMatricula = matriculaDao.buscarPorIdLeciona(leciona);

				for (int i = 0; i < listaMatricula.size(); i++) {
					resposta += listaMatricula.get(i).getIdAluno().getIdAluno();
					resposta += ".";
					resposta += listaMatricula.get(i).getIdAluno().getNome();
					resposta += ".";
					resposta += listaMatricula.get(i).getIdMatricula();
					resposta += "/n";
				}

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}
		}

		if (request.getParameter("parametro").equals("carregaNotasAluno")) {
			String resposta = "";
			try {
				PrintWriter out = response.getWriter();
				Matricula matricula = new Matricula();
				matricula.setIdMatricula(Integer.parseInt((request
						.getParameter("idMatricula"))));

				NotaDao notaDao = new NotaDao();
				ArrayList<Nota> listaNotas = new ArrayList<Nota>();
				listaNotas = notaDao.buscarNotaPorMatricula(matricula);

				for (int i = 0; i < listaNotas.size(); i++) {
					resposta += listaNotas.get(i).getIdNota();
					resposta += "/id";
					resposta += listaNotas.get(i).getNota();
					resposta += "/n";
				}

				out.print(resposta);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e);
			}
		}
		
		if (request.getParameter("parametro").equals("lancarNotas")) {
			String resposta = "";
			PrintWriter out = response.getWriter();
			
			try {
				int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));
				float notaPrimeiroBim = -1;
				float notaSegundoBim = -1;
				float notaExame = -1;
				int idNotaPriBim = 0;
				int idNotaSegBim = 0;
				int idNotaExame = 0;
				notaPrimeiroBim = Float.parseFloat(request
						.getParameter("primeiroBim"));
				notaSegundoBim = Float.parseFloat(request
						.getParameter("segundoBim"));
				notaExame = Float.parseFloat(request
						.getParameter("exame"));
				idNotaPriBim = Integer.parseInt(request
						.getParameter("idNotaPrimBim"));
				idNotaSegBim = Integer.parseInt(request
						.getParameter("idNotaSegBim"));
				idNotaExame = Integer.parseInt(request
						.getParameter("idNotaExame"));
				
				NotaDao notaDao = new NotaDao();
				
				TipoNota tn = new TipoNota();
				Matricula matricula = new Matricula();
				matricula.setIdMatricula(idMatricula);
				
				
				if (notaPrimeiroBim != -1 && notaPrimeiroBim >= 0 && notaPrimeiroBim <= 10) {
					Nota nota = new Nota();
					nota.setIdMatricula(matricula);
					if (idNotaPriBim != 0) {
						nota.setIdNota(idNotaPriBim);
					}					
					nota.setNota(notaPrimeiroBim);
					tn.setIdTipoNota(1);
					nota.setIdTipoNota(tn);
					notaDao.adicionarNotaBimestral(nota);
				} 
				if (notaSegundoBim != -1 && notaSegundoBim >= 0 && notaSegundoBim <= 10) {
					Nota nota = new Nota();
					nota.setIdMatricula(matricula);
					if (idNotaSegBim != 0) {
						nota.setIdNota(idNotaSegBim);
					}	
					nota.setNota(notaSegundoBim);
					tn.setIdTipoNota(2);
					nota.setIdTipoNota(tn);
					notaDao.adicionarNotaBimestral(nota);
				}
				if (notaExame != -1 && notaExame >= 0 && notaExame <= 10) {
					Nota nota = new Nota();
					nota.setIdMatricula(matricula);
					if (idNotaExame != 0) {
						nota.setIdNota(idNotaExame);
					}
					
					nota.setNota(notaExame);
					notaDao.adicionarNotaExame(nota);
				}				
				
				resposta ="Notas inseridas com sucesso!";
				out.print(resposta);
			} catch (Exception e) {
				System.out.println("Erro: "+e);
				resposta ="Erro";
				out.print(resposta);
			}
		}

	}
}
