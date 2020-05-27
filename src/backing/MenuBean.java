package backing;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entidades.Usuario;
import services.UsuarioService;

import java.io.Serializable;

@Named
@SessionScoped
public class MenuBean implements Serializable {

	@EJB
	UsuarioService usuarioService;
	Usuario u;
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	String username = ec.getRemoteUser();

	private static final long serialVersionUID = -4940872351389647929L;

	public MenuBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		u = usuarioService.getUsuarioById(username).get(0);
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public Usuario getU() {
		return u;
	}

	public ExternalContext getEc() {
		return ec;
	}

	public String getUsername() {
		return username;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public void setEc(ExternalContext ec) {
		this.ec = ec;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
