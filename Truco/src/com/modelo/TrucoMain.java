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
		
//		partido.jugar();
		partido.nuevaRonda();

		partido.getRondaActual().nuevaVuelta();
		
		partido.getRondaActual().seCantoTruco(); //No se quiere e inicia otra ronda
		
		partido.getRondaActual().nuevaVuelta();
		
		partido.getRondaActual().seCantoTruco();
		
//		partido.getRondaActual().nuevaVueltaSecundaria(partido.getRondaActual().getVueltaActual().getAccionTruco());
//		
//		partido.getRondaActual().seCantoReTruco();
//		
//		partido.getRondaActual().nuevaVueltaSecundaria(partido.getRondaActual().getVueltaActual().getAccionTruco());
//		
//		partido.getRondaActual().seCantoValeCuatro();
//
		partido.getRondaActual().agregarPuntajeTruco();
		
		System.out.println("Equipo A: " + partido.getPuntosPrimerEquipo());
		System.out.println("Equipo B: " + partido.getPuntosUltimoEquipo());
		
		//para el puntaje hay que fijarse quien es el ganador, esta por
		//defecto el equipo uno para el truco por ahora
	}

}
