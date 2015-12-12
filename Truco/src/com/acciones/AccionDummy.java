package com.acciones;

import com.exceptions.AccionDummyException;
import com.modelo.Equipo;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public class AccionDummy extends Accion{

	public AccionDummy() {
		super();
	}
	
	@Override
	public int getCantidadDecoradas(){
		return 0;
	}
	
	@Override
	public String getID() {
		return Accion.ACCION_DUMMY;
	}

	@Override
	protected Accion getDecorada() {
		throw new AccionDummyException();
	}
	
	@Override
	public int getPuntosQueridos() {
		return 0;
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
		return 0;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpiarAccionesRelacionadasEnVuelta(Vuelta vuelta) {

	}

	
}
