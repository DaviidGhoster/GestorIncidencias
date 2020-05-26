package backing;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.RollbackException;

import entidades.Incidencia;
import entidades.Prioridad;
import entidades.Comentario;
import services.ComentarioService;
import services.IncidenciaService;
import services.PrioridadService;
import services.UsuarioService;

@Named
@SessionScoped
public class NuevaIncidenciaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7887047000890230473L;
	@EJB
	IncidenciaService incidenciaService;
	@EJB
	PrioridadService prioridadService;
	@EJB
	UsuarioService usuarioService;
	@EJB
	ComentarioService comentarioService;
	List<Prioridad> listadoPrioridad;
	Comentario c=new Comentario();
	Incidencia incidencia = new Incidencia();
	Long prioridad;
	String comentario;
	String detalle;
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	String username = ec.getRemoteUser();

	public NuevaIncidenciaBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void ini() {
		listadoPrioridad = prioridadService.getPrioridad();
	}

	public PrioridadService getPrioridadService() {
		return prioridadService;
	}

	public String getComentario() {
		return comentario;
	}

	public ExternalContext getEc() {
		return ec;
	}

	public void setPrioridadService(PrioridadService prioridadService) {
		this.prioridadService = prioridadService;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setEc(ExternalContext ec) {
		this.ec = ec;
	}

	public void nuevaIncidencia() {
		try {
			c.setDetallesComentario(comentario);
			c.setIdcomentario(comentarioService.getUltimoComentario()+1);
			c.setFechaComentario(new Date());
			c.setIncidencia(incidencia);
			c.setUsuario(usuarioService.getUsuarioById(username).get(0));
			incidenciaService.insertIncidencia(incidencia, username, prioridad, detalle,c);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado con éxito",
					"Registro creado con éxito"));
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		incidencia = new Incidencia();
		c=new Comentario();
	}

	public List<Prioridad> getListadoPrioridad() {
		return listadoPrioridad;
	}

	public void setListadoPrioridad(List<Prioridad> listadoPrioridad) {
		this.listadoPrioridad = listadoPrioridad;
	}

	public IncidenciaService getIncidenciaService() {
		return incidenciaService;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public Long getPrioridad() {
		return prioridad;
	}

	public String getDetalle() {
		return detalle;
	}

	public String getUsername() {
		return username;
	}

	public void setIncidenciaService(IncidenciaService incidenciaService) {
		this.incidenciaService = incidenciaService;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
