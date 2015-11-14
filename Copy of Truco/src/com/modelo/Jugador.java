package com.modelo;

import java.util.List;

public abstract class Jugador{
	
	protected List<Carta> _cartas;

	public abstract void recibirCarta(Carta carta);

	protected List<Carta> getCartas(){
		return this._cartas;
	}
	
	public int getCantidadCartas(){
		return this.getCartas().size();
	}
		
	public abstract void bajarCarta(Mano mano, Carta carta);
	
	public void imprimirCartas(){
		this.getCartas().forEach(carta ->
			{
				System.out.println("Palo: " + carta.getPalo() + "Numero: " + carta.getNumero());
			}
		);
	}
}
