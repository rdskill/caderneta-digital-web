package dao;

import java.sql.SQLException;

import bean.UsuarioSecretaria;

public class UsuarioSecretariaDao extends HibernateAbstractDao {

	public UsuarioSecretaria buscarPorNomeUsuarioSecretaria(String nomeUsuario)
			throws SQLException {
		UsuarioSecretaria usuarioSecretaria = new UsuarioSecretaria();
		usuarioSecretaria = (UsuarioSecretaria) consultarPorNomeUsuario(nomeUsuario, "UsuarioSecretaria.buscarPorNomeUsuario");

		return usuarioSecretaria;
	}
}
