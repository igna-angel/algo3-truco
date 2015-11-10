package com.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador implements IRecibible {

	List<Carta> _cartas = null;
	
	public Jugador(){
		this._cartas = new ArrayList<Carta>();
	}
	
	private List<Carta> getCartas(){
		return this._cartas;
	}
	
	public void recibirCarta(Carta carta){
		this.getCartas().add(carta);
	}

	public void devolverCartas(){
		this.getCartas().clear();
	}
	
	public int getCantidadCartas(){
		return this.getCartas().size();
	}
	
	public void bajarCarta(Mano mano, Carta carta){
		this.getCartas().remove(carta);
		mano.recibirCarta(carta);
	}
}
