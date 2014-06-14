package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bean.Frequencia;
import bean.Leciona;
import bean.Matricula;

public class FrequenciaDao extends HibernateAbstractDao {
	
	public void incluirFrequencia(Frequencia frequencia) throws SQLException {
		incluir(frequencia);
	}
	
	public void alterarFrequencia(Frequencia frequencia) throws SQLException {
		alterar(frequencia);		
	}
	
	public ArrayList<Frequencia> consultarFrequenciaPorIdMatricula(Matricula matricula) throws SQLException {
		return (ArrayList<Frequencia>) consultarPorId(matricula.getIdMatricula(), "Frequencia.buscarPorIdMatricula", "idMatricula");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Frequencia> consultarFrequenciaPorLeciona(Leciona leciona) throws SQLException {
		Session sessao = getSessionFactory().openSession();
		Criteria c = sessao.createCriteria(Frequencia.class);
		c.add(Restrictions.eq("idFrequencia.idMatricula.idLeciona", leciona));
		return (ArrayList<Frequencia>) c.list();
	}
	
	public static void main(String[] args) {
		Leciona leciona = new Leciona();
		leciona.setIdLeciona(1);
		try {
			FrequenciaDao freq = new FrequenciaDao();
			ArrayList<Frequencia> lista = freq.consultarFrequenciaPorLeciona(leciona);
			for (Frequencia f : lista) {
				System.out.println(f.getIdMatricula().getIdAluno());
				System.out.println(f.getIdMatricula().getIdAluno());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
