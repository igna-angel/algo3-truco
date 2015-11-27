package com.modelo;

import java.util.EmptyStackException;
import java.util.Stack;

import com.exceptions.EnvidoYaCantadoException;
import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoNoCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;
import com.exceptions.VueltaParaCantarTantoNoPosibleException;
import com.modelo.acciones.envido.*;
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
		if (this.trucoNoCantadoPreviamente()){
			AccionTruco trucoCantado = new Truco();
			Accion truco = this._partido.getManejadorDeRonda().cantarTruco(trucoCantado,this._partido);
			this.getVueltas().peek().getAccionesTruco().add(truco);
		} else {
			throw new TrucoYaCantadoException();
		}
		//verificar si es no querido y finalizar ronda
	}	
	
	private boolean trucoNoCantadoPreviamente() {
		
		if(this._vueltas.peek().getAccionesTruco().isEmpty()){
			return true;
		} else {
			return false;
		}
	}

	public void seCantoReTruco(){
		if (this.ReTrucoNoCantadoPreviamente()){
			ReTruco reTrucoCantado = new ReTruco(this.getTruco());
			Accion reTruco = this._partido.getManejadorDeRonda().cantarReTruco(reTrucoCantado, this._partido);
			this.getVueltas().peek().getAccionesTruco().add(reTruco);
		} else {
			throw new ReTrucoYaCantadoException();
		}
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
	
	private boolean ReTrucoNoCantadoPreviamente() {
		if (this.getTruco().cantar() == 3
		 || this.getTruco().cantar() == 4){
			return false;
		} else {
			return true;
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

	public void agregarPuntajeDeTruco(){
		int posAccionFinal = this.getPosAccionFinalTruco();
		
		Accion accionFinal = this._vueltas.peek().getAccionesTruco().get(posAccionFinal-1);
		
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
			throw new EnvidoYaCantadoException();
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
	
	private void agregarPuntajeDeEnvidoNormal(Accion accionFinal) {
		
		int puntajeFinal = accionFinal.cantar();
		int puntajeNulo = 0;
		
		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}
	
	public void agregarPuntajeDeEnvido(){
		int posAccionFinal = this.getPosAccionFinalEnvido();
		Accion accionFinal = this._vueltas.peek().getAccionesEnvido().get(posAccionFinal-1);
	
		if (accionFinal.cantar() < 30){
			this.agregarPuntajeDeEnvidoNormal(accionFinal);
		} else {
			this.agregarPuntajeDeEnvidoEspecial();
		}
	}

	private void agregarPuntajeDeEnvidoEspecial() {
		
		int puntajeFinal = this._partido.getcantidadDePuntosFaltantes();
		int puntajeNulo = 0;
		
		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}
	
//	public void finalizarRonda(){
//	}

}
