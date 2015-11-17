package com.modelo;

import java.util.Stack;

public abstract class Ronda {

	private Partido _partido;
	private Jugador _repartio;	
	
	private Stack<Vuelta> _vueltas; // IMPLEMENTAR VUELTAS
	
	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._vueltas = new Stack<Vuelta>();
	}
	
	public abstract Ronda getRondaSiguiente(boolean esPicaPica);
	
	public void asignarPuntos(int puntajeA, int puntajeB){
		this.getPartido().agregarPuntos(puntajeA, puntajeB);
	}
	
	protected Partido getPartido(){
		return this._partido;
	}
	
	public Jugador getRepartio(){
		return this._repartio;
	}
}
