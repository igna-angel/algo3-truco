package com.modelo.acciones.envido;

import com.modelo.Ronda;

public class Tanto implements AccionTanto {
	
	@Override
	public int cantar() {
		return 0;
	}
	
	@Override
	public boolean accionEsPosible(Ronda rondaActual) {
		return true;
	}
	
	// No me cierra esto
	@Override
	public String getNombreAccion() {
		return "tanto";
	}

}
