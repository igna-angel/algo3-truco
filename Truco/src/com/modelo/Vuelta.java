package com.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.acciones.Accion;
import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaInvalida;

public class Vuelta implements IRecibible {
	
	private Stack<Carta> _cartas = null;
	private Carta _cartaGanadora = null;
	private Ronda _ronda;
	private List<Accion> _acciones = null;
	
	private Jugador _jugadorInicial = null;
	private Jugador _jugadorActual = null;
	private boolean _esParda = false;

	public Vuelta(Ronda ronda, List<Accion> acciones, Jugador jugadorInicial) {
		this._cartas = new Stack<Carta>();
		this._acciones = acciones;
		this._cartaGanadora = new CartaInvalida();
		this._ronda = ronda;

		this.asignarJugadorInicial(jugadorInicial);
		this.asignarJugadorActual(this.getJugadorInicial());
	}
	
	public List<Accion> getAccionesDeVuelta(){
		return this._acciones;
	}
	
	public List<Accion> getAccionesDeNuevaVuelta(){
		List<Accion> accionesNuevaVuelta = new ArrayList<Accion>();
		
		for(Accion accion : this.getAccionesDeVuelta()){
			if(accion.getID() == Accion.ACCION_TRUCO || accion.getID() == Accion.ACCION_RE_TRUCO || accion.getID() == Accion.ACCION_VALE_CUATRO) accionesNuevaVuelta.add(accion);
		}
		
		return accionesNuevaVuelta;
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
	
	public Jugador getJugadorSiguienteAlActual(){
		return this.getRonda().getJugadorSiguienteAlActual();
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
<<<<<<< HEAD
			System.out.println("Jugar Vuelta");
=======
			System.out.println("Jugando Jugador");
>>>>>>> origin/master
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

	private boolean esFinDeVuelta(){
		return this.getRonda().getCantidadDeJugadoresTotales() == this.getCantidadDeCartasEnVuelta();
	}
	
	public int getCantidadDeCartasEnVuelta(){
		return this.getCartas().size();
	}
	
	private boolean definirSiEsParda(Carta cartaGanadora, Carta cartaNueva){
		if(cartaGanadora.getNumero() == cartaNueva.getNumero()) return true;
		
		if(this._esParda && cartaGanadora.getNumero() > cartaNueva.getNumero())	return true;
		
		return false;
	}
	
	@Override
	public void recibirCarta(Carta carta) {
		this._esParda = this.definirSiEsParda(this.getCartaGanadora(), carta);
		this.setCartaGanadora(this.getCartaGanadora().ganador(carta));
		this.getCartas().push(carta);
	}

	public boolean getEsParda() {
		return this._esParda;
	}

	public void procesarAcciones() {
		for(Accion accion : this.getAccionesDeVuelta()){
			accion.procesarAccion(this.getRonda().getPartido(), this.getRonda());
		}
	}
}
