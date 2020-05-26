package backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.RollbackException;

import entidades.Estadoincidencia;
import entidades.Incidencia;
import entidades.Usuario;
import services.EstadoIncidenciaService;
import services.IncidenciaService;
import services.UsuarioService;
import util.PaginacionHelper;
import java.io.Serializable;

@Named
@SessionScoped
public class VerIncidenciasBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8840963869733377589L;
	@EJB
	private IncidenciaService incidenciaService;
	@EJB
	private EstadoIncidenciaService estadoIncidenciaService;
	@EJB
	private UsuarioService usuarioService;
	private Incidencia incidencia;
	private String estadoIncidencia = "%";
	private List<Incidencia> listadoIncidencias;
	private List<Estadoincidencia> listadoEstado;
	private int slctnrpag = 5;
	private PaginacionHelper paginacion;
	Usuario u = new Usuario();
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	String username = ec.getRemoteUser();

	public VerIncidenciasBean() {
		// TODO Auto-generated constructor stub
	}

	/*****************************************************************************/
	@PostConstruct
	public void ini() {
		u = usuarioService.getUsuarioById(username).get(0);
		listadoEstado = estadoIncidenciaService.getAllEstadoIncidencia();
		if (paginacion == null) {
			paginacion = new PaginacionHelper(getSlctnrpag(), 0) {
				@Override
				public long getItemsCount() {
					return incidenciaService.getTotal(estadoIncidencia, username);
				}
			};
		}
		listadoIncidencias = incidenciaService.getIncidenciasPaginado(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), estadoIncidencia, username);
	}

	/******************************************************************************/

	public void eliminarIncidencia(long idincidencia) {
		try {
			incidenciaService.deleteIncidencia(idincidencia);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro borrado con éxito",
					"Registro borrado con éxito"));
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (Exception e) {
			String mensaje = e.getCause().getCause().getMessage();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
		}
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public Usuario getU() {
		return u;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setU(Usuario u) {
		this.u = u;
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

	public IncidenciaService getIncidenciaService() {
		return incidenciaService;
	}

	public EstadoIncidenciaService getEstadoIncidenciaService() {
		return estadoIncidenciaService;
	}

	public List<Estadoincidencia> getListadoEstado() {
		return listadoEstado;
	}

	public void setEstadoIncidenciaService(EstadoIncidenciaService estadoIncidenciaService) {
		this.estadoIncidenciaService = estadoIncidenciaService;
	}

	public void setListadoEstado(List<Estadoincidencia> listadoEstado) {
		this.listadoEstado = listadoEstado;
	}

	public String getEstadoIncidencia() {
		return estadoIncidencia;
	}

	public void setEstadoIncidencia(String estadoIncidencia) {
		this.estadoIncidencia = estadoIncidencia;
	}

	public void getEstadoIncidenciaFiltrada() {
		paginacion = null;
		ini();
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public List<Incidencia> getListadoIncidencias() {
		return listadoIncidencias;
	}

	public void setIncidenciaService(IncidenciaService incidenciaService) {
		this.incidenciaService = incidenciaService;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public void setListadoIncidencias(List<Incidencia> listadoIncidencias) {
		this.listadoIncidencias = listadoIncidencias;
	}

	public int getSlctnrpag() {
		return slctnrpag;
	}

	public void setSlctnrpag(int slctnrpag) {
		this.slctnrpag = slctnrpag;
	}

	public PaginacionHelper getPaginacion() {
		return paginacion;
	}

	public String editarIncidencia() {
		return "/user/editarincidencia.xhtml&faces-redirect=true";
	}

	public void setPaginacion(PaginacionHelper paginacion) {
		this.paginacion = paginacion;
	}

	/*************************************************************************/
	public int getTotalIncidenciasCandidatas() {
		return (listadoIncidencias != null) ? listadoIncidencias.size() : 0;
	}

	/***************************************************************************/
	public long getTotalIncidencias() {
		return incidenciaService.getTotal(estadoIncidencia, username);
	}

	/********************************************************************/
	public void paginaAnterior() {
		paginacion.getPaginaAnterior();
		listadoIncidencias = incidenciaService.getIncidenciasPaginado(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), estadoIncidencia, username);
	}

	/*********************************************************************/
	public void paginaSiguiente() {
		paginacion.getPaginaSiguiente();
		listadoIncidencias = incidenciaService.getIncidenciasPaginado(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), estadoIncidencia, username);
	}

	/************************************************************************/
	public void resetPaginacion() {
		/*
		 * Procedimiento que recalcula el número de página en función de como se sube y
		 * baja el numero de registros por página. A este procedimiento se le llama
		 * mediante peticion ajax asociada al cuadro combinado que permite seleccionar
		 * los registros por pagina. EL valor seleccionado esta asociado a la propiedad
		 * slctnrpag de nuestro backing bean.
		 */
		int nuevapagina = (paginacion.getPrimerElementoPagina() / slctnrpag);
		paginacion.setNrpag(slctnrpag);
		paginacion.setPagina(nuevapagina);
		listadoIncidencias = incidenciaService.getIncidenciasPaginado(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag(), estadoIncidencia, username);
	}
	/**************************************************************************/

}
