package com.modelo;

public class TrucoMain {
	
	public static void main(String[] args){
	
		Mazo mazo = new Mazo();
		mazo.crear();
		mazo.imprimir();
		
		/*Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		partido.crearPartido();
		
		partido.getOrdenJugadores().resetToFirst();
		for(int i = 0; i < partido.getCantidadDeJugadoresTotales(); i++){
			System.out.println("jugador: " + i);
			partido.getOrdenJugadores().getCurrent().imprimirCartas();
			System.out.println();
			partido.getOrdenJugadores().advanceCursor();
		}*/
	}

}
