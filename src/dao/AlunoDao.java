package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bean.Aluno;

public class AlunoDao extends HibernateAbstractDao {

	public void incluirAluno(Aluno aluno) throws SQLException {
		incluir(aluno);
	}

	public void alterarAluno(Aluno aluno) throws SQLException {
		alterar(aluno);
	}

	public void excluirAluno(Aluno aluno) throws SQLException {
		excluir(aluno);
	}

	public Aluno consultarAlunoPorId(int id) throws SQLException {
		return (Aluno) consultarPorId(id, "Aluno.buscarPorIdAluno", "idAluno");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> consultarAlunoPorNomeUsuario(String usuario) throws SQLException {
		return (ArrayList<Aluno>) consultarPorNomeUsuario(usuario,"Aluno.buscarPorNomeUsuario");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> consultarAlunoPorNome(String nome) throws SQLException {
		return (ArrayList<Aluno>) consultarPorNome("%"+nome+"%","Aluno.buscarPorNome");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> consultarAlunoPorRA (Integer ra) throws SQLException {
		return (ArrayList<Aluno>) consultarPorRa(ra, "Aluno.buscarPorRa");
	}

	public boolean buscarPorNomeUsuarioAluno(String nomeUsuario)
			throws SQLException {
		Aluno aluno = new Aluno();
		aluno = (Aluno) consultarPorNomeUsuario(nomeUsuario,
				"Aluno.buscarPorNomeUsuario");

		boolean encontrou = false;

		if (aluno != null)
			encontrou = true;

		return encontrou;
	}

	public boolean buscarPorUsuarioSenha(Aluno aluno) {
		Session sessao = getOpenSession();
		Criteria criteria = sessao.createCriteria(Aluno.class);
		criteria.add(Restrictions.like("nomeUsuario", aluno.getNomeUsuario()));
		criteria.add(Restrictions.like("senha", aluno.getSenha()));
		Aluno a = new Aluno();
		a = (Aluno) criteria.uniqueResult();

		boolean encontrado = false;

		if (a != null)
			encontrado = true;

		return encontrado;
	}
	
	public Aluno buscarAlunoPorUsuarioSenha(Aluno aluno) {
		Session sessao = getOpenSession();
		Criteria criteria = sessao.createCriteria(Aluno.class);
		criteria.add(Restrictions.like("nomeUsuario", aluno.getNomeUsuario()));
		criteria.add(Restrictions.like("senha", aluno.getSenha()));
		Aluno a = new Aluno();
		a = (Aluno) criteria.uniqueResult();
		return a;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> consultarTudoAluno() throws SQLException {
		return (ArrayList<Aluno>) consultarTudo("Aluno.buscarTudo");
	}
	
	/*public static void main(String[] args) {
		AlunoDao ad = new AlunoDao();
		Aluno a = new Aluno();
		a.setNome("Renan Perez123");
		a.setNomeUsuario("teste");
		a.setRa(123456);
		a.setSenha("123456");
		try {
			ad.incluirAluno(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
