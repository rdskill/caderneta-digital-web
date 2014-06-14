package filter;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String paginaAtual = facesContext.getViewRoot().getViewId();
		System.out.println(paginaAtual);
		boolean isLoginPage = (paginaAtual.indexOf("login.xhtml") > -1);
		System.out.println(isLoginPage);
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Object usuarioAtual = session.getAttribute("currentUser");
		
		if (!isLoginPage && usuarioAtual == null) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");
		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}