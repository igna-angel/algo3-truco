package com.acciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public class Truco extends Accion{
	
	public Truco(Jugador origen, Jugador destino){
		super(origen, destino, Accion.PUNTOS_TRUCO);
		this.setDecorada(new AccionDummy());
		List<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new ReTruco(origen, destino));
		this.setAccionesPosibles(acciones);
	}
	
	@Override
	protected int getCantidadDecoradas() {
		return 0;
	}
	
	@Override
	public String getID(){
		return Accion.ACCION_TRUCO;
	}
	
	@Override
	public int getPuntosQueridos(){
		return this._puntos;
	}
	
	@Override
	public int getPuntosNoQueridos(){
		return this.getCantidadDecoradas() + Accion.PUNTOS_NO_QUERIDO;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(ronda.getEquipoGanador(), this.getPuntosQueridos());		
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		Equipo equipoOrigen = partido.getEquipoDeJugador(this.getOrigen());
		
		partido.agregarPuntosAlEquipo(equipoOrigen, this.getPuntosNoQueridos());		
	}

	@Override
	public int getPuntosQueridos(Partido partido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntosQueridos(Partido partido, Equipo equipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void limpiarAccionesRelacionadasEnVuelta(Vuelta vuelta) {
		List<Accion> nuevasAccionesVuelta = new ArrayList<Accion>();
		
		for(Accion accion : vuelta.getAccionesDeVuelta()){
			if(accion.getEstado().getID().equals(ESTADO_INDEFINIDO)){
				if(!accion.getID().equals(Accion.ACCION_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_REAL_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FALTA_ENVIDO) &&
					!accion.getID().equals(Accion.ACCION_FLOR)){
						nuevasAccionesVuelta.add(accion);
				}
			}else{
				if(!accion.getID().equals(Accion.ACCION_NO_TRUCO))
					nuevasAccionesVuelta.add(accion);
			}
		}
		
		vuelta.setAccionesDeVuelta(nuevasAccionesVuelta);
	}
}
