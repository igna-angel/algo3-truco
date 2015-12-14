package com.modelo;

import java.util.ArrayList;
import java.util.List;

import com.acciones.Accion;
import com.acciones.AccionNoTruco;
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

	private boolean _esFinDeRonda;

	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._ganadoresVueltas = new ArrayList<Jugador>();
		this._vueltas = new ArrayList<Vuelta>();
		this._esFinDeRonda = false;
		this.recolectarCartasDeJugadores();
		this.mezclarYRepartir();
		this.nuevaVuelta();
	}

	private void recolectarCartasDeJugadores() {
		this.getPartido().recolectarCartasDeJugadores();		
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

	public int getCantidadDeVueltas(){
		return this.getVueltas().size();
	}
	
	public Vuelta getVueltaActual(){
		if(this.getVueltas().isEmpty()) throw new NoHayVueltasException();
		return this.getVueltas().get(this.getCantidadDeVueltas()-1);
	}

	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}

	public void nuevaVuelta() {
		if(this.getVueltas().isEmpty()){
			List<Accion> accionesBase = new ArrayList<Accion>();
			accionesBase.add(new AccionNoTruco());
			accionesBase.add(new Envido(null, null));
			accionesBase.add(new RealEnvido(null, null));
			accionesBase.add(new FaltaEnvido(null, null));
			accionesBase.add(new Truco(null, null));
			
			if(this.getPartido().seJuegaConnFlor()){
				accionesBase.add(new Flor(null, null));
			}
			
			this.getVueltas().add(new Vuelta(this, accionesBase, this.getPartido().getJugadorSiguienteA(this.getRepartio())));
		}else if(this.esFinDeRonda()){
			this.procesarAcciones();
			this._partido.nuevaRonda();
			this._partido.terminarSiEquipoAlcanzoLosTantosNecesarios();
		} else if (this.hayParda()){
			this.getVueltaActual().limpiarAccionesDeTanto();
			this.getVueltas().add(new VueltaParda(this,this.getVueltaActual().getAccionesDeVuelta(),this.getPartido().getJugadorSiguienteA(this.getRepartio())));
		}else{
			this.getVueltaActual().limpiarAccionesDeTanto();
			this.getVueltas().add(new Vuelta(this, this.getVueltaActual().getAccionesDeVuelta(), this.getVueltaActual().getJugadorConCartaGanadora()));
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
			this.nuevaVuelta();
			this.getVueltaActual().jugar();
			this.agregarGanadorDeVuelta(this.getVueltaActual().getJugadorConCartaGanadora());
		}
		
		this.procesarAcciones();
	}
	
	private void procesarAcciones(){
		this.getVueltaActual().procesarAcciones();
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
	
	public boolean esInicioDeRonda(){
		return this.getCantidadDeVueltas() == 1 && this.getVueltaActual().esInicioDeVuelta();
	}
	
	public boolean esFinDeRonda(){
		return this._esFinDeRonda ? true : ( (!this.hayParda() && this.hayGanador()) || this.seJugaronTodasLasVueltas());
	}
	
	public void definirFinDeRonda(){
		this._esFinDeRonda = true;
	}
	
	private boolean seJugaronTodasLasVueltas(){
		return this.getCantidadDeVueltas() == Ronda.NUMERO_MAXIMO_VUELTAS;
	}

	private boolean hayGanador() {
		return ((this.getCantidadDeVueltas() >= 2) && this.ganadorDeVueltaUnoEsElMismoQueDeVueltaDos());
	}
	
	private boolean ganadorDeVueltaUnoEsElMismoQueDeVueltaDos(){
		return this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(0)) == this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(1));
	}
	
	public boolean hayParda(){
		if(this.getVueltas().isEmpty()) return false;
		if(this.getCantidadDeVueltas() == 1 && this.getVueltas().get(0).getEsParda()) return true;
		if(this.getCantidadDeVueltas() == 2){
			if(this.getVueltas().get(0).getEsParda() && this.getVueltas().get(1).getEsParda()) return true;
			return false;
		}
		if(this.getCantidadDeVueltas() == 3){
			if(this.getVueltas().get(2).getEsParda()){
				this.asignarGanadorDeVueltaParda(this.getPartido().getJugadorSiguienteA(this.getRepartio()));
			}
			return false;
		}
		return false;
	}

	public Equipo getEquipoGanador() {
		System.out.println(this.hayGanador());
		if(!this.esFinDeRonda()) throw new NoHayGanadorException();
		
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

	public void asignarGanadorDeVuelta() {
		this.agregarGanadorDeVuelta(this.getVueltaActual().getJugadorConCartaGanadora());
	}
	
	public void asignarGanadorDeVueltaParda(Jugador jugadorGanadorParda) {
		this.getGanadoresDeVueltas().remove(0);
		this.agregarGanadorDeVuelta(jugadorGanadorParda);
	}
}
