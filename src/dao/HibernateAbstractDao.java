package dao;

import java.sql.SQLException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateAbstractDao extends HibernateConnectionFactory {

	protected void incluir(Object obj) throws SQLException {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.saveOrUpdate(obj);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			if (!(transacao == null)) {
				transacao.rollback();
			}

			if (!(sessao == null)) {
				sessao.close();
			}
			e.printStackTrace();
			e.getStackTrace();
			throw new SQLException("Erro ao incluir um dado da classe "
					+ obj.getClass().getName());

		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}
	}

	protected void alterar(Object obj) throws SQLException {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(obj);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (!(transacao == null)) {
				transacao.rollback();
			}

			if (!(sessao == null)) {
				sessao.close();
			}
			e.getStackTrace();
			throw new SQLException("Erro ao incluir um dado da classe "
					+ obj.getClass().getName());
		}
	}

	protected void excluir(Object obj) throws SQLException {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(obj);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			if (!(transacao == null)) {
				transacao.rollback();
			}

			if (!(sessao == null)) {
				sessao.close();
			}
			e.getStackTrace();
			new SQLException("Erro ao excluir um dado da classe "
					+ obj.getClass().getName());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}
	}

	protected Object consultarTudo(String namedQuery) throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			return query.list();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao consultar tudo" + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}
	}

	protected Object consultarPorId(int id, String namedQuery, String nomeCampo)
			throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			query.setInteger(nomeCampo, id);
			return query.uniqueResult();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao executar a Named Query: "
					+ namedQuery + " ---> " + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}
	}

	protected Object consultarPorNomeUsuario(String nomeUsuario,
			String namedQuery) throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			query.setString("nomeUsuario", nomeUsuario);
			return query.uniqueResult();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao executar a Named Query: "
					+ namedQuery + " ---> " + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}

	}

	protected Object consultarPorNome(String nome, String namedQuery)
			throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			query.setString("nome", nome);
			return query.list();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao executar a Named Query: "
					+ namedQuery + " ---> " + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}

	}
	
	protected Object consultarPorRa(Integer ra, String namedQuery)
			throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			query.setInteger("ra", ra);
			return query.list();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao executar a Named Query: "
					+ namedQuery + " ---> " + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}

	}
	
	protected Object consultarPorTitulo(String titulo, String namedQuery)
			throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			query.setString("titulo", titulo);
			return query.list();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao executar a Named Query: "
					+ namedQuery + " ---> " + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}

	}
	
	protected Object consultarPorData(String data, String namedQuery)
			throws SQLException {
		Session sessao = null;
		try {
			sessao = getSessionFactory().openSession();
			Query query = sessao.getNamedQuery(namedQuery);
			query.setString("data", data);
			return query.list();
		} catch (Exception e) {
			e.getStackTrace();
			throw new SQLException("Erro ao executar a Named Query: "
					+ namedQuery + " ---> " + e.getMessage());
		} finally {
			if (sessao.isOpen()) {
				sessao.close();
			}
		}

	}

	protected Session getOpenSession() {
		return getSessionFactory().openSession();
	}

}
