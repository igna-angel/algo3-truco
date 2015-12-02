package com.modelo;

import java.util.EmptyStackException;
import java.util.Stack;

import com.exceptions.TantoYaCantadoException;
import com.exceptions.NoHayVueltasException;

import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoNoCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;
import com.exceptions.VueltaParaCantarTantoNoPosibleException;
import com.modelo.acciones.envido.Envido;
import com.modelo.acciones.envido.FaltaEnvido;
import com.modelo.acciones.envido.RealEnvido;
import com.modelo.acciones.envido.Tanto;
import com.modelo.acciones.flor.AccionFlor;
import com.modelo.acciones.flor.Flor;
import com.modelo.acciones.truco.AccionTruco;
import com.modelo.acciones.truco.NoQuiero;
import com.modelo.acciones.truco.ReTruco;
import com.modelo.acciones.truco.Truco;
import com.modelo.acciones.truco.ValeCuatro;


public abstract class Ronda {

	private Partido _partido;
	private Jugador _repartio;

	private Stack<Vuelta> _vueltas;

	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._vueltas = new Stack<Vuelta>();
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
		this.getVueltas().push(new Vuelta(this, this.getPartido().getJugadorSiguienteA(this.getRepartio())));
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

	public void seCantoEnvido(){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		Envido envidoCantado = new Envido(new Tanto());
		Accion envido = this.getPartido().getManejadorDeRonda().cantarEnvido(envidoCantado);
		this.getVueltas().peek().getAccionesEnvido().add(envido);
	}

	private boolean esPrimeraVuelta() {
		return this.getVueltas().size() == 1;
	}

	public void seCantoRealEnvido(){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		RealEnvido realEnvidoCantado = new RealEnvido(new Tanto());
		Accion realEnvido = this.getPartido().getManejadorDeRonda().cantarRealEnvido(realEnvidoCantado);
		this.getVueltas().peek().getAccionesEnvido().add(realEnvido);
	}

	private boolean yaSeCantoEnvido() {
		return !this.getVueltaActual().getAccionesEnvido().isEmpty();
	}

	public void seCantoFaltaEnvido(){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		FaltaEnvido faltaEnvidoCantado = new FaltaEnvido(new Tanto());
		Accion faltaEnvido = this.getPartido().getManejadorDeRonda().cantarFaltaEnvido(faltaEnvidoCantado);

		Envido envidoCantado = new Envido(new Tanto());
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

	public void seCantoFlor(){
		if(!this.esPrimeraVuelta()) throw new VueltaParaCantarTantoNoPosibleException();
		if(this.yaSeCantoEnvido()) throw new TantoYaCantadoException();
		//verificar que en la mano se tengan tres cartas del mismo palo
		AccionFlor florCantada = new Flor();
		Accion flor = this._partido.getManejadorDeRonda().cantarFlor(florCantada);

		this.getVueltas().peek().getAccionesEnvido().add(flor);
	}

	public void agregarPuntajes(){
	    this.agregarPuntajeDeTruco();
		this.agregarPuntajeDeTanto();
	}

	public Jugador getJugadorActual() {
		return this.getVueltaActual().getJugadorActual();
	}


//	public void finalizarRonda(){
//	}

}
