package com.modelo;

import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.TrucoNoCantadoException;
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
	
	public Accion cantarReTruco(boolean trucoYaCantado, Partido partido){
		
		if (trucoYaCantado){
			ReTruco reTruco = new ReTruco(new Truco());
			Accion reTrucoAceptado = this.getJugadorQueDebeAceptar(partido).responderA(reTruco,this,partido);
			partido.volverJugadorQueCantoPreviamente();
			partido.getRondaActual().elReTrucoFueCantado();
			return reTrucoAceptado;
		} else {
			throw new TrucoNoCantadoException();
		}
	}

	public Accion cantarValeCuatro(boolean reTrucoYaCantado, Partido partido) {
		
		if (reTrucoYaCantado){
			ValeCuatro valeCuatro = new ValeCuatro(new ReTruco(new Truco()));
			Accion valeCuatroAceptado = this.getJugadorQueDebeAceptar(partido).responderA(valeCuatro);
			partido.volverJugadorQueCantoPreviamente();
			partido.getRondaActual().elValeCuatroFueCantado();
			return valeCuatroAceptado;
		} else {
			throw new ReTrucoNoCantadoException();
		}
	}
}
