package test.alarma;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import clases.alarma.Accion;
import clases.alarma.Alarma;
import clases.alarma.CentroSeguridad;
import clases.alarma.Sensor;
import clases.alarma.Usuario;

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
		Integer codigoConfiguracion = 333;
		String nombre = "Alarma Sensorial";
		List<Usuario>usuariosValidos = new ArrayList<>();
		Set<Accion>accionesRealizadas = new TreeSet<>();
		Set<Sensor>sensores = new HashSet<>();
		
		Alarma alarmaDeCentral = new Alarma(idAlarma,codigoActivacionDesactivacion, codigoConfiguracion, nombre, usuariosValidos,accionesRealizadas, sensores );
	}
	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma() {
		
	}
	@Test
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException() {
		
	}
	@Test
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException() {
		
	}
	@Test
	public void queNoSePuedaActivarUnaAlarmaSiHayAlmenosUnSensorDesactivado() {
		
	}
	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAccionesDeTipoConfiguracionOrdenadPorIdDeAccion() {
		
	}

}
