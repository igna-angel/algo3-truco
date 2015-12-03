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
	
	public EnvidoDecorator cantarEnvido(Envido envidoCantado){
		return envidoCantado.getDestino().responderA(envidoCantado, this, this.getPartido());
	}
	
	public EnvidoDecorator cantarRealEnvido(RealEnvido realEnvidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(realEnvidoCantado, this, this.getPartido());
	}
	
	public EnvidoDecorator cantarFaltaEnvido(FaltaEnvido faltaEnvidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(faltaEnvidoCantado, this);
	}

	public Accion cantarFlor(AccionFlor florCantada) {
		//verificacion que antes haya alguien con flor de los demas jugadores
		return this.getJugadorQueDebeAceptar().responderA(florCantada, this, this.getPartido());
	}
	
	public void ejecutarRespuestaTanto(QuieroTanto accion){
		if(accion.getOrigen().getTantoEnMano() > accion.getDestino().getTantoEnMano()){
			this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getOrigen()), accion.cantar());
		} else {
			this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getDestino()), accion.cantar());
		}
	}
	
	public void ejecutarRespuestaTanto(NoQuieroTanto accion){
		this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getOrigen()), accion.cantar());
	}

	public void ejecutarRespuestaTanto(Envido accion){
		this.cantarEnvido(accion);
	}
	
	public void ejecutarRespuestaTanto(RealEnvido accion){
		this.cantarRealEnvido(accion);
	}
	
	public void ejecutarRespuestaTanto(FaltaEnvido accion){
		this.cantarFaltaEnvido(accion);
	}
}
