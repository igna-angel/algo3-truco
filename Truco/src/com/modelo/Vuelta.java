package com.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaInvalida;

public class Vuelta implements IRecibible {
	
	private Stack<Carta> _cartas = null;
	private Carta _cartaGanadora = null;
	private List<Accion> _accionesTanto = null;
	private List<Accion> _accionesTruco = null;
	private Ronda _ronda;
	
	private Jugador _jugadorInicial = null;
	private Jugador _jugadorActual = null;

	public Vuelta(Ronda ronda, List<Accion> acciones) {
		this._cartas = new Stack<Carta>();
		this._accionesTanto = acciones;
		this._cartaGanadora = new CartaInvalida();
		this._ronda = ronda;
	}

	public Vuelta(Ronda ronda, Jugador jugadorInicial) {
		this._cartas = new Stack<Carta>();
		this._accionesTanto = new ArrayList<Accion>(); 
		this._accionesTruco = new ArrayList<Accion>();
		this._cartaGanadora = new CartaInvalida();
		this._ronda = ronda;

		this.asignarJugadorInicial(jugadorInicial);
		this.asignarJugadorActual(this.getJugadorInicial());
	}
	
	public void asignarJugadorInicial(Jugador jugadorInicial){
		this._jugadorInicial = jugadorInicial;
	}
	
	public Jugador getJugadorInicial(){
		return this._jugadorInicial;
	}
	
	public Jugador getJugadorActual(){
		return this._jugadorActual;
	}
	
	private void procesarTurnoJugadorActual(){
		this.getJugadorActual().jugar(this);		
	}
	
	private void asignarJugadorActual(Jugador jugadorActual){
		this._jugadorActual = jugadorActual;
	}
	
	private void asignarJugadorSiguiente(){
		this.asignarJugadorActual(this.getRonda().getJugadorSiguienteAlActual());
	}
	
	public Jugador getJugadorConCartaGanadora(){
		return this.getRonda().getJugadorConCartaGanadora(this.getCartaGanadora());
	}

	public void jugar(){
		while(!this.esFinDeVuelta()){
			this.procesarTurnoJugadorActual();
			this.asignarJugadorSiguiente();
		}
	}
	
	public Stack<Carta> getCartas(){
		return this._cartas;
	}
	
	public Carta getCartaGanadora(){
		return this._cartaGanadora;
	}
	
	private void setCartaGanadora(Carta carta){
		this._cartaGanadora = carta;
	}
	
	public Ronda getRonda(){
		return this._ronda;
	}
	
	public List<Accion> getAccionesEnvido(){
		return this._accionesTanto;
	}
	
	public List<Accion> getAccionesTruco(){
		return this._accionesTruco;
	}
	
	private boolean esFinDeVuelta(){
		return this.getRonda().getCantidadDeJugadoresTotales() == this.getCantidadDeCartasEnVuelta();
	}
	
	public int getCantidadDeCartasEnVuelta(){
		return this.getCartas().size();
	}
	
	@Override
	public void recibirCarta(Carta carta) {
		this.setCartaGanadora(carta.ganador(this.getCartaGanadora()));
		this.getCartas().push(carta);
	}
}
