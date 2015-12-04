package com.modelo;

public interface Accion {
	
	public abstract int cantar();
	
	public abstract boolean accionEsPosible(Ronda rondaActual);
	
	public abstract String getNombreAccion();
}
