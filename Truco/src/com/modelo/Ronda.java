package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import com.exceptions.TantoYaCantadoException;
import com.exceptions.AccionNoPosibleException;
import com.exceptions.NoHayVueltasException;

import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoNoCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;
import com.exceptions.VueltaParaCantarTantoNoPosibleException;
import com.modelo.acciones.envido.AccionTanto;
import com.modelo.acciones.envido.Envido;
import com.modelo.acciones.envido.TantoDecorator;
import com.modelo.acciones.envido.FaltaEnvido;
import com.modelo.acciones.envido.Flor;
import com.modelo.acciones.envido.NoQuieroTanto;
import com.modelo.acciones.envido.QuieroTanto;
import com.modelo.acciones.envido.RealEnvido;
import com.modelo.acciones.envido.Tanto;
import com.modelo.acciones.truco.AccionTruco;
import com.modelo.acciones.truco.NoQuiero;
import com.modelo.acciones.truco.ReTruco;
import com.modelo.acciones.truco.Truco;
import com.modelo.acciones.truco.ValeCuatro;
import com.modelo.cartas.Carta;
import com.modelo.cartas.Carta.Palo;
import com.modelo.cartas.CartaAnchoEspada;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaTres;


public abstract class Ronda {

	private static final int NUMERO_MAXIMO_VUELTAS = 3;
	
	private Partido _partido;
	private Jugador _repartio;

	private Stack<Vuelta> _vueltas;
	private List<Jugador> _ganadoresVueltas = null;
	
	private List<String> _accionesPosibles;

	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._ganadoresVueltas = new ArrayList<Jugador>();
		this._vueltas = new Stack<Vuelta>();
		
		// Al comenzar, se puede cantar envido, real envido, falta envido, truco y flor (ver despues esto ultimo)
		this._accionesPosibles = Arrays.asList("envido", "real envido", "falta envido", "truco", "flor");
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

	protected Stack<Vuelta> getVueltas(){
		return this._vueltas;
	}

	protected Vuelta getVueltaActual(){
		if(this.getVueltas().isEmpty()) throw new NoHayVueltasException();
		return this.getVueltas().peek();
	}
	
//	public void nuevaVuelta(List<Accion> acciones){
//		this.getVueltas().push(new Vuelta(this, acciones));
//	}

	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}

	public void nuevaVuelta() {
		if(this.getVueltas().isEmpty())
			this.getVueltas().push(new Vuelta(this, this.getPartido().getJugadorSiguienteA(this.getRepartio())));
		else
			this.getVueltas().push(new Vuelta(this, this.getVueltaActual().getJugadorConCartaGanadora()));
	}

	public void seCantoTruco(){
		this.trucoNoCantadoPreviamente();
		AccionTruco trucoCantado = new Truco();

		Accion truco = this._partido.getManejadorDeRonda().cantarTruco(trucoCantado);

		this.getVueltas().peek().getAccionesTruco().add(truco);
		//verificar si es no querido y finalizar ronda
	}

	private void trucoNoCantadoPreviamente() {
		if(this._vueltas.peek().getAccionesTruco().isEmpty()){
			return;
		} else {
			throw new TrucoYaCantadoException();
		}
	}

	private void ReTrucoNoCantadoPreviamente() {
		if (this.getTruco().cantar() == 3
		 || this.getTruco().cantar() == 4){
			throw new ReTrucoYaCantadoException();
		} else {
			return;
		}
	}

	public void seCantoReTruco(){
		this.ReTrucoNoCantadoPreviamente();
		ReTruco reTrucoCantado = new ReTruco(this.getTruco());

		Accion reTruco = this._partido.getManejadorDeRonda().cantarReTruco(reTrucoCantado);

		this.getVueltas().peek().getAccionesTruco().add(reTruco);
		//verificar si es no querido y finalizar ronda
	}

	private AccionTruco getTruco() {
		try{
			if (!this._vueltas.peek().getAccionesTruco().isEmpty()){
				return (AccionTruco) this._vueltas.peek().getAccionesTruco().get(0);
			} else {
				this._vueltas.pop();
				return this.getTruco();
			}
		} catch (EmptyStackException e){
			throw new TrucoNoCantadoException();
		}
	}

	public void seCantoValeCuatro(){
		ValeCuatro valeCuatroCantado = new ValeCuatro(this.getReTruco());
		Accion valeCuatro = this._partido.getManejadorDeRonda().cantarValeCuatro(valeCuatroCantado);

		this.getVueltas().peek().getAccionesTruco().add(valeCuatro);
		//verificar si es no querido y finalizar ronda
	}

	private AccionTruco getReTruco() {
		try{
			if (!this._vueltas.peek().getAccionesTruco().isEmpty()){
				return (AccionTruco) this._vueltas.peek().getAccionesTruco().get(1);
			} else {
				this._vueltas.pop();
				return this.getReTruco();
			}
		}catch (EmptyStackException e){
			throw new ReTrucoNoCantadoException();
		}catch (IndexOutOfBoundsException e){
			if (this._vueltas.peek().getAccionesTruco().get(0).cantar() == 3){
				return (AccionTruco) this._vueltas.peek().getAccionesTruco().get(0);
			} else {
				throw new ValeCuatroYaCantadoException();
			}
		}
	}

	private Accion getAccionFinalTruco(){
		try{
			int posAccionFinal = this.getPosAccionFinalTruco();
			Accion accionFinal = this._vueltas.peek().getAccionesTruco().get(posAccionFinal-1);
			return accionFinal;
		} catch (EmptyStackException e){
			Accion accionFinal = new NoQuiero(new Truco());
			return accionFinal;
		}
	}

	private void agregarPuntajeDeTruco(){

		Accion accionFinal = this.getAccionFinalTruco();

		int puntajeFinal = accionFinal.cantar();
		int puntajeNulo = 0;

		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}

	private int getPosAccionFinalTruco() {
		if (!(this._vueltas.peek().getAccionesTruco().isEmpty())){
			return this._vueltas.peek().getAccionesTruco().size();
		} else {
			this._vueltas.pop();
			return this.getPosAccionFinalTruco();
		}
	}

	public void seCantoEnvido(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		
		Envido envidoCantado = new Envido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen), this);
		TantoDecorator desicion = this.getPartido().getManejadorDeRonda().cantarEnvido(envidoCantado);
		Accion envido = (Accion)desicion;
		this.getVueltaActual().getAccionesEnvido().add(envido);
//		this.getPartido().getManejadorDeRonda().ejecutarRespuestaTanto(desicion);
	}
	
	private boolean esPrimeraVuelta() {
		return this.getVueltas().size() == 1;
	}

	public void seCantoRealEnvido(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		RealEnvido realEnvidoCantado = new RealEnvido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen), this);
		Accion realEnvido = this.getPartido().getManejadorDeRonda().cantarRealEnvido(realEnvidoCantado);
		this.getVueltas().peek().getAccionesEnvido().add(realEnvido);
	}

	public boolean yaSeCantoEnvido() {
		return !this.getVueltaActual().getAccionesEnvido().isEmpty();
	}

	public void seCantoFaltaEnvido(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		FaltaEnvido faltaEnvidoCantado = new FaltaEnvido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen), this);
		Accion faltaEnvido = this.getPartido().getManejadorDeRonda().cantarFaltaEnvido(faltaEnvidoCantado);

		Envido envidoCantado = new Envido(new Tanto(), jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen), this);
		Accion envido = this._partido.getManejadorDeRonda().cantarEnvido(envidoCantado);
		this.getVueltas().peek().getAccionesEnvido().add(envido);
	}

	private int getPosAccionFinalEnvido() {
		if (!this._vueltas.peek().getAccionesEnvido().isEmpty()){
			return this._vueltas.peek().getAccionesEnvido().size();
		} else {
			this._vueltas.pop();
			return this.getPosAccionFinalEnvido();
		}
	}

	private void agregarPuntajeDeTantoNormal(Accion accionFinal) {

		int puntajeFinal = accionFinal.cantar();
		int puntajeNulo = 0;

		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}

	private Accion getAccionFinalEnvido(){
		try {
			int posAccionFinal = this.getPosAccionFinalEnvido();
			Accion accionFinal = this._vueltas.peek().getAccionesEnvido().get(posAccionFinal-1);
			return accionFinal;
		} catch (EmptyStackException e) {
			Accion accionFinal = new Tanto();
			return accionFinal;
		}
	}

	private void agregarPuntajeDeTanto(){

		Accion accionFinal = this.getAccionFinalEnvido();

		if (accionFinal.cantar() < 30){
			this.agregarPuntajeDeTantoNormal(accionFinal);
		} else {
			this.agregarPuntajeDeTantoEspecial();
		}
	}

	private void agregarPuntajeDeTantoEspecial() {

		int puntajeFinal = this._partido.getcantidadDePuntosFaltantes();
		int puntajeNulo = 0;

		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}

	public void seCantoFlor(Jugador jugadorOrigen){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		
//		Jugador jugadorAuxiliar2 = jugadorOrigen;
//		
//		Jugador jugadorAuxiliar = new JugadorHumano();
//		jugadorAuxiliar.recibirCarta(new CartaAnchoEspada());
//		jugadorAuxiliar.recibirCarta(new CartaSieteEspada());
//		jugadorAuxiliar.recibirCarta(new CartaTres(Palo.Espada));
//		
//		jugadorOrigen = jugadorAuxiliar;
		
		if(this.jugadorNoTieneFlor(jugadorOrigen)) throw new AccionNoPosibleException();
		
		Flor florCantada = new Flor(new Tanto(),jugadorOrigen, this.getPartido().getJugadorSiguienteA(jugadorOrigen));
		
//		jugadorContrarioConFlor = this.buscarJugadorConFlor();
		
		TantoDecorator desicion = this.getPartido().getManejadorDeRonda().cantarFlor(florCantada);
		Accion flor = (Accion)desicion;
		this.getVueltaActual().getAccionesEnvido().add(flor);
	}
	
	private boolean jugadorNoTieneFlor(Jugador jugador){
		return (!jugador.getMano().florEnMano());
	}

	public void agregarPuntajes(){
	    this.agregarPuntajeDeTruco();
		this.agregarPuntajeDeTanto();
	}

	public Jugador getJugadorActual() {
		return this.getVueltaActual().getJugadorActual();
	}

	public void jugar() {
		while(!this.esFinDeRonda()){
			this.nuevaVuelta();
			this.getVueltaActual().jugar();
			this.agregarGanadorDeVuelta(this.getVueltaActual().getJugadorConCartaGanadora());
		}
	}

	public Jugador getJugadorConCartaGanadora(Carta cartaGanadora) {
		return this.getPartido().getJugadorConCartaGanadora(cartaGanadora);
	}
	
	private void agregarGanadorDeVuelta(Jugador jugador){
		this._ganadoresVueltas.add(jugador);
	}

	private List<Jugador> getGanadoresDeVueltas(){
		return this._ganadoresVueltas;
	}
	
	private boolean esFinDeRonda(){
		return this.hayGanador() || this.getVueltas().size() == Ronda.NUMERO_MAXIMO_VUELTAS;
	}

	private boolean hayGanador() {
		return this.getVueltas().size() >= 2 && (this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(0)) == this.getPartido().getEquipoDeJugador(this.getGanadoresDeVueltas().get(1)));
	}
	
	public List<String> getAccionesPosiblesEnElMomento() {
		return this._accionesPosibles;
	}

	
	public void setAccionesPosibles (List<String> acciones) {
		this._accionesPosibles = acciones;
	}


//	public void finalizarRonda(){
//	}

}
