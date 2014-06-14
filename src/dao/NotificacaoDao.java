package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import bean.Notificacao;


public class NotificacaoDao extends HibernateAbstractDao {

	public void incluirNotificacao(Notificacao notificacao) throws SQLException {
		notificacao.setData(new Date());
		incluir(notificacao);
	}
	
	public void alterarNotificacao(Notificacao notificacao) throws SQLException {
		alterar(notificacao);
	}
	
	public void excluirNotificacao(Notificacao notificacao) throws SQLException {
		excluir(notificacao);
	}
	
	public Notificacao consultarNotificacaoPorId(int id) throws SQLException {
		return (Notificacao) consultarPorId(id, "Notificacao.consultarPorId", "idNotificacao");
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Notificacao> consultarNotificacaoPorTitulo(String titulo) throws SQLException {
		return (ArrayList<Notificacao>) consultarPorTitulo("%"+titulo+"%", "Notificacao.buscarPorTitulo");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Notificacao> consultarNotificacaoPorData(String data) throws SQLException {
		return (ArrayList<Notificacao>) consultarPorData(data, "Notificacao.buscarPorData");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Notificacao> consultarTudoNotificacao() throws SQLException {
		return (ArrayList<Notificacao>) consultarTudo("Notificacao.buscarTudo");
	} 
	
}
