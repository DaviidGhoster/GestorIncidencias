package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import entidades.Comentario;
import entidades.Estadoincidencia;
import entidades.Incidencia;
import entidades.Prioridad;
import entidades.Usuario;

/**
 * Session Bean implementation class IncidenciaService
 */
@Stateless
@LocalBean
public class IncidenciaService {

	@PersistenceContext(unitName = "GestorIncidencia")
	private EntityManager em;

	public IncidenciaService() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Incidencia> getIncidenciasPaginado(int primerResultado, int maxResultados, String tipo, String User) {
		Usuario u = em.find(Usuario.class, User);

		List<Incidencia> listaIncidencia = new ArrayList<Incidencia>();
		Query consulta1 = em.createNamedQuery("Incidencia.findByTipo");
		listaIncidencia = consulta1.setParameter("tipo", tipo).setFirstResult(primerResultado)
				.setMaxResults(maxResultados).getResultList();
		if (u.getGrupousuario().getId().getIdrol() == 4) {
			System.out.println(4);
			listaIncidencia.clear();
			Query consulta = em.createNamedQuery("Incidencia.findByUser");
			listaIncidencia = consulta.setParameter("email", User).setParameter("tipo", tipo)
					.setFirstResult(primerResultado).setMaxResults(maxResultados).getResultList();
			return listaIncidencia;
		} else if (u.getGrupousuario().getId().getIdrol() == 3) {

			String dep = u.getDepartamentoBean().getDetalleDepartamento();
			System.out.println(3);
			listaIncidencia.clear();
			Query consulta = em.createNamedQuery("Incidencia.findByDepartamento");
			listaIncidencia = consulta.setParameter("email", User).setParameter("tipo", tipo)
					.setParameter("departamento", dep).setFirstResult(primerResultado).setMaxResults(maxResultados)
					.getResultList();
			return listaIncidencia;
		}

		return listaIncidencia;

	}

	@SuppressWarnings("unchecked")
	public List<Incidencia> allListadoIncidencias(int primerResultado, int maxResultados, String tipo) {

		Query consulta1 = em.createNamedQuery("Incidencia.findByTipo");
		List<Incidencia> listaIncidencia = consulta1.setParameter("tipo", tipo).setFirstResult(1)
				.setMaxResults(maxResultados).getResultList();
		return listaIncidencia;
	}

	public long getTotal() {
		Query consulta = em.createQuery("select count(i) from Incidencia i");
		return (Long) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public long getTotal(String tipo, String User) {
		Usuario u = em.find(Usuario.class, User);

		List<Incidencia> listaIncidencia = new ArrayList<Incidencia>();
		Query consulta1 = em.createNamedQuery("Incidencia.findByTipo");
		listaIncidencia = consulta1.setParameter("tipo", tipo).getResultList();
		if (u.getGrupousuario().getId().getIdrol() == 4) {
			System.out.println(4);
			listaIncidencia.clear();
			Query consulta = em.createNamedQuery("Incidencia.findByUser");
			listaIncidencia = consulta.setParameter("email", User).setParameter("tipo", tipo).getResultList();
			return listaIncidencia.size();
		} else if (u.getGrupousuario().getId().getIdrol() == 3) {

			String dep = u.getDepartamentoBean().getDetalleDepartamento();
			System.out.println(3);
			listaIncidencia.clear();
			Query consulta = em.createNamedQuery("Incidencia.findByDepartamento");
			listaIncidencia = consulta.setParameter("email", User).setParameter("tipo", tipo)
					.setParameter("departamento", dep).getResultList();
			return listaIncidencia.size();
		}
		return listaIncidencia.size();
	}

	@SuppressWarnings("unchecked")
	public List<Incidencia> getLastIncidencia() {
		return em.createQuery("Select i from Incidencia i order by i.idIncidencia desc").getResultList();
	}

	public void insertIncidencia(Incidencia i, String username, Long Prioridad, String Detalle, Comentario comentario)
			throws RollbackException {
		Usuario u = em.find(Usuario.class, username);
		Prioridad p = em.find(Prioridad.class, Prioridad);
		Estadoincidencia estado = em.find(Estadoincidencia.class, 1L);
		i.setIdIncidencia(getLastIncidencia().get(0).getIdIncidencia() + 1);
		i.setUsuarioBean(u);
		i.setDetalleIncidencia(Detalle);
		i.setFechaIncidencia(new Date());
		i.setPrioridadBean(p);
		i.setDepartamento(null);
		i.setEstadoincidencia(estado);
		try {
			em.persist(i);
			em.persist(comentario);
		} catch (RollbackException e) {
			throw e;
		}

	}

	public void deleteIncidencia(long i) {
		Incidencia inc=em.find(Incidencia.class, i);
		try {
			em.remove(inc);
		} catch (RollbackException e) {
			throw e;
		}
	}

	public void actualizarIncidencia(Incidencia i) throws RollbackException {
		try {
			em.merge(i);
		} catch (EJBException e) {
			throw e;
		}
	}
}
