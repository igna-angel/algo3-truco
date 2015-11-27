package com.modelo;

public class TrucoMain {
	
	public static void main(String[] args){
	
//		Mazo mazo = new Mazo();
//		mazo.crear();
//		mazo.imprimir();
//		
		Partido partido = new Partido();
		
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
		}
		
		partido.getRondaActual().nuevaVuelta();
		
//		partido.getRondaActual().seCantoFaltaEnvido();
		
		partido.getRondaActual().seCantoTruco();
		
		partido.getRondaActual().nuevaVuelta();
	
		partido.getRondaActual().seCantoReTruco();
		
		partido.getRondaActual().nuevaVuelta();
		
		partido.getRondaActual().seCantoValeCuatro();
		
		partido.getRondaActual().agregarPuntajeDeTruco();
		
//		partido.getRondaActual().agregarPuntajeDeEnvido();
		//para el puntaje hay que fijarse quien es el ganador, esta por
		//defecto el equipo uno
		
		System.out.println(partido.getPuntosPrimerEquipo());
		
		System.out.println(partido.getPuntosUltimoEquipo());
		
	}

}
