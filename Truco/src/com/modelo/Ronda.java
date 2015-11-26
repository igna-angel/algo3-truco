package com.modelo;

import java.util.List;
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
	
	protected Stack<Vuelta> getVueltas(){
		return this._vueltas;
	}
	
	public void nuevaVuelta(List<Accion> acciones){
		this.getVueltas().push(new Vuelta(this, acciones));
	}
	
	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}
	
	public void seCantoTruco(){
		
		Accion truco = this._partido.getManejadorDeRonda().cantarTruco(this._partido);
		
		this.getVueltas().peek().getAcciones().add(truco);	
	}

	public void nuevaVuelta() {
		this.getVueltas().push(new Vuelta(this));
	}
}
