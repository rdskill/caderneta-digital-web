package dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bean.Aluno;
import bean.Leciona;
import bean.Matricula;

public class MatriculaDao extends HibernateAbstractDao {
	
	/*
	 * Metedo usado para recuperar os dados da aula (Leciona) para
	 * ser utilizado no momento da chamada. A partir dele 'e possivel
	 * ter acesso aos dados dos alunos, da disciplina e do professor 
	 */
	public ArrayList<Matricula> buscarPorIdLeciona(Leciona leciona) {
		Session sessao = getSessionFactory().openSession();
		Criteria c = sessao.createCriteria(Matricula.class);
		c.add(Restrictions.eq("idLeciona", leciona));
		return (ArrayList<Matricula>) c.list();
	}
	
	public ArrayList<Matricula> buscarPorIdAluno(Aluno aluno) {
		Session sessao = getSessionFactory().openSession();
		Criteria c = sessao.createCriteria(Aluno.class);
		c.add(Restrictions.eq("idAluno", aluno));
		return (ArrayList<Matricula>) c.list();
	}
	
	/*public static void main(String[] args) {
		MatriculaDao md = new MatriculaDao();
		Leciona l = new Leciona();
		l.setIdLeciona(1);
		ArrayList<Matricula> lista = md.buscarPorIdLeciona(l);
		for (Matricula m : lista) {
			System.out.println(m.getIdAluno().getNome());
		}
	}*/
	
}
