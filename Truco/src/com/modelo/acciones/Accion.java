package com.modelo.acciones;

import java.util.List;


/*
 * Cada uno de los m�todos de esta clase abstracta, verifica si la acci�n que indica su nombre
 * es posible de realizarse en la situaci�n actual del juego
 */

public abstract class Accion {
	protected int puntosQueOtorgaSiSeQuiere;
	protected int puntosQueOtorgaSiNoSeQuiere;
	
	// Sirve esto? Y de servir, c�mo se integrar�a a la interfaz gr�fica para habilitar los botones correspondientes?
	protected List<String> proximasAccionesPosibles;	
	
	public abstract boolean accionTrucoEsPosible();
	public abstract boolean accionReTrucoEsPosible();
	public abstract boolean accionValeCuatroEsPosible();
	public abstract boolean accionEnvidoEsPosible();
	public abstract boolean accionEnvidoEnvidoEsPosible();
	public abstract boolean accionRealEnvidoEsPosible();
	public abstract boolean accionEnvidoRealEnvidoEsPosible();
	public abstract boolean accionEnvidoEnvidoRealEnvidoEsPosible();
	public abstract boolean accionFaltaEnvidoEsPosible();
	public abstract boolean accionEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionEnvidoEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionEnvidoRealEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionEnvidoEnvidoRealEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionRealEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionFlorEsPosible();
	public abstract boolean accionContraflorEsPosible();
	public abstract boolean accionContraflorAlRestoEsPosible();
	public abstract boolean accionJuegoLibreEsPosible();
}
