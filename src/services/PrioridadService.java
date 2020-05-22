package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Prioridad;

/**
 * Session Bean implementation class PrioridadService
 */
@Stateless
@LocalBean
public class PrioridadService {

	@PersistenceContext(unitName="GestorIncidencia")
	private EntityManager em;
    public PrioridadService() {
        // TODO Auto-generated constructor stub
    }
    @SuppressWarnings("unchecked")
	public List<Prioridad> getPrioridad(){
    	return em.createNamedQuery("Prioridad.findAll").getResultList();
    }
}
