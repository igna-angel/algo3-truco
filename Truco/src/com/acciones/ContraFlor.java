package com.acciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;

public class ContraFlor extends Accion{

	public ContraFlor(Jugador origen, Jugador destino) {
		super(origen, destino, Accion.PUNTOS_CONTRA_FLOR);
		List<Accion> acciones = new ArrayList<Accion>();	
		acciones.add(new ContraFlorAlResto(origen, destino));
		this.setAccionesPosibles(acciones);
	}

	@Override
	public int getPuntosQueridos() {
		return this.getDecorada().getPuntosQueridos() + this._puntos;
	}
	
	public int getPuntosQueridos(Partido partido){
		return this.getDecorada().getPuntosQueridos(partido) * this._puntos;
	}

	@Override
	public int getPuntosQueridos(Partido partido, Equipo equipo) {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	@Override
	public int getPuntosNoQueridos() {
		return this.getDecorada().getPuntosQueridos() + this.getCantidadDecoradas();
	}

	@Override
	public String getID() {
		return Accion.ACCION_CONTRA_FLOR;
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
		partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(this.getOrigen()), this.getPuntosNoQueridos());
	}
}
