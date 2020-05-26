package backing;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.RollbackException;

import entidades.Comentario;
import entidades.Departamento;
import entidades.Estadoincidencia;
import entidades.Incidencia;
import entidades.Role;
import entidades.Usuario;
import services.ComentarioService;
import services.DepartamentoService;
import services.EstadoIncidenciaService;
import services.IncidenciaService;
import services.RolesService;
import services.UsuarioService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class EditarIncidenciaBean implements Serializable {

	private static final long serialVersionUID = -1949161888114233850L;

	public EditarIncidenciaBean() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	private RolesService rolesService;
	@EJB
	UsuarioService usuarioService;
	@EJB
	EstadoIncidenciaService estadoService;
	@EJB
	ComentarioService comentarioService;
	@EJB
	DepartamentoService departamentoService;
	@EJB
	IncidenciaService incidenciaService;

	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	String idIncidencia;
	String idDepartamento;
	Usuario usuario;
	String username = ec.getRemoteUser();
	String comentario;
	Long estado;
	Incidencia incidencia;

	private List<Departamento> listadoDepartamentos = null;
	private List<Comentario> listadoComenentarios;
	private List<Comentario> lCom;

	private List<Role> listadoRoles = null;

	@PostConstruct
	public void init() {
		usuario = usuarioService.getUsuarioById(username).get(0);
		listadoComenentarios = comentarioService.getComentarioById(idIncidencia);
		listadoDepartamentos = departamentoService.getDepartamentos();
	}
	

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public EstadoIncidenciaService getEstadoService() {
		return estadoService;
	}

	public ComentarioService getComentarioService() {
		return comentarioService;
	}

	public IncidenciaService getIncidenciaService() {
		return incidenciaService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public List<Comentario> getListadoComenentarios() {
		return listadoComenentarios;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setEstadoService(EstadoIncidenciaService estadoService) {
		this.estadoService = estadoService;
	}

	public void setComentarioService(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}

	public void setIncidenciaService(IncidenciaService incidenciaService) {
		this.incidenciaService = incidenciaService;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public void setListadoComenentarios(List<Comentario> listadoComenentarios) {
		this.listadoComenentarios = listadoComenentarios;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public ExternalContext getEc() {
		return ec;
	}

	public String getUsername() {
		return username;
	}

	public void setEc(ExternalContext ec) {
		this.ec = ec;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RolesService getRolesService() {
		return rolesService;
	}

	public List<Role> getListadoRoles() {
		return listadoRoles;
	}

	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	public void setListadoRoles(List<Role> listadoRoles) {
		this.listadoRoles = listadoRoles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Comentario> getlCom() {
		return lCom;
	}

	public void setlCom(List<Comentario> lCom) {
		this.lCom = lCom;
	}

	public List<Departamento> getListadoDepartamentos() {
		return listadoDepartamentos;
	}

	public void setListadoDepartamentos(List<Departamento> listadoDepartamentos) {
		this.listadoDepartamentos = listadoDepartamentos;
	}

	public String getIdIncidencia() {
		return idIncidencia;
	}

	public String getIdDepartamento() {
		return idDepartamento;
	}

	public DepartamentoService getDepartamentoService() {
		return departamentoService;
	}

	public void setIdIncidencia(String idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	public void guardarVariable() {

		if (idDepartamento != null) {
			int idDepartament = Integer.parseInt(idDepartamento);
			Departamento dep = departamentoService.getDepartamentoById(idDepartament).get(0);
			incidencia.setDepartamento(dep);
		}

		Long co = comentarioService.getUltimoComentario() + 1;
		Date date = new Date();

		Usuario u = usuarioService.getUsuarioById(username).get(0);
		Comentario c = new Comentario();
		c.setFechaComentario(date);
		c.setDetallesComentario(comentario);
		c.setIncidencia(incidencia);
		c.setUsuario(u);
		c.setIdcomentario(co);
		String l = String.valueOf(estado);
		Estadoincidencia es = estadoService.getEstado(l).get(0);
		try {
			System.out.println(incidencia.getDepartamento().getIdDepartamento());
			comentarioService.newComentario(c);
			List<Comentario> list=incidencia.getComentarios();
    		list.add(c);
    		incidencia.setComentarios(list);
			incidencia.setEstadoincidencia(es);
			incidenciaService.actualizarIncidencia(incidencia);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado con éxito",
					"Registro creado con éxito"));
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (Exception e) {
			String mensaje = e.getCause().getCause().getMessage();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
		}
		comentario="";
	}
}
