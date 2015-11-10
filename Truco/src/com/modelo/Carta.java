package com.modelo;

import com.exceptions.NumeroFueraDeRangoException;

public class Carta {

	public enum Palo{
		Indefinido(0), Espada(1), Basto(2), Oro(3), Copa(4);
		
		private int _valorPalo;
		private Palo(int valorPalo){
			this._valorPalo = valorPalo;
		}
		
		public int getValorPalo(){
			return this._valorPalo;
		}
		
		public static Palo getTipoPalo(int valorPalo){			
			for(Palo palo : Palo.values()){
				if(palo.getValorPalo() == valorPalo) return palo;
			}
			
			return Palo.Indefinido;
		}
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
