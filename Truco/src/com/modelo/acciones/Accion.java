package com.modelo.acciones;

import java.util.List;

import com.modelo.Mano;

/*
 * Cada uno de los métodos de esta clase abstracta, verifica si la acción que indica su nombre
 * es posible de realizarse en la situación actual del juego
 */

public abstract class Accion {
	private int puntosQueOtorga;
	
	// Sirve esto? Y de servir, cómo se integraría a la interfaz gráfica para habilitar los botones correspondientes?
	private List<String> proximasAccionesPosibles;	
	
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
	public abstract boolean accionEnvidoEnvidoRealEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionRealEnvidoFaltaEnvidoEsPosible();
	public abstract boolean accionFlorEsPosible();
	public abstract boolean accionContraflorEsPosible();
	public abstract boolean accionContraflorAlRestoEsPosible();
	public abstract boolean accionJuegoLibreEsPosible();
}
