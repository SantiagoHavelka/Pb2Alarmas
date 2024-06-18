package clases.alarma;

import java.time.LocalDate;
import java.util.Objects;

public class Accion implements Comparable<Accion>{

	private Integer id;
	private Alarma alarma;
	private Usuario usuario;
	private LocalDate fecha;
	private TipoDeOperacion tipoOperacion;

	public Accion(Integer idAccion, Alarma alarmaDeCentral, Usuario usuario, LocalDate fecha,
			TipoDeOperacion tipoOperacion) {
		this.id = idAccion;
		this.alarma = alarmaDeCentral;
		this.usuario = usuario;
		this.fecha = fecha;
		this.tipoOperacion = tipoOperacion;
	}

	public Integer getId() {
		return id;
	}

	public Alarma getAlarma() {
		return alarma;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alarma, fecha, id, tipoOperacion, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accion other = (Accion) obj;
		return Objects.equals(alarma, other.alarma) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && tipoOperacion == other.tipoOperacion
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public int compareTo(Accion o) {
		return this.id.compareTo(o.getId());
	}
	

}
