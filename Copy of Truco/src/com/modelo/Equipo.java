package com.modelo;

public class Equipo {

	private CircularList<Jugador> _jugadores = null;
	private int _puntaje = 0;
	
	public Equipo(){
		this._jugadores = new CircularList<Jugador>();
	}
	
	public CircularList<Jugador> getJugadores(){
		return this._jugadores;
	}
	
	public void agregarJugador(Jugador jugador){
		this.getJugadores().add(jugador);
	}
	
	public int getPuntaje(){
		return this._puntaje;
	}
	
	public void agregarPuntos(int puntos){
		this._puntaje += Math.abs(puntos);
	}
	
	public int getCantidadJugadores(){
		return this.getJugadores().getSize();
	}
	
}
