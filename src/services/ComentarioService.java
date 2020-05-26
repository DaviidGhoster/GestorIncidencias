package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import entidades.Comentario;

/**
 * Session Bean implementation class ComentarioService
 */
@Stateless
@LocalBean
public class ComentarioService {

	@PersistenceContext(unitName = "GestorIncidencia")
	private EntityManager em;

	public ComentarioService() {
		// TODO Auto-generated constructor stub
	}

	public long getUltimoComentario() {
		Query consulta1 = em.createNamedQuery("Comentario.findAllOrdenado");
		Comentario i = (Comentario) consulta1.getResultList().get(0);
		return i.getIdcomentario();
	}

	@SuppressWarnings("unchecked")
	public List<Comentario> getComentarioById(String id) {
		Query consulta1 = em.createNamedQuery("Comentario.findComentario");
		List<Comentario> i = consulta1.setParameter("id", id).getResultList();
		return i;
	}

	public void newComentario(Comentario c) {
		try {

			em.persist(c);
		} catch (RollbackException e) {
			// TODO: handle exception
		}

	}
}