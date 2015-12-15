package com.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.acciones.Accion;
import com.exceptions.EstadoIndefinidoException;
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
	
	public void limpiarAccionesDeTanto(){
		List<Accion> nuevaListaAcciones = new ArrayList<Accion>();

		for(Accion accion : this.getAccionesDeVuelta()){
			if(!(accion.esDeTanto() || accion.esDeFlor()) || !accion.getEstado().getID().equals(Accion.ESTADO_INDEFINIDO)){
				nuevaListaAcciones.add(accion);
			}
		}
		
		this.setAccionesDeVuelta(nuevaListaAcciones);
	}
	
	public List<Accion> getAccionesDeVuelta(){
		return this._acciones;
	}
	
	public void setAccionesDeVuelta(List<Accion> acciones){
		this._acciones = acciones;
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
	
	private void asignarJugadorActual(Jugador jugadorActual){
		this._jugadorActual = jugadorActual;
	}
	
	private void asignarJugadorSiguiente(){
		this.asignarJugadorActual(this.getRonda().getJugadorSiguienteAlActual());
	}
	
	public Jugador getJugadorConCartaGanadora(){
		return this.getRonda().getJugadorConCartaGanadora(this.getCartaGanadora());
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
	
	public boolean esInicioDeVuelta(){
		return this.getCartas().isEmpty();
	}

	public boolean esFinDeVuelta(){
		return this.getRonda().getCantidadDeJugadoresTotales() == this.getCantidadDeCartasEnVuelta();
	}
	
	public int getCantidadDeCartasEnVuelta(){
		return this.getCartas().size();
	}
	
	private boolean definirSiEsParda(Carta cartaGanadora, Carta cartaNueva){
		return cartaGanadora.ganador(cartaNueva).equals(cartaGanadora) && cartaNueva.ganador(cartaGanadora).equals(cartaNueva);
	}
	
	@Override
	public void recibirCarta(Carta carta) {
		this._esParda = this.definirSiEsParda(this.getCartaGanadora(), carta);
		
		this.setCartaGanadora(this.getCartaGanadora().ganador(carta));
		this.getCartas().push(carta);
		
		this.asignarJugadorSiguiente();
		
		if(this.esFinDeVuelta()){
			this._ronda.asignarGanadorDeVuelta();
			this.getRonda().nuevaVuelta();
		}
	}

	public boolean getEsParda() {
		return this._esParda;
	}

	public void procesarAcciones() {
		for(Accion accion : this.getAccionesDeVuelta()){
			if (!(accion.esDeTanto() || accion.esDeFlor())) {
				try{
					accion.procesarAccion(this.getRonda().getPartido(), this.getRonda());
				}catch(EstadoIndefinidoException e){
					//System.out.println("Accion con estado indefinido, salteando");
				}
			}
		}
	}
}
