package com.modelo.acciones;

import java.util.Arrays;

public class Flor extends Accion {
	public Flor() {
		this.puntosQueOtorgaSiSeQuiere = 3;
		this.puntosQueOtorgaSiNoSeQuiere = 1;
		this.proximasAccionesPosibles = Arrays.asList("ContraFlor", "ContraFlorAlResto", "JuegoLibre");
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
		return false;
	}

	@Override
	public boolean accionRealEnvidoEsPosible() {
		return false;
	}

	@Override
	public boolean accionEnvidoRealEnvidoEsPosible() {
		return false;
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
		return false;
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
		return false;
	}

	@Override
	public boolean accionContraflorEsPosible() {
		return true;
	}

	@Override
	public boolean accionContraflorAlRestoEsPosible() {
		return true;
	}

	@Override
	public boolean accionJuegoLibreEsPosible() {
		return true;
	}


}
