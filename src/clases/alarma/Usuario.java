package clases.alarma;

import java.util.Objects;

public class Usuario {

	private Integer dni;
	private String nombre;

	public Usuario(Integer dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre);
	}

	public Integer getDni() {
		return dni;
	}

}
