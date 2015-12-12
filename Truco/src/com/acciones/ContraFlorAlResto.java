package com.acciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public class ContraFlorAlResto extends Accion{
	
	public ContraFlorAlResto(Jugador origen, Jugador destino) {
		super(origen, destino);
		List<Accion> acciones = new ArrayList<Accion>();
		this.setAccionesPosibles(acciones);
	}

	@Override
	public int getPuntosQueridos() {
		return 0;
	}
	
	public int getPuntosQueridos(Partido partido){
		return 0;
	}

	@Override
	public int getPuntosQueridos(Partido partido, Equipo equipo) {
		return partido.getPuntosFaltantesDeEquipo(equipo);
	}	
	
	@Override
	public int getPuntosNoQueridos() {
		return this.getDecorada().getPuntosQueridos() + this.getCantidadDecoradas();
	}

	@Override
	public String getID() {
		return Accion.ACCION_CONTRA_FLOR_AL_RESTO;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		Equipo equipoA = partido.getPrimerEquipo();
		Equipo equipoB = partido.getUltimoEquipo();
		
		Jugador jugadorFlorMasAltaEquipoA = ronda.getJugadorConMayorFlorEnEquipo(equipoA);
		Jugador jugadorFlorMasAltaEquipoB = ronda.getJugadorConMayorFlorEnEquipo(equipoB);
		
		int florEquipoA = jugadorFlorMasAltaEquipoA.getFlorEnMano();
		int florEquipoB = jugadorFlorMasAltaEquipoB.getFlorEnMano();
				
		if(florEquipoA > florEquipoB){
			partido.agregarPuntosAlEquipo(equipoA, this.getPuntosQueridos(partido, equipoA));
		} else if(florEquipoA == florEquipoB){
			Jugador jugadorMano = ronda.getJugadorManoEntre(jugadorFlorMasAltaEquipoA, jugadorFlorMasAltaEquipoB);
			partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(jugadorMano), this.getPuntosQueridos(partido, partido.getEquipoDeJugador(jugadorMano)));
		}else{
			partido.agregarPuntosAlEquipo(equipoB, this.getPuntosQueridos(partido, equipoB));
		}
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(this.getOrigen()), this.getPuntosNoQueridos());
	}

	@Override
	public void limpiarAccionesRelacionadasEnVuelta(Vuelta vuelta) {
		List<Accion> nuevasAccionesVuelta = new ArrayList<Accion>();
		
		this.getDecorada().limpiarAccionesRelacionadasEnVuelta(vuelta);
		
		for(Accion accion : vuelta.getAccionesDeVuelta()){
			if(accion.getEstado().getID().equals(ESTADO_INDEFINIDO)){
				if(!accion.getID().equals(Accion.ACCION_REAL_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_ENVIDO_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FALTA_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FLOR) &&
					!accion.getID().equals(Accion.ACCION_CONTRA_FLOR) &&
					!accion.getID().equals(Accion.ACCION_ENVIDO)){
						nuevasAccionesVuelta.add(accion);
				}
			}else{
				nuevasAccionesVuelta.add(accion);
			}
		}
		
		vuelta.setAccionesDeVuelta(nuevasAccionesVuelta);
	}
}
