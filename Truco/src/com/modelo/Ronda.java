package com.modelo;

import java.util.EmptyStackException;
import java.util.Stack;

import com.exceptions.TantoYaCantadoException;
import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoNoCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;
import com.exceptions.VueltaParaCantarTantoNoPosibleException;
import com.modelo.acciones.envido.*;
import com.modelo.acciones.flor.AccionFlor;
import com.modelo.acciones.flor.Flor;
import com.modelo.acciones.truco.*;

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
	
	protected Stack<Vuelta> getVueltas(){
		return this._vueltas;
	}
	
//	public void nuevaVuelta(List<Accion> acciones){
//		this.getVueltas().push(new Vuelta(this, acciones));
//	}
	
	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}
	

	public void nuevaVuelta() {
		this.getVueltas().push(new Vuelta(this));
	}
	
	public void seCantoTruco(){
		this.trucoNoCantadoPreviamente();
		AccionTruco trucoCantado = new Truco();
		Accion truco = this._partido.getManejadorDeRonda().cantarTruco(trucoCantado,this._partido);
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
		Accion reTruco = this._partido.getManejadorDeRonda().cantarReTruco(reTrucoCantado, this._partido);
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
		Accion valeCuatro = this._partido.getManejadorDeRonda().cantarValeCuatro(valeCuatroCantado, this._partido);
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
		this.verificarQueSeaLaPrimeraVuelta();
		this.verificarQueNoSeHayaCantadoPreviamente();
		Envido envidoCantado = new Envido(new Tanto());
		Accion envido = this._partido.getManejadorDeRonda().cantarEnvido(envidoCantado,this._partido);
		this.getVueltas().peek().getAccionesEnvido().add(envido);
	}
	
	private void verificarQueSeaLaPrimeraVuelta() {
		if (this._vueltas.size() == 1){
			return;
		} else {
			throw new VueltaParaCantarTantoNoPosibleException();
		}
	}

	public void seCantoRealEnvido(){
		this.verificarQueSeaLaPrimeraVuelta();
		this.verificarQueNoSeHayaCantadoPreviamente();
		RealEnvido realEnvidoCantado = new RealEnvido(new Tanto());
		Accion realEnvido = this._partido.getManejadorDeRonda().cantarRealEnvido(realEnvidoCantado,this._partido);
		this.getVueltas().peek().getAccionesEnvido().add(realEnvido);
	}
	
	private void verificarQueNoSeHayaCantadoPreviamente() {
		if (this._vueltas.peek().getAccionesEnvido().isEmpty()){
			return;
		} else {
			throw new TantoYaCantadoException();
		}
	}

	public void seCantoFaltaEnvido(){
		this.verificarQueSeaLaPrimeraVuelta();
		this.verificarQueNoSeHayaCantadoPreviamente();
		FaltaEnvido faltaEnvidoCantado = new FaltaEnvido(new Tanto());
		Accion faltaEnvido = this._partido.getManejadorDeRonda().cantarFaltaEnvido(faltaEnvidoCantado,this._partido);
		this.getVueltas().peek().getAccionesEnvido().add(faltaEnvido);
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

	public void seCantoFlor() {
		// GENERAR		
	}
	
	public void seCantoFlor(){
		this.verificarQueSeaLaPrimeraVuelta();
		this.verificarQueNoSeHayaCantadoPreviamente();
		//verificar que en la mano se tengan tres cartas del mismo palo
		AccionFlor florCantada = new Flor();
		Accion flor = this._partido.getManejadorDeRonda().cantarFlor(florCantada,this._partido);
		this.getVueltas().peek().getAccionesEnvido().add(flor);
	}
	
	public void agregarPuntajes(){
		this.agregarPuntajeDeTanto();
		this.agregarPuntajeDeTruco();
	}
	
//	public void finalizarRonda(){
//	}

}
