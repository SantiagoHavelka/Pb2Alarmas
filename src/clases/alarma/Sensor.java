package clases.alarma;

import java.util.Objects;

public class Sensor {

	private Integer id;
	private Boolean estado;

	public Sensor(Integer id, Boolean activado) {
		this.id = id;
		this.estado = activado;
	}

	public Boolean isActivado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(estado, other.estado) && Objects.equals(id, other.id);
	}

}
