package com.modelo;

import java.util.List;
import java.util.Stack;

import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;

public abstract class Ronda {

	private Partido _partido;
	private Jugador _repartio;	
	private boolean trucoCantado;
	private boolean reTrucoCantado;
	private boolean valeCuatroCantado;
	
	private Stack<Vuelta> _vueltas; // IMPLEMENTAR VUELTAS
	
	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
		this._vueltas = new Stack<Vuelta>();
		this.trucoCantado = false;
		this.reTrucoCantado = false;
		this.valeCuatroCantado = false;
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
		if (!trucoCantado){
			Accion truco = this._partido.getManejadorDeRonda().cantarTruco(this._partido);
			this.getVueltas().peek().getAccionesTruco().add(truco);
			this.trucoCantado = true;
		} else {
			throw new TrucoYaCantadoException();
		}
	}	
	
	public void seCantoReTruco(){
		if (!reTrucoCantado){
			Accion reTruco = this._partido.getManejadorDeRonda().cantarReTruco(trucoCantado, this._partido);
			this.getVueltas().peek().getAccionesTruco().add(reTruco);
			this.reTrucoCantado = true;
		} else {
			throw new ReTrucoYaCantadoException();
		}
	}
	
	public void elReTrucoFueCantado(){
		reTrucoCantado = true;
	}
	
	public void elValeCuatroFueCantado(){
		valeCuatroCantado = true;
	}
	
	public void seCantoValeCuatro(){
		if (!valeCuatroCantado){
			Accion valeCuatro = this._partido.getManejadorDeRonda().cantarValeCuatro(reTrucoCantado, this._partido);
			this.getVueltas().peek().getAccionesTruco().add(valeCuatro);
			this.valeCuatroCantado = true;
		} else {
			throw new ValeCuatroYaCantadoException();
		}
	}
	
	public void agregarPuntaje(){

		int posAccionFinal = this.getAccionFinal().size();
		
		Accion accionFinal = this._vueltas.peek().getAccionesTruco().get(posAccionFinal-1);
		
		int puntajeFinal = accionFinal.cantar();
		int puntajeNulo = 0;
		
		this._partido.agregarPuntos(puntajeFinal, puntajeNulo);
	}
	
	private List<Accion> getAccionFinal() {
		if (!this._vueltas.peek().getAccionesTruco().isEmpty()){
			return this._vueltas.peek().getAccionesTruco();
		} else {
			this._vueltas.pop();
			return this.getAccionFinal();
		}
	}
	
//	public void finalizarRonda(){
//		trucoCantado = false;
//		reTrucoCantado = false;
//		valeCuatroCantado = false;
//	}

}
