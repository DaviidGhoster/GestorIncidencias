package backing;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.RollbackException;

import entidades.Incidencia;
import entidades.Prioridad;
import services.IncidenciaService;
import services.PrioridadService;

@Named
@ViewScoped
public class NuevaIncidenciaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7887047000890230473L;
	@EJB
	IncidenciaService incidenciaService;
	@EJB
	PrioridadService prioridadService;
	List<Prioridad> listadoPrioridad;
	Incidencia incidencia = new Incidencia();
	Long prioridad;
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

	public void nuevaIncidencia() {
		try {
			incidenciaService.insertIncidencia(incidencia, username, prioridad, detalle);
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
		incidencia=new Incidencia();
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
