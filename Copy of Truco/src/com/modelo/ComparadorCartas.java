package com.modelo;

import com.modelo.Carta;

public class ComparadorCartas {
	
	public Carta compararCartas (Carta... cartas) {
		
		int cantidadDeCartas = cartas.length;
		
		return compararCartas(cantidadDeCartas,cartas);
	}
	
	private Carta compararCartas(int cantidadDeCartas,Carta... cartas){
		
		int primeraCarta = 0;
		Carta cartaGanadora = cartas[primeraCarta];
		
		for (int i=1; i < cantidadDeCartas ; i++){
			cartaGanadora = cartaGanadora.vs(cartas[i]);
		}
		
		return cartaGanadora; //podria haber una cantidad de cartas incorrecta, ver excepcion 
							  //aunque se supone que se la pasa el desarrollador
	}
}
