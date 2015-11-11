package com.modelo;

import java.util.ArrayList;
import java.util.List;

public class JugadorHumano extends Jugador{
	
	public JugadorHumano(){
		_cartasEnMano = new ArrayList<Carta>();
	}
	
	public void mezclarMazo(){
		Mazo.getInstanciaMazo().mezclar();
	}
	
	public void repartirCartas(Jugador... jugadoresEnMesa){

	    for (int i = 0; i < jugadoresEnMesa.length; ++i) {
			jugadoresEnMesa[i].recibirCarta();
		}
	    
	    for (int i = 0; i < jugadoresEnMesa.length; ++i) {
			jugadoresEnMesa[i].recibirCarta();
		}
	    
	    for (int i = 0; i < jugadoresEnMesa.length; ++i) {
			jugadoresEnMesa[i].recibirCarta();
		}
	    
	    //ver como evitar que se repita el ciclo 
	    //(una carta por "reparticion" seria esto)
	    
	}

	public void recibirCarta() {
		Mazo.getInstanciaMazo().repartirCarta(this);
	}

	public List<Carta> getCartasEnMano() {
		return this._cartasEnMano; //trabajar excepciones null posible
	}
	
	public void devolverCartasAlMazo(){
		if(this._cartasEnMano.size() != 0){
			Mazo.getInstanciaMazo().agregarCartasAlMazoPorFinalizacionDeRonda(this._cartasEnMano);
			this._cartasEnMano.clear();
		} //trabajar excepciones
	}
	
	public Carta getCartaEnMano(int index){
		if ((index < 3) && (index >= 0) && (_cartasEnMano.size() != 0)){
			return _cartasEnMano.get(index);
		} else {
			return null;
		} //trabajar excepciones
	}

	@Override
	public Carta bajarCarta() {
		// TODO Auto-generated method stub
		return null;
	}

	/*Backup Jugador que subio Manuel
	private CircularList<Jugador> _jugadores = null;
	private int _puntaje = 0;
	
	public Equipo(){
		this._jugadores = new CircularList<Jugador>();
	}
	
	private CircularList<Jugador> getJugadores(){
		return this._jugadores;
	}
	
	public void agregarJugador(){
		this.getJugadores().add(new Jugador());
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
	*/
}
