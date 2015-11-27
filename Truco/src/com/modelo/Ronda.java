package com.modelo;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoNoCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;
import com.modelo.acciones.envido.*;
import com.modelo.acciones.truco.*;

public abstract class Ronda {

	private Partido _partido;
	private Jugador _repartio;	
	
	private Stack<Vuelta> _vueltas; // IMPLEMENTAR VUELTAS
	
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
	
	public void nuevaVuelta(List<Accion> acciones){
		this.getVueltas().push(new Vuelta(this, acciones));
	}
	
	public int getCantidadDeJugadoresTotales(){
		return this.getPartido().getCantidadDeJugadoresTotales();
	}
	

	public void nuevaVuelta() {
		this.getVueltas().push(new Vuelta(this));
	}
	
	public void seCantoTruco(){
		AccionTruco trucoCantado = new Truco();
		Accion truco = this._partido.getManejadorDeRonda().cantarTruco(trucoCantado,this._partido);
		this.getVueltas().peek().getAccionesTruco().add(truco);
		//verificar si es no querido y finalizar ronda
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
				throw new ReTrucoNoCantadoException();
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
	
	private List<Accion> getAccionFinalEnvido() {
		if (!this._vueltas.peek().getAcciones().isEmpty()){
			return this._vueltas.peek().getAcciones();
		} else {
			this._vueltas.pop();
			return this.getAccionFinalEnvido();
		}
	}
	
	public void seCantoEnvido(){
		Envido envidoCantado = new Envido(new Tanto());
		Accion envido = this._partido.getManejadorDeRonda().cantarEnvido(envidoCantado,this._partido);
		this.getVueltas().peek().getAcciones().add(envido);
	}
	
	public void seCantoRealEnvido(){
		RealEnvido realEnvidoCantado = new RealEnvido(new Tanto());
		Accion envido = this._partido.getManejadorDeRonda().cantarRealEnvido(realEnvidoCantado,this._partido);
		this.getVueltas().peek().getAcciones().add(envido);
	}
	
	public void seCantoFaltaEnvido(){
		Accion envido = this._partido.getManejadorDeRonda().cantarFaltaEnvido(this._partido);
		this.getVueltas().peek().getAcciones().add(envido);
	}

	public void agregarPuntajeDeEnvido() {
		int posAccionFinal = this.getAccionFinalEnvido().size();
		
		Accion accionFinal = this._vueltas.peek().getAcciones().get(posAccionFinal-1);
		
		int puntajeFinal = accionFinal.cantar();
		int puntajeNulo = 0;
		
		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}
	
//	public void finalizarRonda(){
//		trucoCantado = false;
//		reTrucoCantado = false;
//		valeCuatroCantado = false;
//	}

}
