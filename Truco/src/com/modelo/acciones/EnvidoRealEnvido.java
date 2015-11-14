package com.modelo.acciones;

import java.util.Arrays;

public class EnvidoRealEnvido extends Accion {
	public EnvidoRealEnvido() {
		this.puntosQueOtorgaSiSeQuiere = 5;
		this.puntosQueOtorgaSiNoSeQuiere = 2;
		this.proximasAccionesPosibles = Arrays.asList("EnvidoRealEnvidoFaltaEnvido", "JuegoLibre");
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
		return true;
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
