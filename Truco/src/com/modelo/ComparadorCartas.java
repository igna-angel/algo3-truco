package com.modelo;

import java.util.ArrayList;
import java.util.List;

import com.modelo.cartas.Carta;

public class ComparadorCartas {
	
	public Carta compararCartas (Carta... cartas) {
		
		int cantidadDeCartas = cartas.length;
		
		// Me parece innecesaria esta validacion, en que cambia comparar 1 o 300 cartas?
		// this.verificarNumeroCartasCorrecto(cantidadDeCartas);
		
		return compararCartas(cantidadDeCartas,cartas);
	}
	
	/*private void verificarNumeroCartasCorrecto(int cantidadDeCartas) {
		
		if (cantidadDeCartasCorrecto(cantidadDeCartas)){
			return;
		} else {
			throw  new NumeroCartasACompararIncorrectoException();
		}
	}*/
	
	/*private boolean cantidadDeCartasCorrecto(int cantidad){
		return (cantidad == 2 || cantidad == 4 || cantidad == 6);
	}*/

	private Carta compararCartas(int cantidadDeCartas,Carta... cartas){
		
		int primeraCarta = 0;
		Carta cartaGanadora = cartas[primeraCarta];
		
		for (int i=1; i < cantidadDeCartas ; i++){
			cartaGanadora = cartaGanadora.ganador(cartas[i]);
		}
		
		return cartaGanadora;
	}

	public List<Carta> getCartasMasAltasQueUnaDada(Carta masAltaJugada, List<Carta> cartasAComparar) {
		List<Carta> cartasQueGanan = new ArrayList<Carta>();
		ComparadorCartas comparador = new ComparadorCartas();
		for (Carta cartaAVerificar : cartasAComparar) {
			if (comparador.compararCartas(masAltaJugada, cartaAVerificar) == cartaAVerificar) {
				cartasQueGanan.add(cartaAVerificar);
			}
		}
		
		return cartasQueGanan;
	}

	public Carta getCartaMasBaja(Carta... cartas) {
		// Comparo 1 a 1 las cartas recibidas y me quedo con la más baja
		Carta masBaja = cartas[0];
		Carta auxiliar = null;
		for (int i=1; i < cartas.length ; i++){
			auxiliar = masBaja.ganador(cartas[i]);
			masBaja = (auxiliar == masBaja) ? cartas[i] : masBaja;
		}
		
		return masBaja;
	}
}
