package com.modelo;

import java.util.ArrayList;
import java.util.List;

import com.acciones.Accion;
import com.acciones.Envido;
import com.acciones.FaltaEnvido;
import com.acciones.Flor;
import com.acciones.RealEnvido;
import com.acciones.Truco;
import com.exceptions.NoHayGanadorException;
import com.exceptions.NoHayVueltasException;
import com.exceptions.NoSeEncuentraJugadorException;
import com.modelo.cartas.Carta;

public abstract class Ronda {

	private static final int NUMERO_MAXIMO_VUELTAS = 3;
	
	private Partido _partido;
	private Jugador _repartio;

	private List<Vuelta> _vueltas;
	private List<Jugador> _ganadoresVueltas = null;

	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._ganadoresVueltas = new ArrayList<Jugador>();
		this._vueltas = new ArrayList<Vuelta>();
		this.mezclarYRepartir();
	}

	public abstract Ronda getRondaSiguiente(boolean esPicaPica);

	public void asignarPuntos(int puntajeA, int puntajeB){
		this.getPartido().agregarPuntos(puntajeA, puntajeB);
	}

	protected Partido getPartido(){
		return this._partido;
	}

	public Jugador getRepartio(){
		return this._repartio;
	}

	public Jugador getJugadorSiguienteAlActual(){
		return this.getPartido().getJugadorSiguienteAlActual();
	}

	protected List<Vuelta> getVueltas(){
		return this._vueltas;
	}

	public Vuelta getVueltaActual(){
		if(this.getVueltas().isEmpty()) throw new NoHayVueltasException();
		return this.getVueltas().get(this.getVueltas().size()-1);
	}

	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}

	public void nuevaVuelta() {
		if(this.getVueltas().isEmpty()){
			List<Accion> accionesBase = new ArrayList<Accion>();
			accionesBase.add(new Envido(null, null));
			accionesBase.add(new RealEnvido(null, null));
			accionesBase.add(new FaltaEnvido(null, null));
			accionesBase.add(new Truco(null, null));
			if(this.getPartido().seJuegaConnFlor()){
				accionesBase.add(new Flor(null, null));
			}
			
			this.getVueltas().add(new Vuelta(this, accionesBase, this.getPartido().getJugadorSiguienteA(this.getRepartio())));
		}else{
			this.getVueltas().add(new Vuelta(this, this.getVueltaActual().getAccionesDeNuevaVuelta(), this.getVueltaActual().getJugadorConCartaGanadora()));
		}
	}
	
	public Jugador getJugadorActual() {
		return this.getVueltaActual().getJugadorActual();
	}

	public void mezclarYRepartir(){
		this.getPartido().mezclarYRepartir();
	}
	
	public void jugar() {
		while(!this.esFinDeRonda()){
			System.out.println("Jugar Ronda");
			this.nuevaVuelta();
			this.getVueltaActual().jugar();
			this.agregarGanadorDeVuelta(this.getVueltaActual().getJugadorConCartaGanadora());
		}
		
		this.procesarAcciones();
	}
	
	private void procesarAcciones(){
		for(Vuelta vuelta : this.getVueltas()){
			vuelta.procesarAcciones();
		}
	}

	public Jugador getJugadorConCartaGanadora(Carta cartaGanadora) {
		return this.getPartido().getJugadorConCartaGanadora(cartaGanadora);
	}
	
	public void agregarGanadorDeVuelta(Jugador jugador){
		this._ganadoresVueltas.add(jugador);
	}

	private List<Jugador> getGanadoresDeVueltas(){
		return this._ganadoresVueltas;
	}
	
	private boolean esFinDeRonda(){
		return (!this.hayParda() && this.hayGanador()) || this.seJugaronTodasLasVueltas();
	}
	
	private boolean seJugaronTodasLasVueltas(){
		return this.getVueltas().size() == Ronda.NUMERO_MAXIMO_VUELTAS;
	}

	private boolean hayGanador() {
		return (this.getVueltas().size() >= 2 && (this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(0)) == this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(1))));
	}
	
	private boolean hayParda(){
		if(this.getVueltas().isEmpty()) return false;
		if(this.getVueltas().size() == 1 && this.getVueltas().get(0).getEsParda()) return true;
		if(this.getVueltas().size() == 2){
			if(this.getVueltas().get(0).getEsParda() && this.getVueltas().get(1).getEsParda()) return true;
			return false;
		}
		return false;
	}

	public Equipo getEquipoGanador() {
		if(!this.hayGanador()) throw new NoHayGanadorException();
		
		List<Equipo> equiposGanadores = new ArrayList<Equipo>();
		
		for(Jugador jugador : this.getGanadoresDeVueltas()){
			equiposGanadores.add(this.getPartido().getEquipoDeJugador(jugador));
		}
		
		Equipo ganadorA = equiposGanadores.get(0);
		Equipo ganadorB = null;
		
		for(int i = 1; i < equiposGanadores.size(); i++){
			if(ganadorA == equiposGanadores.get(i))
				return ganadorA;
			else ganadorB = equiposGanadores.get(i);
		}
		
		return ganadorB;
	}
	
	public Jugador getJugadorConMayorTantoEnEquipo(Equipo equipo){
		return equipo.getJugadorConMayorTanto();
	}
	
	public Jugador getJugadorConMayorFlorEnEquipo(Equipo equipo){
		return equipo.getJugadorConMayorFlor();
	}

	public int getMayorFlorDeEquipo(Equipo equipo){
		return equipo.getMayorFlor();
	}
	
	public Jugador getJugadorManoEntre(Jugador jugadorA, Jugador jugadorB){
		Jugador jugadorInicial = this.getPartido().getJugadorSiguienteA(this.getRepartio());
		Jugador jugadorActual = jugadorInicial;
		
		do{		
			if(jugadorA == jugadorInicial) return jugadorA;
			else if (jugadorB == jugadorInicial) return jugadorB;
			jugadorActual = this.getPartido().getJugadorSiguienteA(jugadorActual);
		}while(jugadorActual != jugadorInicial);
		
		throw new NoSeEncuentraJugadorException();
	}
}
