package NuevasAcciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;

public class FaltaEnvido extends Accion{

	public FaltaEnvido(Jugador origen, Jugador destino) {
		super(origen, destino, Accion.PUNTOS_REAL_ENVIDO);
		this.setDecorada(new AccionDummy());
		List<Accion> acciones = new ArrayList<Accion>();
		this.setAccionesPosibles(acciones);
	}

	@Override
	public int getPuntosQueridos() {
		return 0;
	}

	@Override
	public int getPuntosNoQueridos() {
		return this.getDecorada().getPuntosNoQueridos() + Accion.PUNTOS_NO_QUERIDO;
	}
	
	public int getPuntosQueridos(Partido partido, Equipo equipo){
		return partido.getPuntosFaltantesDeEquipo(equipo);
	}

	@Override
	public String getID() {
		return Accion.ACCION_FALTA_ENVIDO;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		Equipo equipoA = partido.getEquipos().getFirst();
		Equipo equipoB = partido.getEquipos().getLast();
		
		int tantoEquipoA = ronda.getMayorTantoDeEquipo(equipoA);
		int tantoEquipoB = ronda.getMayorTantoDeEquipo(equipoB);
				
		if(tantoEquipoA > tantoEquipoB){
			partido.agregarPuntosAlEquipo(equipoA, this.getPuntosQueridos(partido, equipoA));
		} else {
			partido.agregarPuntosAlEquipo(equipoB, this.getPuntosQueridos(partido, equipoA));
		}
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(this.getOrigen()), this.getPuntosNoQueridos());
	}
}
