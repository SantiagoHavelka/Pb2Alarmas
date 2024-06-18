package clases.alarma;

import java.util.List;
import java.util.ArrayList;

public class CentroSeguridad {

	private String nombre;
	private List<Alarma>alarmas;
	private List<Usuario> usuarios;

	public CentroSeguridad(String nombreCentroSeguridad) {
		this.nombre = nombreCentroSeguridad;
		this.alarmas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}

	public Boolean registrarAlarma(Alarma alarmaDeCentral) {
		return this.alarmas.add(alarmaDeCentral);
		
	}
	public Boolean registrarUsuario(Usuario usuario) {
		return this.usuarios.add(usuario);
	}

	public Boolean agregarUsuarioAUnaAlarma(Integer dni, Integer idAlarma, String codigoConfiguracion) throws CodigoAlarmaIncorrectoException {
		for(Usuario user : this.usuarios) {
			if(user.getDni().equals(dni)) {
				for(Alarma alarm : this.alarmas) {
					if(alarm.getId().equals(idAlarma) && alarm.getCodigoConfiguracion().equals(codigoConfiguracion)) {
						alarm.getUsuariosValidos().add(user);
						return true;
					}
				}
			}
		}
		throw new CodigoAlarmaIncorrectoException("El codigo de configuracion es incorrecto");
		
	}

	public Boolean agregarSensorAAlarma(Integer idAlarma, String codigoConfigAlarma, Sensor sensor, Integer dniUsuarioConfigurador) throws SensorDuplicadoException {
		for(Alarma alarm: this.alarmas) {
			if(alarm.getId().equals(idAlarma) && alarm.getCodigoConfiguracion().equals(codigoConfigAlarma)) {
				for(Usuario user: alarm.getUsuariosValidos()){
					if(user.getDni().equals(dniUsuarioConfigurador)&& !alarm.getSensores().contains(sensor) ) {
						return alarm.getSensores().add(sensor);		
							}
						}
					}
						}
						
		throw new SensorDuplicadoException("El sensor que quiere agregar ya se encuentra en la alarma");
	}

	public Boolean activarSensorDeAlarma(Integer idSensor, Integer idAlarma, Integer codigoActivacion) {
		for(Alarma alarm: this.alarmas) {
			if(alarm.getId().equals(idAlarma) && alarm.getCodigoActivacionDesactivacion().equals(codigoActivacion)) {
				for(Sensor sens : alarm.getSensores()) {
					if(sens.getId().equals(idSensor) && !sens.isActivado()) {
						sens.setEstado(true);
						return true;
					}
				}
			}
		}
		return false;
	}

	public Boolean activarDesactivarAlarma(Integer idAlarma, String codigoConfigAlarma, Usuario usuario) throws SensorDesactivadoException {
		for(Alarma al : this.alarmas) {
			if(al.getId().equals(idAlarma) && al.getCodigoConfiguracion().equals(codigoConfigAlarma)) {
				for(Usuario u : this.usuarios) {
					if(u.equals(usuario)) {
						for(Sensor s : al.getSensores()) {
							if(!s.isActivado()) {
								al.setActivada(false);
								throw new SensorDesactivadoException("Uno o varios sensores estan desactivados");
							}else {
								al.setActivada(true);
							}
					}
				}
			}
		}
	}
		
		return true;
	}
}

