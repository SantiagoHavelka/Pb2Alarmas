package test.alarma;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import clases.alarma.Accion;
import clases.alarma.Alarma;
import clases.alarma.CentroSeguridad;
import clases.alarma.CodigoAlarmaIncorrectoException;
import clases.alarma.Sensor;
import clases.alarma.SensorDesactivadoException;
import clases.alarma.SensorDuplicadoException;
import clases.alarma.TipoDeOperacion;
import clases.alarma.Usuario;
import clases.alarma.UsuarioConfigurador;

public class AlarmaTest {

	private static final String NOMBRE_CENTRO_SEGURIDAD = "Security Center";
	CentroSeguridad centroDeSeguridad;
	
	@Before
	public void init() {
		this.centroDeSeguridad = new CentroSeguridad(NOMBRE_CENTRO_SEGURIDAD);
	}
	
	@Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() {
		Integer idAlarma = 22;
		Integer codigoActivacionDesactivacion = 224455;
		String codigoConfiguracion = "ccc";
		String nombre = "Alarma Sensorial";
		
		Alarma alarmaDeCentral = new Alarma(idAlarma,codigoActivacionDesactivacion, codigoConfiguracion, nombre);
		Boolean sePudoRegistrar = this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		assertTrue(sePudoRegistrar);
	}
	@Test
	public void queSePuedaRegistrarUnUsuarioConfiguradorYUnoActivadorALaCentral() {
		Integer dni = 4255567;
		String nombre = "Lucas";
		Integer dni2= 22444567;
	    String nombre2= "Juan";
	    
		Usuario usuarioConfig = new UsuarioConfigurador(dni, nombre);
		Boolean sePuedeRegistrarConfigurador = this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		Usuario usuarioActivador = new UsuarioConfigurador(dni2, nombre2);
		Boolean sePuedeRegistrarActivador = this.centroDeSeguridad.registrarUsuario(usuarioActivador);
		
		assertTrue(sePuedeRegistrarConfigurador);
		assertTrue(sePuedeRegistrarActivador);
		
		
	}
	@Test
	public void queSePuedaAgregarUnUsuarioALaListaDeUsuariosValidosDeUnaAlarma() throws CodigoAlarmaIncorrectoException {
		Integer idAlarma = 22;
		Integer codigoActivacionDesactivacion = 224455;
		String codigoConfiguracion = "ccc";
		String nombreAlarma = "Alarma Sensorial";
		
		Alarma alarmaDeCentral = new Alarma(idAlarma,codigoActivacionDesactivacion, codigoConfiguracion, nombreAlarma);
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Integer dni = 4255567;
		String nombreUsuario = "Lucas";
		Usuario usuarioConfig = new UsuarioConfigurador(dni, nombreUsuario);
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		Boolean sePuedeAgregar = this.centroDeSeguridad.agregarUsuarioAUnaAlarma(dni,idAlarma, codigoConfiguracion);
		Usuario valido = alarmaDeCentral.obtenerUsuarioValido(usuarioConfig);
		
		assertTrue(sePuedeAgregar);
		assertEquals(usuarioConfig, valido);
		
		
		
	}
	@Test (expected = CodigoAlarmaIncorrectoException.class)
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException() throws CodigoAlarmaIncorrectoException {
		Integer idAlarma = 22;
		Integer codigoActivacionDesactivacion = 224455;
		String codigoConfiguracion = "ccc";
		String nombreAlarma = "Alarma Sensorial";
		
		Alarma alarmaDeCentral = new Alarma(idAlarma,codigoActivacionDesactivacion, codigoConfiguracion, nombreAlarma);
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Integer dni = 4255567;
		String nombreUsuario = "Lucas";
		Usuario usuarioConfig = new UsuarioConfigurador(dni, nombreUsuario);
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		Boolean sePuedeAgregar = this.centroDeSeguridad.agregarUsuarioAUnaAlarma(dni,idAlarma, "ggh");
		
		assertTrue(sePuedeAgregar);
	}
	@Test
	public void queSePuedaAgregarUnSensorAUnaAlarma() throws CodigoAlarmaIncorrectoException, SensorDuplicadoException {
		
		Alarma alarmaDeCentral = new Alarma(44 ,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,44, "ccc");
		Sensor sensor = new Sensor(66, false);
		
		Boolean sePuedeAgregar = this.centroDeSeguridad.agregarSensorAAlarma(44 ,"ccc",sensor, 425567);
		
		Sensor deAlarma = alarmaDeCentral.obtenerSensor(sensor);
		
		assertTrue(sePuedeAgregar);
		assertEquals(sensor, deAlarma);
	}
	@Test (expected = SensorDuplicadoException.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException() throws CodigoAlarmaIncorrectoException, SensorDuplicadoException {
		Alarma alarmaDeCentral = new Alarma(55,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,55, "ccc");
		
		Sensor sensor = new Sensor(22, false);
		
	     this.centroDeSeguridad.agregarSensorAAlarma(55, "ccc",sensor, 425567);
	     
	     this.centroDeSeguridad.agregarSensorAAlarma(55, "ccc",sensor, 425567);
	    
	}
	@Test
	public void queSePuedaActivarElSensorDeUnaAlarma() throws CodigoAlarmaIncorrectoException, SensorDuplicadoException {
		Alarma alarmaDeCentral = new Alarma(44,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,44, "ccc");
		
		Sensor sensor = new Sensor(22, false);
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor, 425567);
	     
	     Boolean sePuedeActivar = this.centroDeSeguridad.activarSensorDeAlarma(22, 44, 224455);
	     assertTrue(sePuedeActivar);
	     
	     Boolean estadoSensor =  sensor.isActivado();
	     assertEquals(estadoSensor, true);
	}
	@Test
	public void queSePuedaActivarUnaAlarma() throws CodigoAlarmaIncorrectoException, SensorDuplicadoException, SensorDesactivadoException {
		Alarma alarmaDeCentral = new Alarma(44,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,44, "ccc");
		
		Sensor sensor = new Sensor(22, false);
		Sensor sensor2 = new Sensor(57, false);
		Sensor sensor3 = new Sensor(87, false);
		
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor, 425567);
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor2, 425567);
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor3, 425567);
	        
	     this.centroDeSeguridad.activarSensorDeAlarma(22, 44, 224455);
	     this.centroDeSeguridad.activarSensorDeAlarma(57, 44, 224455);
	     this.centroDeSeguridad.activarSensorDeAlarma(87, 44, 224455);
	    
	     
	     
	     Boolean sePuedeActivarLaAlarma = this.centroDeSeguridad.activarDesactivarAlarma(44, "ccc", usuarioConfig);
	     
	     Boolean estadoDeAlarma = alarmaDeCentral.getActivada();
	     
	     assertTrue(sePuedeActivarLaAlarma);
	     assertEquals(estadoDeAlarma, true);
	}
	@Test (expected = SensorDesactivadoException.class)
	public void queNoSePuedaActivarUnaAlarmaSiHayAlmenosUnSensorDesactivado() throws CodigoAlarmaIncorrectoException, SensorDuplicadoException, SensorDesactivadoException {
		Alarma alarmaDeCentral = new Alarma(44,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,44, "ccc");
		
		Sensor sensor = new Sensor(22, false);
		Sensor sensor2 = new Sensor(57, false);
		Sensor sensor3 = new Sensor(87, false);
		
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor, 425567);
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor2, 425567);
	     this.centroDeSeguridad.agregarSensorAAlarma(44, "ccc",sensor3, 425567);
	        
	     this.centroDeSeguridad.activarSensorDeAlarma(22, 44, 224455);
	     this.centroDeSeguridad.activarSensorDeAlarma(55, 44, 224455);
	     this.centroDeSeguridad.activarSensorDeAlarma(87, 44, 224455);
	    
	     this.centroDeSeguridad.activarDesactivarAlarma(44, "ccc", usuarioConfig);
	     
	}
	@Test
	public void queSePuedaAccionarSobreUnaAlarma() throws CodigoAlarmaIncorrectoException {
		Alarma alarmaDeCentral = new Alarma(44,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,44, "ccc");
		
		Integer idAccion = 22;
		LocalDate fecha = LocalDate.of(2024, 6, 23);
		TipoDeOperacion tipoOperacion = TipoDeOperacion.ACTIVAR;
		
		Accion accionEnAlarma = new Accion(idAccion, alarmaDeCentral, usuarioConfig, fecha, tipoOperacion);
		Boolean sePuedeAgregarAccionEnLaAlarma = this.centroDeSeguridad.accionarEnAlarma(accionEnAlarma);
		
		assertTrue(sePuedeAgregarAccionEnLaAlarma);	
		
	}
	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAccionesDeTipoConfiguracionOrdenadPorIdDeAccion() throws CodigoAlarmaIncorrectoException {
		Alarma alarmaDeCentral = new Alarma(44,224455, "ccc", "Alarma Sensorial");
		this.centroDeSeguridad.registrarAlarma(alarmaDeCentral);
		
		Usuario usuarioConfig = new UsuarioConfigurador(425567, "Lucas");
		this.centroDeSeguridad.registrarUsuario(usuarioConfig);
		
		this.centroDeSeguridad.agregarUsuarioAUnaAlarma(425567,44, "ccc");
		
		Integer idAccion = 22;
		LocalDate fecha = LocalDate.of(2024, 6, 23);
		TipoDeOperacion tipoOperacion = TipoDeOperacion.CONFIGURAR;
		
		Integer idAccion2 = 44;
		LocalDate fecha2 = LocalDate.of(2024, 6, 28);
		TipoDeOperacion tipoOperacion2 = TipoDeOperacion.CONFIGURAR;
		
		
		Integer idAccion3 = 12;
		LocalDate fecha3 = LocalDate.of(2024, 7, 11);
		TipoDeOperacion tipoOperacion3 = TipoDeOperacion.CONFIGURAR;
		
		
		Accion accionEnAlarma = new Accion(idAccion, alarmaDeCentral, usuarioConfig, fecha, tipoOperacion);
		Accion accionEnAlarma2 = new Accion(idAccion2, alarmaDeCentral, usuarioConfig, fecha2, tipoOperacion2);
		Accion accionEnAlarma3 = new Accion(idAccion3, alarmaDeCentral, usuarioConfig, fecha3, tipoOperacion3);
		
		 this.centroDeSeguridad.accionarEnAlarma(accionEnAlarma);
		 this.centroDeSeguridad.accionarEnAlarma(accionEnAlarma2);
		 this.centroDeSeguridad.accionarEnAlarma(accionEnAlarma3);
		 
		 TreeSet<Accion>acciones = this.centroDeSeguridad.obtenerAcciones(alarmaDeCentral);
		 
		 assertEquals(acciones.first(),accionEnAlarma3 );
		 assertEquals(acciones.last(),accionEnAlarma2 );
		
	}

}
