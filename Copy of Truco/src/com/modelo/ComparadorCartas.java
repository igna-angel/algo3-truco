package com.modelo;

import com.exceptions.NumeroCartasACompararIncorrectoException;
import com.modelo.cartas.Carta;

public class ComparadorCartas {
	
	public Carta compararCartas (Carta... cartas) {
		
		int cantidadDeCartas = cartas.length;
		
		this.verificarNumeroCartasCorrecto(cantidadDeCartas);
		
		return compararCartas(cantidadDeCartas,cartas);
	}
	
	private void verificarNumeroCartasCorrecto(int cantidadDeCartas) {
		
		if (cantidadDeCartasCorrecto(cantidadDeCartas)){
			return;
		} else {
			throw  new NumeroCartasACompararIncorrectoException();
		}
	}
	
	private boolean cantidadDeCartasCorrecto(int cantidad){
		return (cantidad == 2 || cantidad == 4 || cantidad == 6);
	}

	private Carta compararCartas(int cantidadDeCartas,Carta... cartas){
		
		int primeraCarta = 0;
		Carta cartaGanadora = cartas[primeraCarta];
		
		for (int i=1; i < cantidadDeCartas ; i++){
			cartaGanadora = cartaGanadora.ganador(cartas[i]);
		}
		
		return cartaGanadora;
	}
}
