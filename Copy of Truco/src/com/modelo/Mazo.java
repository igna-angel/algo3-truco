package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

//import com.modelo.Carta.Palo;

public class Mazo {
	
	public static final int CARTAS_EN_MAZO = 40;
	public static final int CANTIDAD_DE_PALOS = 4;
	
	private Palo basto = new Basto();
	private Palo espada = new Espada();
	private Palo oro = new Oro();
	private Palo copa = new Copa();
	
	private List<Carta> _cartas = null;
			
	public Mazo(){
		this._cartas = new ArrayList<Carta>();
		this.crear();
	}
	
	public List<Carta> getCartas(){
		return this._cartas;
	}
	
	private void crear(){
		
		_cartas.add(new CartaUno(basto));
		_cartas.add(new CartaUno(oro));
		_cartas.add(new CartaUno(espada));
		_cartas.add(new CartaUno(copa));
		
		_cartas.add(new CartaDos(basto));
		_cartas.add(new CartaDos(oro));
		_cartas.add(new CartaDos(espada));
		_cartas.add(new CartaDos(copa));
		
		_cartas.add(new CartaTres(basto));
		_cartas.add(new CartaTres(oro));
		_cartas.add(new CartaTres(espada));
		_cartas.add(new CartaTres(copa));
		
		_cartas.add(new CartaCuatro(basto));
		_cartas.add(new CartaCuatro(oro));
		_cartas.add(new CartaCuatro(espada));
		_cartas.add(new CartaCuatro(copa));
		
		_cartas.add(new CartaCinco(basto));
		_cartas.add(new CartaCinco(oro));
		_cartas.add(new CartaCinco(espada));
		_cartas.add(new CartaCinco(copa));
		
		_cartas.add(new CartaSeis(basto));
		_cartas.add(new CartaSeis(oro));
		_cartas.add(new CartaSeis(espada));
		_cartas.add(new CartaSeis(copa));
		
		_cartas.add(new CartaSiete(basto));
		_cartas.add(new CartaSiete(oro));
		_cartas.add(new CartaSiete(espada));
		_cartas.add(new CartaSiete(copa));
		
		_cartas.add(new CartaDiez(basto));
		_cartas.add(new CartaDiez(oro));
		_cartas.add(new CartaDiez(espada));
		_cartas.add(new CartaDiez(copa));
		
		_cartas.add(new CartaOnce(basto));
		_cartas.add(new CartaOnce(oro));
		_cartas.add(new CartaOnce(espada));
		_cartas.add(new CartaOnce(copa));
		
		_cartas.add(new CartaDoce(basto));
		_cartas.add(new CartaDoce(oro));
		_cartas.add(new CartaDoce(espada));
		_cartas.add(new CartaDoce(copa));
	}
	
	public void imprimir(){		
		this.getCartas().forEach(carta -> {
			System.out.println(carta.getPalo() + " " + carta.getNumero());
		});
		
		System.out.println("");
	}
	
	public void imprimir(int[] nuevoOrden){
		for(int i = 0; i < nuevoOrden.length; i++){
			System.out.println(this._cartas.get(nuevoOrden[i]).getPalo() + " " + this._cartas.get(nuevoOrden[i]).getNumero());
		}	
		
		System.out.println("");
	}
	
	public int[] mezclar(){
		int[] nuevoOrden = new int[Mazo.CARTAS_EN_MAZO];
		int posicionInvalida = -1;
		
		Arrays.fill(nuevoOrden, posicionInvalida);
		
		int nuevaPosicion;
		
		for(int i = 0; i < Mazo.CARTAS_EN_MAZO; i++){
			do{
				nuevaPosicion = ThreadLocalRandom.current().nextInt(0, Mazo.CARTAS_EN_MAZO);
			}while(nuevoOrden[nuevaPosicion] != posicionInvalida);
			
			nuevoOrden[nuevaPosicion] = i;			
		}
		
		return nuevoOrden;
	}
	
	public Carta getCarta(int indice){
		return this.getCartas().get(indice);
	}
}
