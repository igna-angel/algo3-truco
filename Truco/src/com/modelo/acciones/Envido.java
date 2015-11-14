package com.modelo.acciones;

import java.util.Arrays;

public class Envido extends Accion {
	public Envido() {
		this.puntosQueOtorgaSiSeQuiere = 2;
		this.puntosQueOtorgaSiNoSeQuiere = 1;
		this.proximasAccionesPosibles = Arrays.asList("EnvidoEnvido", "EnvidoRealEnvido", "EnvidoFaltaEnvido", "Flor", "JuegoLibre");
	}

	@Override
	public boolean accionTrucoEsPosible() {
		return false;
	}

	@Override
	public boolean accionReTrucoEsPosible() {
		return false;
	}

	@Override
	public boolean accionValeCuatroEsPosible() {
		return false;
	}

	@Override
	public boolean accionEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionEnvidoEnvidoEsPosible() {
		return true;
	}

	@Override
	public boolean accionRealEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionEnvidoRealEnvidoEsPosible() {
		return true;
	}

	@Override
	public boolean accionEnvidoEnvidoRealEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionFaltaEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionEnvidoFaltaEnvidoEsPosible() {
		return true;
	}

	@Override
	public boolean accionEnvidoEnvidoFaltaEnvidoEsPosible() {
		return false;
	}
	
	@Override
	public boolean accionEnvidoRealEnvidoFaltaEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionEnvidoEnvidoRealEnvidoFaltaEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionRealEnvidoFaltaEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionFlorEsPosible() {
		return true;
	}

	@Override
	public boolean accionContraflorEsPosible() {
		return false;
	}

	@Override
	public boolean accionContraflorAlRestoEsPosible() {
		return false;
	}

	@Override
	public boolean accionJuegoLibreEsPosible() {
		return true;
	}

}
