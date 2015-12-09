package com.modelo;

import com.exceptions.ValueNotFoundException;

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
	
	public boolean contiene(Jugador jugador){
		try{
			this.getJugadores().getIndexOf(jugador);
			return true;
		}catch(ValueNotFoundException e){
			return false;
		}
	}
	
	public int getMayorTanto(){
		int tantoMayor = 0;
		int tantoEnMano;
		
		for(int i = 0; i < this.getJugadores().getSize(); i++){
			tantoEnMano = this.getJugadores().getAt(i).getTantoEnMano();
			tantoMayor = tantoEnMano > tantoMayor? tantoEnMano : tantoMayor;
		}
		
		return tantoMayor;
	}	
}
