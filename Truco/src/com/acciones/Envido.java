package com.acciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public class Envido extends Accion{

	public Envido(Jugador origen, Jugador destino){
		super(origen, destino, Accion.PUNTOS_ENVIDO);
		this.setDecorada(new AccionDummy());
		List<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new EnvidoEnvido(origen, destino));
		acciones.add(new RealEnvido(origen, destino));
		acciones.add(new FaltaEnvido(origen, destino));
		this.setAccionesPosibles(acciones);
	}
	
	@Override
	public int getPuntosQueridos() {
		return this.getDecorada().getPuntosQueridos() + this._puntos;
	}
	
	@Override
	public int getPuntosQueridos(Partido partido) {
		return 0;
	}
	
	@Override
	public int getPuntosQueridos(Partido partido, Equipo equipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntosNoQueridos() {
		return this.getDecorada().getPuntosQueridos() + Accion.PUNTOS_NO_QUERIDO;
	}

	@Override
	public String getID() {
		return Accion.ACCION_ENVIDO;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		Jugador jugadorTantoMasAltoEquipoA = ronda.getJugadorConMayorTantoEnEquipo(partido.getPrimerEquipo());
		Jugador jugadorTantoMasAltoEquipoB = ronda.getJugadorConMayorTantoEnEquipo(partido.getUltimoEquipo());
		
		int tantoEquipoA = jugadorTantoMasAltoEquipoA.getTantoEnMano();
		int tantoEquipoB = jugadorTantoMasAltoEquipoB.getTantoEnMano();
				
		if(tantoEquipoA > tantoEquipoB){
			partido.agregarPuntosAlEquipo(partido.getPrimerEquipo(), this.getPuntosQueridos());
		} else if(tantoEquipoA == tantoEquipoB){
			Jugador jugadorMano = ronda.getJugadorManoEntre(jugadorTantoMasAltoEquipoA, jugadorTantoMasAltoEquipoB);
			partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(jugadorMano), this.getPuntosQueridos());
		}else{
			partido.agregarPuntosAlEquipo(partido.getUltimoEquipo(), this.getPuntosQueridos());
		}
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(this.getOrigen()), this.getPuntosNoQueridos());
	}

	@Override
	public void limpiarAccionesRelacionadasEnVuelta(Vuelta vuelta) {		
		List<Accion> nuevasAccionesVuelta = new ArrayList<Accion>();
		
		for(Accion accion : vuelta.getAccionesDeVuelta()){
			if(accion.getEstado().getID().equals(ESTADO_INDEFINIDO)){
				if(!accion.getID().equals(Accion.ACCION_REAL_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_ENVIDO_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FALTA_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FLOR)){
						nuevasAccionesVuelta.add(accion);
				}
			}else{
				nuevasAccionesVuelta.add(accion);
			}
		}
		
		vuelta.setAccionesDeVuelta(nuevasAccionesVuelta);
	}
	
	@Override
	public void iniciarNuevaRondaSiCorresponde() {
		
	}
}
