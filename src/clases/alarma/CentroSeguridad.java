package clases.alarma;

import java.util.List;
import java.util.ArrayList;

public class CentroSeguridad {

	private String nombre;
	private List<Usuario>usuarios;
	
	public CentroSeguridad(String nombreCentroSeguridad) {
		this.nombre = nombreCentroSeguridad;
		this.usuarios = new ArrayList<>();
	}

	public Boolean agregarUsuario(Usuario usuario) {
		return this.usuarios.add(usuario);
	}
	
	

}
