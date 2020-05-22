package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GRUPOUSUARIOS database table.
 * 
 */
@Entity
@Table(name="GRUPOUSUARIOS")
@NamedQuery(name="Grupousuario.findAll", query="SELECT g FROM Grupousuario g")
public class Grupousuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupousuarioPK id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="IDROL")
	private Role role;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	public Grupousuario() {
	}

	public GrupousuarioPK getId() {
		return this.id;
	}

	public void setId(GrupousuarioPK id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}