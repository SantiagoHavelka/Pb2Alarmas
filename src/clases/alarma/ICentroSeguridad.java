package clases.alarma;

import java.util.TreeSet;

public interface ICentroSeguridad {
	public Boolean registrarAlarma(Alarma alarmaDeCentral);
	public Boolean registrarUsuario(Usuario usuario);
	public Boolean agregarUsuarioAUnaAlarma(Integer dni, Integer idAlarma, String codigoConfiguracion) throws CodigoAlarmaIncorrectoException;
	public Boolean agregarSensorAAlarma(Integer idAlarma, String codigoConfigAlarma, Sensor sensor, Integer dniUsuarioConfigurador) throws SensorDuplicadoException;
	public Boolean activarSensorDeAlarma(Integer idSensor, Integer idAlarma, Integer codigoActivacion);
	public Boolean activarDesactivarAlarma(Integer idAlarma, String codigoConfigAlarma, Usuario usuario) throws SensorDesactivadoException;
	public Boolean accionarEnAlarma(Accion accionEnAlarma);
	public TreeSet<Accion> obtenerAcciones(Alarma alarmaDeCentral);
}
