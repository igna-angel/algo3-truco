package com.acciones;

import com.modelo.Equipo;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public class AccionNoTruco extends Accion{
	
	public AccionNoTruco() {
		this.setDecorada(new AccionDummy());
		this.aceptar();	
	}

	@Override
	public int getPuntosQueridos() {
		return Accion.PUNTOS_NO_TRUCO;
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
	public int getPuntosNoQueridos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getID() {
		return Accion.ACCION_NO_TRUCO;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(ronda.getEquipoGanador(), this.getPuntosQueridos());	
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpiarAccionesRelacionadasEnVuelta(Vuelta vuelta) {
		// TODO Auto-generated method stub
		
	}


}
