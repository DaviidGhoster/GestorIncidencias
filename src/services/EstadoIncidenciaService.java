package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Estadoincidencia;

/**
 * Session Bean implementation class EstadoIncidenciaService
 */
@Stateless
@LocalBean
public class EstadoIncidenciaService {

	@PersistenceContext(unitName = "GestorIncidencia")
	private EntityManager em;

	public EstadoIncidenciaService() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Estadoincidencia> getAllEstadoIncidencia() {
		return em.createNamedQuery("Estadoincidencia.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estadoincidencia> getEstado(String id) {
		return em.createNamedQuery("Estadoincidencia.findById").setParameter("id", id).getResultList();
	}
}
