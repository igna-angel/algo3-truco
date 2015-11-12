package com.modelo;

import java.util.ArrayList;

public class JugadorHumano extends Jugador{
			
	public JugadorHumano(){
		this._cartas = new ArrayList<Carta>();
	}
		
	@Override
	public void recibirCarta(Carta carta){
		this.getCartas().add(carta);
	}

	public void devolverCartas(){
		this.getCartas().clear();
	}

	@Override
	public void bajarCarta(Mano mano, Carta carta){
		this.getCartas().remove(carta);
		mano.recibirCarta(carta);
	}
}
	
	
	
