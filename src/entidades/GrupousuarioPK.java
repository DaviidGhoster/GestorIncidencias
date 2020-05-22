package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the GRUPOUSUARIOS database table.
 * 
 */
@Embeddable
public class GrupousuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long idrol;

	@Column(insertable=false, updatable=false)
	private String idusuario;

	public GrupousuarioPK() {
	}
	public long getIdrol() {
		return this.idrol;
	}
	public void setIdrol(long idrol) {
		this.idrol = idrol;
	}
	public String getIdusuario() {
		return this.idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupousuarioPK)) {
			return false;
		}
		GrupousuarioPK castOther = (GrupousuarioPK)other;
		return 
			(this.idrol == castOther.idrol)
			&& this.idusuario.equals(castOther.idusuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idrol ^ (this.idrol >>> 32)));
		hash = hash * prime + this.idusuario.hashCode();
		
		return hash;
	}
}