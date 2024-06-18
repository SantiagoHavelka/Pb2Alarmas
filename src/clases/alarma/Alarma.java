package clases.alarma;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Alarma {

	private Integer id;
	private Integer codigoActivacionDesactivacion;
	private String codigoConfiguracion;
	private String nombre;
	private Boolean activada;
	private List<Usuario> usuariosValidos;
	private Set<Accion> acciones;
	private Set<Sensor> sensores;

	public Alarma(Integer idAlarma, Integer codigoActivacionDesactivacion, String codigoConfiguracion2, String nombre) {
		
		this.id = idAlarma;
		this.codigoActivacionDesactivacion = codigoActivacionDesactivacion;
		this.codigoConfiguracion = codigoConfiguracion2;
		this.nombre = nombre;
		this.usuariosValidos = new ArrayList<>();
		this.acciones = new TreeSet<>();
		this.sensores = new HashSet<>();
		this.activada = false;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(acciones, codigoActivacionDesactivacion, codigoConfiguracion, id, nombre, sensores,
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
				&& Objects.equals(codigoActivacionDesactivacion, other.codigoActivacionDesactivacion)
				&& Objects.equals(codigoConfiguracion, other.codigoConfiguracion) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(sensores, other.sensores)
				&& Objects.equals(usuariosValidos, other.usuariosValidos);
	}

	public Boolean getActivada() {
		return activada;
	}

	public void setActivada(Boolean activada) {
		this.activada = activada;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCodigoActivacionDesactivacion() {
		return codigoActivacionDesactivacion;
	}

	public String getCodigoConfiguracion() {
		return codigoConfiguracion;
	}

	public List<Usuario> getUsuariosValidos() {
		return usuariosValidos;
	}

	public Set<Accion> getAcciones() {
		return acciones;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public Usuario obtenerUsuarioValido(Usuario usuarioConfig) {
		Usuario valido = null;
		for(Usuario validos : this.usuariosValidos) {
			if(validos.equals(usuarioConfig)) {
				valido = validos;
			}
		}
		return valido;
	}

	public Sensor obtenerSensor(Sensor sensor) {
		Sensor buscado = null;
		for(Sensor sens: this.sensores) {
			if(sens.equals(sensor)) {
				buscado = sens;
			}
		}
		return buscado;
	}
	

}
