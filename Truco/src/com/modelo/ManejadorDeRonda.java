package com.modelo;

import com.modelo.acciones.envido.*;
import com.modelo.acciones.flor.AccionFlor;
import com.modelo.acciones.truco.*;

public class ManejadorDeRonda {
	
	private Partido _partido = null;
	
	public ManejadorDeRonda(Partido partido){
		this._partido = partido;
	}

	private Partido getPartido(){
		return this._partido;
	}
	
	public Accion cantarTruco(AccionTruco trucoCantado){
		return this.getJugadorQueDebeAceptar().responderA(trucoCantado, this, this.getPartido());
	}
	
	private Jugador getJugadorQueDebeAceptar(){
		return this.getPartido().getJugadorSiguienteAlActual();
	}
	
	public Accion cantarReTruco(ReTruco reTruco){
		return this.getJugadorQueDebeAceptar().responderA(reTruco, this, this.getPartido());
	}

	public Accion cantarValeCuatro(ValeCuatro valeCuatro) {
		return this.getJugadorQueDebeAceptar().responderA(valeCuatro);
	}
	
	public Accion cantarEnvido(Envido envidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(envidoCantado, this, this.getPartido());
	}
	
	public Accion cantarRealEnvido(RealEnvido realEnvidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(realEnvidoCantado, this, this.getPartido());
	}
	
	public Accion cantarFaltaEnvido(FaltaEnvido faltaEnvidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(faltaEnvidoCantado);
	}

	public Accion cantarFlor(AccionFlor florCantada) {
		//verificacion que antes haya alguien con flor de los demas jugadores
		return this.getJugadorQueDebeAceptar().responderA(florCantada, this, this.getPartido());
	}
}
