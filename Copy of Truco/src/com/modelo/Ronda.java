package com.modelo;

public abstract class Ronda {

	private Partido _partido;
	private Jugador _repartio;	
	
	public Ronda(Partido partido, Jugador reparte){
		this._partido = partido;
		this._repartio = reparte;
	}
	
	public abstract Ronda getRondaSiguiente(boolean esPicaPica);
	
	public void asignarPuntos(int puntajeA, int puntajeB){
		this.getPartido().agregarPuntos(puntajeA, puntajeB);
	}
	
	protected Partido getPartido(){
		return this._partido;
	}
	
	public void repartir(int[] mazoMezclado, int cartasPorJugador){
		
		//this.getPartido().getMazo().imprimir(mazoMezclado);//TEST
		
		CircularList<Jugador> ordenJugadores = this.getPartido().getOrdenJugadores();
			
		ordenJugadores.moveCursorTo(ordenJugadores.getIndexOf(this.getRepartio()));
		
		int cartasARepartir = this.getPartido().getCantidadDeJugadoresTotales() * cartasPorJugador;
		
		for(int i = 0; i < cartasARepartir; i++){
			ordenJugadores.advanceCursor();
			ordenJugadores.getCurrent().recibirCarta(this.getPartido().getMazo().getCarta(mazoMezclado[i]));			
		}
	}
	
	public Jugador getRepartio(){
		return this._repartio;
	}

	public Jugador getProximoEnRepartir(){
		this.getPartido().getOrdenJugadores().resetToFirst();
		int posicionAMover = this.getPartido().getOrdenJugadores().getIndexOf(this.getRepartio());
		this.getPartido().getOrdenJugadores().moveCursorTo(posicionAMover);
		this.getPartido().getOrdenJugadores().advanceCursor();
		return this.getPartido().getOrdenJugadores().getCurrent();
	}
}
