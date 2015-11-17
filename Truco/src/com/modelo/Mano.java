package com.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mano implements IRecibible{

	private List<Carta> _cartas = null;
	
	public Mano() {
		this._cartas = new ArrayList<Carta>();
	}
	
	public List<Carta> getCartas(){
		return this._cartas;
	}
	
	@Override
	public void recibirCarta(Carta carta) {
		this.getCartas().add(carta);
	}
	
	public int getCantidadCartas(){
		return this.getCartas().size();
	}
	
	public boolean contiene(Carta carta){
		return this.getCartas().contains(carta);
	}

	public void quitarCarta(Carta carta) {
		this.getCartas().remove(carta);
	}

	public void devolverCartas() {
		this.getCartas().clear();
	}
}
