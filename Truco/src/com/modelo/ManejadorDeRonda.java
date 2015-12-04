package com.modelo;

import com.modelo.acciones.envido.*;
import com.modelo.acciones.truco.*;

public class ManejadorDeRonda {
	
	private Partido _partido = null;
	
	public ManejadorDeRonda(Partido partido){
		this._partido = partido;
	}

	public Partido getPartido(){
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
	
	public TantoDecorator cantarEnvido(Envido envidoCantado){
		return envidoCantado.getDestino().responderA(envidoCantado, this, this.getPartido());
	}
	
	public TantoDecorator cantarRealEnvido(RealEnvido realEnvidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(realEnvidoCantado, this, this.getPartido());
	}
	
	public TantoDecorator cantarFaltaEnvido(FaltaEnvido faltaEnvidoCantado){
		return this.getJugadorQueDebeAceptar().responderA(faltaEnvidoCantado, this);
	}

	public TantoDecorator cantarFlor(Flor florCantada) {
		//verificacion que antes haya alguien con flor de los demas jugadores
		return this.getJugadorQueDebeAceptar().responderA(florCantada, this, this.getPartido());
	}
	
	public TantoDecorator cantarContraFlorAlResto(ContraFlorAlResto contraFlorAlRestoCantada){
		return this.getJugadorQueDebeAceptar().responderA(contraFlorAlRestoCantada, this);
	}
	
	public void ejecutarRespuestaTanto(QuieroTanto accion){
		if(accion.getOrigen().getTantoEnMano() > accion.getDestino().getTantoEnMano()){
			this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getOrigen()), accion.cantar());
		} else {
			this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getDestino()), accion.cantar());
		}
	}
	
	public void ejecutarRespuestaTanto(FlorQuiero accion){
		if(accion.getOrigen().getTantoEnMano() > accion.getDestino().getTantoEnMano()){
			this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getOrigen()), accion.cantar());
		} else {
			this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getDestino()), accion.cantar());
		}
	}
	
	public void ejecutarRespuestaTanto(FlorMeAchico accion) {
		this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getOrigen()), accion.cantar());
	}
	
	public void ejecutarRespuestaTanto(NoQuieroTanto accion){
		this.getPartido().agregarPuntosAlEquipo(this.getPartido().getEquipoDeJugador(accion.getOrigen()), accion.cantar());
	}

	public void ejecutarRespuestaTanto(Flor accion){
		this.cantarFlor(accion);
	}

	public void ejecutarRespuestaTanto(ContraFlorAlResto accion) {
		this.cantarContraFlorAlResto(accion);
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
