package com.modelo;

import java.util.List;

public abstract class Jugador{
	
	protected List<Carta> _cartasEnMano;

	public abstract void recibirCarta();
	
	public abstract void mezclarMazo();

	public abstract List<Carta> getCartasEnMano();
	
	public abstract void repartirCartas(Jugador... jugadoresEnMesa);

	public abstract void devolverCartasAlMazo();
	
	public abstract Carta getCartaEnMano(int index);
	
	public abstract Carta bajarCarta();
}
