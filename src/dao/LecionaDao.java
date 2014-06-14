package dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import bean.Leciona;
import bean.Professor;

public class LecionaDao extends HibernateAbstractDao {

	@SuppressWarnings("unchecked")
	public ArrayList<Leciona> buscarDicipinasPorIdProfessor(Professor professor) {
		
		Criteria c = getOpenSession().createCriteria(Leciona.class);
		c.add(Restrictions.eq("idProfessor", professor));
		return (ArrayList<Leciona>) c.list();
	}
	/*
	public static void main(String[] args) {
		Professor p = new Professor();
		p.setIdProfessor(1);
		for (Leciona l : new LecionaDao().buscarDicipinasPorIdProfessor(p)) {
			System.out.println(l.getIdDisciplina().getIdDisciplina().getNome());
		}

	}*/

}
