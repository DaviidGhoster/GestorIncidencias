package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRIORIDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Prioridad.findAll", query="SELECT p FROM Prioridad p")
public class Prioridad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long idPrioridad;

	@Column(name="COMENTARIO")
	private String comentarioPrioridad;

	@Column(name="DESCRIPCION")
	private String descripcionPrioridad;

	//bi-directional many-to-one association to Incidencia
	@OneToMany(mappedBy="prioridadBean")
	private List<Incidencia> incidencias;

	public Prioridad() {
	}

	public Long getIdPrioridad() {
		return this.idPrioridad;
	}

	public void setIdPrioridad(Long idPrioridad) {
		this.idPrioridad = idPrioridad;
	}

	public String getComentarioPrioridad() {
		return this.comentarioPrioridad;
	}

	public void setComentarioPrioridad(String comentarioPrioridad) {
		this.comentarioPrioridad = comentarioPrioridad;
	}

	public String getDescripcionPrioridad() {
		return this.descripcionPrioridad;
	}

	public void setDescripcionPrioridad(String descripcionPrioridad) {
		this.descripcionPrioridad = descripcionPrioridad;
	}

	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public Incidencia addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setPrioridadBean(this);

		return incidencia;
	}

	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setPrioridadBean(null);

		return incidencia;
	}

}