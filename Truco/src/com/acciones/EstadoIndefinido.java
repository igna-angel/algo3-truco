package com.acciones;

import com.modelo.Partido;
import com.modelo.Ronda;

public class EstadoIndefinido implements EstadoAccion{

	@Override
	public void procesar(Accion accion, Partido partido, Ronda ronda) {
		accion.procesarAccion(this, partido, ronda);
	}

	@Override
	public String getID() {
		return Accion.ESTADO_INDEFINIDO;
	}

}
