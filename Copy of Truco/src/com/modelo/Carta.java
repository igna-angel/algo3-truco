package com.modelo;

import com.modelo.cartas.CartaCinco;
import com.modelo.cartas.CartaCuatro;
import com.modelo.cartas.CartaDiez;
import com.modelo.cartas.CartaDoce;
import com.modelo.cartas.CartaDos;
import com.modelo.cartas.CartaOnce;
import com.modelo.cartas.CartaSeis;
import com.modelo.cartas.CartaSiete;
import com.modelo.cartas.CartaTres;
import com.modelo.cartas.CartaUno;

//import com.exceptions.NumeroFueraDeRangoException;

public abstract class Carta {
	
	protected Palo _palo;
	protected int _numero;
	//private int _numero = 0;
	
//	public Carta(Palo palo){
//		this.setCarta(palo);
//	}
	
	public abstract Carta vs(Carta carta);
	
	public abstract Carta vs(CartaUno cartaUno);
	
	public abstract Carta vs(CartaDos cartaDos);
	
	public abstract Carta vs(CartaTres cartaTres);
	
	public abstract Carta vs(CartaCuatro cartaCuatro);
	
	public abstract Carta vs(CartaCinco cartaCinco);
	
	public abstract Carta vs(CartaSeis cartaSeis);
	
	public abstract Carta vs(CartaSiete cartaSiete);
	
	public abstract Carta vs(CartaDiez cartaDiez);
	
	public abstract Carta vs(CartaOnce cartaOnce);
	
	public abstract Carta vs(CartaDoce cartaDoce);
	
	
//	public Carta(){
//		this(Palo.Indefinido, 1);
//	}
	
//	private void setCarta(Palo palo){
//		this._palo = palo;
//	}
	
	public int getNumero(){
		return this._numero;
	}
	
	public Palo getPalo(){
		return this._palo;
	}
	
//	public int getNumero() {
//		return this._numero;
//	}
	
}
