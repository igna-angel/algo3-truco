package com.modelo;


import java.util.Stack;

import com.exceptions.EmptyListException;
import com.exceptions.NoHayEquiposException;

public class Partido {

	private final int PUNTAJE_PICAPICA_MIN = 5;
	private final int PUNTAJE_PICAPICA_MAX = 25;
	private final int JUGADORES_MINIMOS_PICAPICA_POR_EQUIPO = 3;
	private final int CARTAS_POR_JUGADOR = 3;
	
	private Stack<Ronda> _rondas = null;
	private CircularList<Equipo> _equipos = null;
	private ManejadorDeRonda _manejadorDeRonda = null;
	
	private Mazo _mazo = null;
	private CircularList<Jugador> _ordenJugadores = null;	
	
	public Partido(){
		this._rondas = new Stack<Ronda>();
		this._equipos = new CircularList<Equipo>();
		this._manejadorDeRonda = new ManejadorDeRonda();	
	}
	
	public void crearOrdenJugadores(){
		this._ordenJugadores = new CircularList<Jugador>();
		
		this.getEquipos().getFirst().getJugadores().resetToFirst();
		this.getEquipos().getLast().getJugadores().resetToFirst();
		
		while(this._ordenJugadores.getSize() < this.getCantidadDeJugadoresTotales()){
			this._ordenJugadores.add(this.getEquipos().getFirst().getJugadores().getCurrent());
			this._ordenJugadores.add(this.getEquipos().getLast().getJugadores().getCurrent());
			this.getEquipos().getFirst().getJugadores().advanceCursor();
			this.getEquipos().getLast().getJugadores().advanceCursor();
		}	
		
	}

	public CircularList<Jugador> getOrdenJugadores(){
		return this._ordenJugadores;
	}
		
	public Mazo getMazo(){
		return this._mazo;
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
		if(this.getRondas().isEmpty()){
			this.getOrdenJugadores().resetToFirst();
			this.getRondas().push(new RondaRedonda(this, this.getOrdenJugadores().getCurrent()));
		}
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
	
	public CircularList<Equipo> getEquipos(){
		return this._equipos;
	}
	
	public int getCantidadEquipos(){
		return this.getEquipos().getSize();
	}
	
	public int getCantidadDeJugadoresPorEquipo(){
		if(this.getEquipos().isEmpty()) throw new NoHayEquiposException();
		return this.getEquipos().getFirst().getCantidadJugadores();
	}
	
	public int getCantidadDeJugadoresTotales(){
		return this.getCantidadDeJugadoresPorEquipo()*2;
	}
	
	private boolean	esPicaPica(){
		return (this.getCantidadDeJugadoresPorEquipo() >= JUGADORES_MINIMOS_PICAPICA_POR_EQUIPO && (this.obtenerMaximoPuntaje() >= this.PUNTAJE_PICAPICA_MIN && this.obtenerMaximoPuntaje() <= this.PUNTAJE_PICAPICA_MAX));
	}
	
	private int obtenerMaximoPuntaje(){
		return (this.getEquipos().getFirst().getPuntaje() > this.getEquipos().getLast().getPuntaje()) ? this.getEquipos().getFirst().getPuntaje() : this.getEquipos().getLast().getPuntaje();
	}

	public void agregarPuntos(int puntajeA, int puntajeB) {
		this.getEquipos().getFirst().agregarPuntos(puntajeA);
		this.getEquipos().getLast().agregarPuntos(puntajeB);
	}

	public void crearPartido(){
		this._mazo = new Mazo();
//		this._mazo.crear();
		
		this.crearOrdenJugadores();
		
		this.nuevaRonda();
		this.getRondaActual().repartir(this.getMazo().mezclar(), this.CARTAS_POR_JUGADOR);
	}	
}
