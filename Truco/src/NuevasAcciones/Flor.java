package NuevasAcciones;

import java.util.ArrayList;
import java.util.List;

import com.exceptions.NoSePuedeNoQuererException;
import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;

public class Flor extends Accion {

	public Flor(Jugador origen, Jugador destino) {
		super(origen, destino, Accion.PUNTOS_FLOR);
		this.setDecorada(new AccionDummy());
		List<Accion> acciones = new ArrayList<Accion>();		
		acciones.add(new ContraFlor(origen, destino));
		acciones.add(new ContraFlorAlResto(origen, destino));
		this.setAccionesPosibles(acciones);
	}
	
	@Override
	protected int getCantidadDecoradas() {
		return 0;
	}

	@Override
	public int getPuntosQueridos() {
		return this._puntos;
	}
	
	public int getPuntosQueridos(Partido partido){
		return (partido.getPrimerEquipo().getCantidadDeJugadoresConFlor() + partido.getUltimoEquipo().getCantidadDeJugadoresConFlor()) * this._puntos;
	}
	
	@Override
	public int getPuntosQueridos(Partido partido, Equipo equipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntosNoQueridos() {
		throw new NoSePuedeNoQuererException();
	}

	@Override
	public String getID() {
		return Accion.ACCION_FLOR;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		Jugador jugadorFlorMasAltaEquipoA = ronda.getJugadorConMayorFlorEnEquipo(partido.getPrimerEquipo());
		Jugador jugadorFlorMasAltaEquipoB = ronda.getJugadorConMayorFlorEnEquipo(partido.getUltimoEquipo());
		
		int florEquipoA = jugadorFlorMasAltaEquipoA.getFlorEnMano();
		int florEquipoB = jugadorFlorMasAltaEquipoB.getFlorEnMano();
				
		if(florEquipoA > florEquipoB){
			partido.agregarPuntosAlEquipo(partido.getPrimerEquipo(), this.getPuntosQueridos());
		} else if(florEquipoA == florEquipoB){
			Jugador jugadorMano = ronda.getJugadorManoEntre(jugadorFlorMasAltaEquipoA, jugadorFlorMasAltaEquipoB);
			partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(jugadorMano), this.getPuntosQueridos());
		}else{
			partido.agregarPuntosAlEquipo(partido.getUltimoEquipo(), this.getPuntosQueridos());
		}
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		throw new NoSePuedeNoQuererException();
	}
}
