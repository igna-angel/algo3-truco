package com.modelo;

public abstract class Ronda {

	protected Equipo _equipoA;
	protected Equipo _equipoB;
	
	public Ronda(Equipo equipoA, Equipo equipoB){
		this._equipoA = equipoA;
		this._equipoB = equipoB;
	}
	
	public abstract Ronda getRondaSiguiente(boolean esPicaPica);
	
	public void asignarPuntos(int puntajeA, int puntajeB){
		this._equipoA.agregarPuntos(puntajeA);
		this._equipoB.agregarPuntos(puntajeB);
	}
}
