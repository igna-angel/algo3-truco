package com.modelo;


import java.util.Stack;

import com.exceptions.NoContieneCartaException;
import com.exceptions.NoHayEquiposException;
import com.modelo.cartas.Carta;

public class Partido {

	private final int PUNTAJE_PICAPICA_MIN = 5;
	private final int PUNTAJE_PICAPICA_MAX = 25;
	private final int PUNTAJE_MAXIMO_JUEGO = 30;
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
		this._manejadorDeRonda = new ManejadorDeRonda(this);
		this._mazo = new Mazo();
		this.getMazo().crear();
	}
	
	public void crearOrdenJugadores(){
		this._ordenJugadores = new CircularList<Jugador>();
		
		this.getPrimerEquipo().getJugadores().resetToFirst();
		this.getUltimoEquipo().getJugadores().resetToFirst();
		
		while(this._ordenJugadores.getSize() < this.getCantidadDeJugadoresTotales()){
			this._ordenJugadores.add(this.getPrimerEquipo().getJugadores().getCurrent());
			this._ordenJugadores.add(this.getUltimoEquipo().getJugadores().getCurrent());
			this.getPrimerEquipo().getJugadores().advanceCursor();
			this.getUltimoEquipo().getJugadores().advanceCursor();
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
			this.getRondas().push(new RondaRedonda(this, this.getOrdenJugadores().getFirst()));
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
	
	public void agregarJugadorAEquipo(Jugador jugador, int numeroEquipo){
		this.getEquipos().getAt(numeroEquipo).agregarJugador(jugador);
	}
	
	public CircularList<Equipo> getEquipos(){
		return this._equipos;
	}
	
	public int getCantidadEquipos(){
		return this.getEquipos().getSize();
	}
	
	public int getCantidadDeJugadoresPorEquipo(){
		if(this.getEquipos().isEmpty()) throw new NoHayEquiposException();
		return this.getPrimerEquipo().getCantidadJugadores();
	}
	
	public int getCantidadDeJugadoresTotales(){
		return this.getCantidadDeJugadoresPorEquipo()*2;
	}
	
	public boolean esPicaPica(){
		return (this.getCantidadDeJugadoresPorEquipo() >= JUGADORES_MINIMOS_PICAPICA_POR_EQUIPO && (this.getMaximoPuntaje() >= this.PUNTAJE_PICAPICA_MIN && this.getMaximoPuntaje() <= this.PUNTAJE_PICAPICA_MAX));
	}
	
	public int getMaximoPuntaje(){
		return (this.getPrimerEquipo().getPuntaje() > this.getUltimoEquipo().getPuntaje()) ? this.getPrimerEquipo().getPuntaje() : this.getUltimoEquipo().getPuntaje();
	}

	public void agregarPuntosAlEquipo(Equipo equipo, int puntos){
		equipo.agregarPuntos(puntos);
	}
	
	public void agregarPuntos(int puntajeA, int puntajeB) {
		this.agregarPuntosAlEquipo(this.getPrimerEquipo(), puntajeA);
		this.agregarPuntosAlEquipo(this.getUltimoEquipo(), puntajeB);
	}
	
	public Equipo getPrimerEquipo(){
		return this.getEquipos().getFirst();
	}
	
	public Equipo getUltimoEquipo(){
		return this.getEquipos().getLast();
	}
	
	public int getPuntosPrimerEquipo(){
		return this.getPrimerEquipo().getPuntaje();
	}
	
	public int getPuntosUltimoEquipo(){
		return this.getUltimoEquipo().getPuntaje();
	}
	
	public Jugador getProximoEnRepartir(){
		return this.getJugadorSiguienteA(this.getRepartidorActual());
	}
	
	public Jugador getRepartidorActual(){
		return this.getRondaActual().getRepartio();
	}	

	public Jugador getJugadorActual(){
		return this.getRondaActual().getJugadorActual();
	}
	
	public Jugador getJugadorSiguienteAlActual(){
		return this.getJugadorSiguienteA(this.getJugadorActual());
	}

	public int getcantidadDePuntosFaltantes() {
		return this.puntajeFaltanteSegunMalasOBuenas(this.getMaximoPuntaje());
	}

	private int puntajeFaltanteSegunMalasOBuenas(int puntaje){
		int malas = (PUNTAJE_MAXIMO_JUEGO / 2);
		
		if(puntaje < malas){
			return malas - puntaje;
		} else {
			return (this.PUNTAJE_MAXIMO_JUEGO - puntaje);
		}
	}
	
	public int getPuntosFaltantesDeEquipo(Equipo equipo){
		return this.puntajeFaltanteSegunMalasOBuenas(equipo.getPuntaje());
	}

	public Jugador getJugadorSiguienteA(Jugador jugadorActual) {
		this.getOrdenJugadores().resetToFirst();
		this.getOrdenJugadores().moveCursorTo(this.getOrdenJugadores().getIndexOf(jugadorActual));
		this._ordenJugadores.advanceCursor();
		return this._ordenJugadores.getCurrent();
	}
	
	public void crearPartido(){
		this.crearOrdenJugadores();
		
		this.getMazo().mezclar();
		this.getMazo().repartir(this.getOrdenJugadores(), this.getOrdenJugadores().getFirst(), this.CARTAS_POR_JUGADOR);
	}	
	
	public void jugar(){
		while(!this.esFinDePartido()){
			this.nuevaRonda();
			this.getRondaActual().jugar();
		}
	}
	
	private boolean esFinDePartido(){
		return this.getcantidadDePuntosFaltantes() == 0;
	}

	public Jugador getJugadorConCartaGanadora(Carta cartaGanadora) {
		for(int i = 0; i < this.getOrdenJugadores().getSize(); i++){
			if(this.getOrdenJugadores().getAt(i).tieneCarta(cartaGanadora))
				return this.getOrdenJugadores().getAt(i);
		}
		
		throw new NoContieneCartaException();
	}

	public Equipo getEquipoDeJugador(Jugador jugador){
		return this.getPrimerEquipo().contiene(jugador)? this.getPrimerEquipo() : this.getUltimoEquipo();
	}
}
