package com.acciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public class FaltaEnvido extends Accion{

	public FaltaEnvido(Jugador origen, Jugador destino) {
		super(origen, destino);
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
	
	@Override
	public int getPuntosQueridos(Partido partido) {
		// TODO Auto-generated method stub
		return 0;
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
		Equipo equipoA = partido.getPrimerEquipo();
		Equipo equipoB = partido.getUltimoEquipo();
		
		Jugador jugadorTantoMasAltoEquipoA = ronda.getJugadorConMayorTantoEnEquipo(equipoA);
		Jugador jugadorTantoMasAltoEquipoB = ronda.getJugadorConMayorTantoEnEquipo(equipoB);
		
		int tantoEquipoA = jugadorTantoMasAltoEquipoA.getTantoEnMano();
		int tantoEquipoB = jugadorTantoMasAltoEquipoB.getTantoEnMano();
			
		if(tantoEquipoA > tantoEquipoB){
			partido.agregarPuntosAlEquipo(equipoA, this.getPuntosQueridos(partido, equipoA));
		} else if(tantoEquipoA == tantoEquipoB){
			Jugador jugadorMano = ronda.getJugadorManoEntre(jugadorTantoMasAltoEquipoA, jugadorTantoMasAltoEquipoB);
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
					!accion.getID().equals(Accion.ACCION_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FLOR) &&
					!accion.getID().equals(Accion.ACCION_ENVIDO_ENVIDO)){
						nuevasAccionesVuelta.add(accion);
				}
			}else{
				nuevasAccionesVuelta.add(accion);
			}
		}
		
		vuelta.setAccionesDeVuelta(nuevasAccionesVuelta);
	}
}
