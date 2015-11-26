package com.modelo;

import com.modelo.acciones.truco.*;

public class ManejadorDeRonda {

	public Accion cantarTruco(Partido partido){
		
		AccionTruco trucoCantado = new Truco();
		Accion trucoAceptado = this.getJugadorQueDebeAceptar(partido).responderA(trucoCantado,this,partido);
		partido.volverJugadorQueCantoPreviamente();
		return trucoAceptado;
	}
	
	private Jugador getJugadorQueDebeAceptar(Partido partidoActual){
		
		Jugador jugadorAAceptarAccion;
		jugadorAAceptarAccion = partidoActual.getJugadorSiguienteAlActual();
		return jugadorAAceptarAccion;
	}
	
	public Accion cantarReTruco(AccionTruco truco, Partido partido){
		
		ReTruco reTruco = new ReTruco(truco);
		Accion reTrucoAceptado = this.getJugadorQueDebeAceptar(partido).responderA(reTruco,this,partido);
		partido.volverJugadorQueCantoPreviamente();
		return reTrucoAceptado;
	}

	public Accion cantarValeCuatro(ReTruco retruco, Partido partido) {
		
		ValeCuatro valeCuatro = new ValeCuatro(retruco);
		Accion valeCuatroAceptado = this.getJugadorQueDebeAceptar(partido).responderA(valeCuatro);
		partido.volverJugadorQueCantoPreviamente();
		return valeCuatroAceptado;
	}
	
}
