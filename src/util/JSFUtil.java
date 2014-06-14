package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class JSFUtil {
	/**
	 * Mostrar uma mensagem de Informação
	 * 
	 * @param msg
	 */
	public static void addInfoMessage(String msg) {
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(msg, facesMessage);
	}

	public static void addErrorMessage(String msg) {
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, msg, msg);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(msg, facesMessage);
	}

	/**
	 * Mostrar mensagem de aviso no FrontEnd
	 * 
	 * @param msg
	 */
	public static void addWarnMessage(String msg) {
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_WARN, msg, msg);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(msg, facesMessage);
	}

	/**
	 * Mostrar mensagem de aviso no FrontEnd para um componente p:message
	 * identificado
	 * 
	 * @param msg
	 */
	public static void addWarnMessage(String msg,
			String nomeDoComponentePrimeMessage) {
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_WARN, msg, msg);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(nomeDoComponentePrimeMessage, facesMessage);
	}

	/**
	 * Método requerido para geração de relatórios
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse resp = (HttpServletResponse) externalContext
				.getResponse();
		return resp;
	}
	
	

	/**
	 * Método requerido para geração de relatórios
	 * 
	 * @return
	 */
	public static void responseComplete() {
		FacesContext.getCurrentInstance().renderResponse();
		System.out.println("passou pelo render");
		FacesContext.getCurrentInstance().responseComplete();
		System.out.println("passou pelo response complete");

	}

}
