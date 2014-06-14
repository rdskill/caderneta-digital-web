package dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bean.Professor;

public class ProfessorDao extends HibernateAbstractDao {
	public Professor buscarProfessorPorUsuarioSenha(Professor professor) {
		Session sessao = getOpenSession();
		Criteria criteria = sessao.createCriteria(Professor.class);
		criteria.add(Restrictions.like("nomeUsuario", professor.getNomeUsuario()));
		criteria.add(Restrictions.like("senha", professor.getSenha()));
		Professor p = new Professor();
		p = (Professor) criteria.uniqueResult();
		return p;
	}
}
