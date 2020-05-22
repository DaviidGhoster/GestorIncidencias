package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name="\"ROLES\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long idRoles;

	@Column(name="DETALLE")
	private String detalleRoles;

	//bi-directional many-to-one association to Grupousuario
	@OneToMany(mappedBy="role")
	private List<Grupousuario> grupousuarios;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="roles")
	private List<Usuario> usuarios;

	public Role() {
	}

	public Long getIdRoles() {
		return this.idRoles;
	}

	public void setIdRoles(Long idRoles) {
		this.idRoles = idRoles;
	}

	public String getDetalleRoles() {
		return this.detalleRoles;
	}

	public void setDetalleRoles(String detalleRoles) {
		this.detalleRoles = detalleRoles;
	}

	public List<Grupousuario> getGrupousuarios() {
		return this.grupousuarios;
	}

	public void setGrupousuarios(List<Grupousuario> grupousuarios) {
		this.grupousuarios = grupousuarios;
	}

	public Grupousuario addGrupousuario(Grupousuario grupousuario) {
		getGrupousuarios().add(grupousuario);
		grupousuario.setRole(this);

		return grupousuario;
	}

	public Grupousuario removeGrupousuario(Grupousuario grupousuario) {
		getGrupousuarios().remove(grupousuario);
		grupousuario.setRole(null);

		return grupousuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}