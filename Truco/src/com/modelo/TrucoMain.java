package com.modelo;

public class TrucoMain {
	
	public static void main(String[] args){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();

		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		partido.crearPartido();
		partido.jugar();
	}
}
