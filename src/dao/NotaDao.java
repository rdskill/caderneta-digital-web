package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import util.Util;

import bean.Matricula;
import bean.Nota;
import bean.TipoNota;

public class NotaDao extends HibernateAbstractDao {
	
	public void adicionarOuAlterarNota(Nota nota) throws SQLException {
		incluir(nota);
	}
	
	public void adicionarNotaExame(Nota nota) throws SQLException {
		TipoNota tn = new TipoNota();
		tn.setIdTipoNota(2);
		nota.setIdTipoNota(tn);
		incluir(nota);
	}
	
	public void adicionarNotaBimestral(Nota nota) throws SQLException {
		TipoNota tn = new TipoNota();
		tn.setIdTipoNota(1);
		nota.setIdTipoNota(tn);
		incluir(nota);
	}
	
	public ArrayList<Nota> buscarNotaPorMatricula(Matricula matricula) {
		Criteria c = getOpenSession().createCriteria(Nota.class);
		c.add(Restrictions.eq("idMatricula", matricula));
		@SuppressWarnings("unchecked")
		ArrayList<Nota> ln = (ArrayList<Nota>) c.list();
		return Util.OrganizarNotas(ln);
	}
	
	public static void main(String[] args) {

		
	}

}
