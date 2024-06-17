package clases.alarma;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Alarma {

	private Integer id;
	private Integer codicoActivacionDesactivacion;
	private Integer codigoConfiguracion;
	private String nombre;
	private List<Usuario> usuariosValidos;
	private Set<Accion> acciones;
	private Set<Sensor> sensores;

	public Alarma(Integer idAlarma, Integer codigoActivacionDesactivacion, Integer codigoConfiguracion, String nombre,
			List<Usuario> usuariosValidos, Set<Accion> acciones, Set<Sensor> sensores) {
		
		this.id = idAlarma;
		this.codicoActivacionDesactivacion = codigoActivacionDesactivacion;
		this.codigoConfiguracion = codigoConfiguracion;
		this.nombre = nombre;
		this.usuariosValidos = usuariosValidos;
		this.acciones = acciones;
		this.sensores = sensores;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(acciones, codicoActivacionDesactivacion, codigoConfiguracion, id, nombre, sensores,
				usuariosValidos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		return Objects.equals(acciones, other.acciones)
				&& Objects.equals(codicoActivacionDesactivacion, other.codicoActivacionDesactivacion)
				&& Objects.equals(codigoConfiguracion, other.codigoConfiguracion) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(sensores, other.sensores)
				&& Objects.equals(usuariosValidos, other.usuariosValidos);
	}
	

}
