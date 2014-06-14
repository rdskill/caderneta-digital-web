

import org.hibernate.Session;

import util.HibernateUtil;

public class GeraTabelas {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Session sessao = (Session) HibernateUtil.getSessionFactory();
		
	}
}
