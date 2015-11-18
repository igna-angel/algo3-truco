package com.modelo;

import com.modelo.Carta;

public class ComparadorCartas {
	
	public Carta compararCartas (Carta... cartas) {
		
		Carta cartaGanadora = null;
		
		if (cartas.length == 2){
			cartaGanadora = compararCartas(2,cartas);
		}
		
		if (cartas.length == 4){
			cartaGanadora = compararCartas(4,cartas);
		}
		
		if (cartas.length == 6){
			cartaGanadora = compararCartas(6,cartas);
		}
		
		return cartaGanadora;
	}
	
	private Carta compararCartas(int cantidadDeCartas,Carta... cartas){
		
		int index = 0;
		Carta cartaGanadora = null;
		
		if (cantidadDeCartas == 2){
			Carta primeraCarta = cartas[index];
			Carta segundaCarta = cartas[index+1];
			
			cartaGanadora = primeraCarta.vs(segundaCarta);
			return cartaGanadora;
		}
		
		if (cantidadDeCartas == 4){
			Carta primeraCarta = cartas[index];
			Carta segundaCarta = cartas[index+1];
			Carta terceraCarta = cartas[index+2];
			Carta cuartaCarta = cartas[index+3];
			
			cartaGanadora = primeraCarta.vs(segundaCarta);
			cartaGanadora = cartaGanadora.vs(terceraCarta);
			cartaGanadora = cartaGanadora.vs(cuartaCarta);
			return cartaGanadora;
		}
		
		if (cantidadDeCartas == 6){
			Carta primeraCarta = cartas[index];
			Carta segundaCarta = cartas[index+1];
			Carta terceraCarta = cartas[index+2];
			Carta cuartaCarta = cartas[index+3];
			Carta quintaCarta = cartas[index+4];
			Carta sextaCarta = cartas[index+5];
			
			cartaGanadora = primeraCarta.vs(segundaCarta);
			cartaGanadora = cartaGanadora.vs(terceraCarta);
			cartaGanadora = cartaGanadora.vs(cuartaCarta);
			cartaGanadora = cartaGanadora.vs(quintaCarta);
			cartaGanadora = cartaGanadora.vs(sextaCarta);
			return cartaGanadora;
		}
		
		return cartaGanadora; //podria haber una cantidad de cartas incorrecta, ver excepcion 
							  //aunque se supone que se la pasa el desarrollador
	}
}
