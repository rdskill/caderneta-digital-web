package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
		doPost(httpservletrequest, httpservletresponse);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		String nome = req.getParameter("nome").trim().toUpperCase();
		String endereco = req.getParameter("endereco").toUpperCase();
		System.out.println(nome + " - " + endereco);
		out.print("resposta para o android");
		
	}

	public static void main(String[] args) {
		Controlador c = new Controlador();
	}

}
