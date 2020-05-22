package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import entidades.Usuario;
import excepciones.UsuarioException;

/**
 * Session Bean implementation class UsuarioService
 */
@Stateless
@LocalBean
public class UsuarioService {

	@PersistenceContext(unitName="GestorIncidencia")
	private EntityManager em;
    public UsuarioService() {
        // TODO Auto-generated constructor stub
    }
    
    public void registrarUsuario(Usuario u) throws RollbackException, UsuarioException{
    	if(em.find(Usuario.class, u.getEmail())!=null) {
    		throw new UsuarioException("El usuario ya existe");
    	}
    	if (u.getRoles().get(0).getIdRoles()==3 && u.getDepartamentoBean()==null) {
    		throw new UsuarioException("Debe de seleccionar un departamento para el rol de técnico");
    	}
    	try {
    		em.persist(u);
		}catch (RollbackException rbe) {
			throw rbe;
		}
    }
    @SuppressWarnings("unchecked")
	public List<Usuario> getUsuarioById(String email) {
    	return em.createQuery("Select u from Usuario u where u.email=:email").setParameter("email", email).getResultList();
    }
    public void updatePassword(String password, String username) {
    	Usuario u=em.find(Usuario.class, username);
    	u.setPassword(password);
    	try {
    		em.merge(u);
    	}catch(RollbackException rbe) {
    		throw rbe;
    	}
    }
}
