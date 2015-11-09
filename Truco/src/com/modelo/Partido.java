package com.modelo;


import java.util.Stack;

import com.exceptions.NoHayEquiposException;

public class Partido {

	private final int PUNTAJE_PICAPICA_MIN = 5;
	private final int PUNTAJE_PICAPICA_MAX = 25;
	private final int JUGADORES_MINIMOS_PICAPICA_POR_EQUIPO = 3;
	
	private Stack<Ronda> _rondas = null;
	private CircularList<Equipo> _equipos = null;
	private ManejadorDeRonda _manejadorDeRonda = null;
	
	
	public Partido(){
		this._rondas = new Stack<Ronda>();
		this._equipos = new CircularList<Equipo>();
		this._manejadorDeRonda = new ManejadorDeRonda();
	}
	
	public ManejadorDeRonda getManejadorDeRonda(){
		return this._manejadorDeRonda;
	}
	
	private Stack<Ronda> getRondas(){
		return this._rondas;
	}
	
	public int getCantidadDeRondas(){
		return this.getRondas().size();
	}
	
	public Ronda getRondaActual(){
		return this.getRondas().peek();
	}
	
	public void nuevaRonda(){
		if(getEquipos().isEmpty()) throw new NoHayEquiposException();
		if(this.getRondas().isEmpty()) this.getRondas().push(new RondaRedonda(this.getEquipos().getFirst(), this.getEquipos().getLast()));
		else this.getRondas().push(this.getNuevaRonda());
	}
	
	private Ronda getNuevaRonda() {
		return this.getRondaActual().getRondaSiguiente(this.esPicaPica());
	}
	
	public void agregarEquipo(){
		this.getEquipos().add(new Equipo());
	}
	
	public int getCantidadJugadoresEnEquipo(int numeroEquipo){
		return this.getEquipos().getAt(numeroEquipo).getCantidadJugadores();
	}
	
	public void agregarJugadorAEquipo(int numeroEquipo){
		this.getEquipos().getAt(numeroEquipo).agregarJugador();
	}
	
	private CircularList<Equipo> getEquipos(){
		return this._equipos;
	}
	
	public int getCantidadEquipos(){
		return this.getEquipos().getSize();
	}
	
	public int getCantidadDeJugadoresPorEquipo(){
		if(this.getEquipos().isEmpty()) throw new NoHayEquiposException();
		return this.getEquipos().getFirst().getCantidadJugadores();
	}
	
	private boolean	esPicaPica(){
		return (this.getCantidadDeJugadoresPorEquipo() >= JUGADORES_MINIMOS_PICAPICA_POR_EQUIPO && (this.obtenerMaximoPuntaje() >= this.PUNTAJE_PICAPICA_MIN && this.obtenerMaximoPuntaje() <= this.PUNTAJE_PICAPICA_MAX));
	}
	
	private int obtenerMaximoPuntaje(){
		return (this.getEquipos().getFirst().getPuntaje() > this.getEquipos().getLast().getPuntaje()) ? this.getEquipos().getFirst().getPuntaje() : this.getEquipos().getLast().getPuntaje();
	}

}
