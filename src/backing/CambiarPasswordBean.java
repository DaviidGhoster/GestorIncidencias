package backing;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import services.UsuarioService;

import java.io.Serializable;

@Named
@SessionScoped
public class CambiarPasswordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 25940941818992102L;

	@EJB
	UsuarioService usuarioService;
	String password1="";
	String password2="";
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	String username = ec.getRemoteUser();

	public CambiarPasswordBean() {
		// TODO Auto-generated constructor stub
	}

	public void cambiarPassword() {
		if (password1.equals(password2)) {
			usuarioService.updatePassword(password1, username);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña cambiada con éxito",
					"Contraseña cambiada con éxito"));
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Las contraseñas no coinciden",
					"Las contraseñas no coinciden"));
		}
	}

	public String getPassword1() {
		return password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
