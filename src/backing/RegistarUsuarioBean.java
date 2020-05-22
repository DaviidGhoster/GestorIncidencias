package backing;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.RollbackException;

import entidades.Departamento;
import entidades.Role;
import entidades.Usuario;
import excepciones.UsuarioException;
import services.DepartamentoService;
import services.RolesService;
import services.UsuarioService;

@Named
@SessionScoped
public class RegistarUsuarioBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7374983185424125417L;
	@EJB
	private UsuarioService usuarioService;
	@EJB
	private DepartamentoService departamentoService;
	@EJB
	private RolesService rolesService;
	private Usuario usuario = new Usuario();
	private List<Departamento> listadoDepartamentos = null;
	private List<Role> listadoRoles = null;
	private int idRol = 1;
	private int idDepartamento = 0;

	public RegistarUsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void ini() {
		listadoRoles = rolesService.getRoles();
		listadoDepartamentos = departamentoService.getDepartamentos();
	}

	public DepartamentoService getDepartamentoService() {
		return departamentoService;
	}

	public RolesService getRolesService() {
		return rolesService;
	}

	public List<Departamento> getListadoDepartamentos() {
		return listadoDepartamentos;
	}

	public List<Role> getListadoRoles() {
		return listadoRoles;
	}

	public int getIdRol() {
		return idRol;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	public void setListadoDepartamentos(List<Departamento> listadoDepartamentos) {
		this.listadoDepartamentos = listadoDepartamentos;
	}

	public void setListadoRoles(List<Role> listadoRoles) {
		this.listadoRoles = listadoRoles;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void nuevoRegistro() {
		try {
			Departamento departamento = null;
			if (idDepartamento != 0) {
				departamento = departamentoService.getDepartamentoById(idDepartamento).get(0);
			}
			usuario.setDepartamentoBean(departamento);
			usuario.setRoles(rolesService.getRolById(idRol));
			usuarioService.registrarUsuario(usuario);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado con éxito",
					"Registro creado con éxito"));
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (UsuarioException e) {
			String mensaje = e.getMessage();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
		} catch (Exception e) {
			String mensaje = e.getCause().getCause().getMessage();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
		}
		usuario = new Usuario();
	}

}
