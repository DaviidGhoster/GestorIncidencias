package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Role;

/**
 * Session Bean implementation class RolesService
 */
@Stateless
@LocalBean
public class RolesService {

	@PersistenceContext(unitName = "GestorIncidencia")
	private EntityManager em;
    public RolesService() {
        // TODO Auto-generated constructor stub
    }
    @SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		return em.createNamedQuery("Role.findAll").getResultList();
	}
    @SuppressWarnings("unchecked")
	public List<Role> getRolById(int id) {
		return em.createQuery("Select r from Role r where r.idRoles=:id").setParameter("id", id)
				.getResultList();
	}
}
