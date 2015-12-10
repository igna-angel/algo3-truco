package com.modelo.cartas;

import com.exceptions.NumeroFueraDeRangoException;

public abstract class Carta implements IPeleable {

	public static final int PUNTOS_BASE_MISMO_PALO = 20;
	private static final int PUNTOS_BASE_NO_MISMO_PALO = 0;
	protected static final int PUNTOS_CARTA_SIN_PUNTOS = 0;
	
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
	private boolean _enMano = false;
	
	public Carta(Palo palo, int numero){
		this.setCarta(palo, numero);
	}
	
	public Carta(){
		this(Palo.Indefinido, 1);
	}
	
	private void setCarta(Palo palo, int numero){
		if(numero < 1 || numero == 8 || numero == 9 || numero > 12) throw new NumeroFueraDeRangoException();
		
		this._palo = palo;
		this._numero = numero;
	}
	
	public void cartaEnMano(){
		this._enMano = true;
	}
	
	public void cartaJugada(){
		this._enMano = false;
	}
	
	public boolean estaCartaEnMano(){
		return this._enMano;
	}
	
	public Palo getPalo(){
		return this._palo;
	}
	
	public int getNumero() {
		return this._numero;
	}

	public boolean esMismoPaloQue(Carta carta){
		return this.getPalo() == carta.getPalo();
	}

	public abstract int getPuntosEnvido();

	public int getPuntosEnvidoCon(Carta carta) {
		return this.esMismoPaloQue(carta)? (Carta.PUNTOS_BASE_MISMO_PALO + this.getPuntosEnvido() + carta.getPuntosEnvido()) : Carta.PUNTOS_BASE_NO_MISMO_PALO;
	}	
}
