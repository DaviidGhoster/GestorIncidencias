package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Departamento;

/**
 * Session Bean implementation class DepartamentoService
 */
@Stateless
@LocalBean
public class DepartamentoService {

	@PersistenceContext(unitName = "GestorIncidencia")
	private EntityManager em;

	public DepartamentoService() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Departamento> getDepartamentoById(int id) {
		return em.createQuery("Select d from Departamento d where d.idDepartamento=:id").setParameter("id", id)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Departamento> getDepartamentos() {
		return em.createNamedQuery("Departamento.findAll").getResultList();
	}
}
