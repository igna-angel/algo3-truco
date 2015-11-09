package com.modelo;

import com.exceptions.NumeroFueraDeRangoException;

public class Carta {

	public enum Palo{
		Indefinido, Espada, Basto, Oro, Copa,
	}
	
	
	private Palo _palo = Palo.Indefinido;
	private int _numero = 0; 
	
	public Carta(Palo palo, int numero){
		this.setCarta(palo, numero);
	}
	
	public Carta(){
		this(Palo.Indefinido, 1);
	}
	
	private void setCarta(Palo palo, int numero){
		if(numero < 1 || numero > 12) throw new NumeroFueraDeRangoException();
		
		this._palo = palo;
		this._numero = numero;
	}
	
	public Palo getPalo(){
		return this._palo;
	}
	
	public int getNumero() {
		return this._numero;
	}
	
}
