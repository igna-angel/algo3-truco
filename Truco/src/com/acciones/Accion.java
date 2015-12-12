package com.acciones;

import java.util.List;

import com.exceptions.AccionDummyException;
import com.exceptions.EstadoIndefinidoException;
import com.exceptions.JugadorNoPuedeCantarAccionConcatenadaConsecutivamenteException;
import com.exceptions.NoExisteAccionException;
import com.exceptions.NoHayAccionesException;
import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.Vuelta;

public abstract class Accion{
	
	public static final String ESTADO_ACEPTADO = "Aceptado";
	public static final String ESTADO_NEGADO = "NEGADO";
	public static final String ESTADO_INDEFINIDO = "Indefinido";
	
	public static final int PUNTOS_TRUCO = 2;
	public static final int PUNTOS_RE_TRUCO = 3;
	public static final int PUNTOS_VALE_CUATRO = 4;
	
	public static final int PUNTOS_ENVIDO = 2;
	public static final int PUNTOS_ENVIDO_ENVIDO = 2;
	public static final int PUNTOS_REAL_ENVIDO = 3;
	
	public static final int PUNTOS_FLOR = 3;
	public static final int PUNTOS_CONTRA_FLOR = 3;
	
	public static final int PUNTOS_NO_QUERIDO = 1;	
	public static final int PUNTOS_NO_TRUCO = 1;
	
	public static final String ACCION_TRUCO = "Truco";
	public static final String ACCION_RE_TRUCO = "Re Truco";
	public static final String ACCION_VALE_CUATRO = "Vale Cuatro";
	public static final String ACCION_ENVIDO = "Envido";
	public static final String ACCION_ENVIDO_ENVIDO = "Envido Envido";
	public static final String ACCION_REAL_ENVIDO = "Real Envido";
	public static final String ACCION_FALTA_ENVIDO = "Falta Envido";
	public static final String ACCION_FLOR = "Flor";
	public static final String ACCION_CONTRA_FLOR = "Contra Flor";
	public static final String ACCION_CONTRA_FLOR_AL_RESTO = "Contra Flor Al Resto";
	public static final String ACCION_DUMMY = "Dummy";
	public static final String ACCION_NO_TRUCO = "No Truco";
		
	private List<Accion> _accionesPosibles = null;
	
	private Jugador _origen = null; 
	private Jugador _destino = null; 
	
	private Accion _decorada = null;
	
	protected int _puntos = 0;
	
	protected EstadoAccion _estado = new EstadoIndefinido();

	public Accion(){
		this(null, null, null, 0);
	}
	
	public Accion(Jugador origen, Jugador destino){
		this(origen, destino, 0);
	}
	
	public Accion(Jugador origen, Jugador destino, int puntos){
		this(null, origen, destino, puntos);
	}
		
	protected Accion(Accion decorada, Jugador origen, Jugador destino, int puntos){
		this.setDecorada(decorada);
		this.setOrigenDestino(origen, destino);
		this._puntos = puntos;
		this._estado = new EstadoIndefinido();
	}
	
	public void setOrigenDestino(Jugador origen, Jugador destino){
		this._origen = origen;
		this._destino = destino;
	}
	
	public Jugador getOrigen(){
		return this._origen;
	}
	
	public Jugador getDestino(){
		return this._destino;
	}
	
	protected void setAccionesPosibles(List<Accion> accionesPosibles){
		this._accionesPosibles = accionesPosibles;
	}
	
	public List<Accion> getAccionesPosibles(){
		if(this._accionesPosibles.isEmpty()) throw new NoHayAccionesException();
		return this._accionesPosibles;
	}
	
	public void setDecorada(Accion decorada){
		this._decorada = decorada;
	}
	
	protected Accion getDecorada(){
		return this._decorada;
	}
	
	protected int getCantidadDecoradas(){
		return this.getDecorada().getCantidadDecoradas() + 1;
	}
	
	public abstract int getPuntosQueridos();

	public abstract int getPuntosQueridos(Partido partido);

	public abstract int getPuntosQueridos(Partido partido, Equipo equipo);

	public abstract int getPuntosNoQueridos();

	public abstract String getID();
	
	public boolean puedePedirNuevaAccion(Partido partido, Jugador nuevoOrigen){
		if(this.getDecorada().getOrigen() == null) return true;
		if(partido.getEquipoDeJugador(this.getDecorada().getOrigen()) == partido.getEquipoDeJugador(nuevoOrigen)) return false;
		return true;
	}
	
	public Accion getNuevaAccion(Accion accion, Jugador origen, Jugador destino, Partido partido){
		if(!this.puedePedirNuevaAccion(partido, origen)) throw new JugadorNoPuedeCantarAccionConcatenadaConsecutivamenteException();
		accion.setDecorada(this);
		accion.setOrigenDestino(origen, destino);
		return accion;
	}
	
	public Accion getAccionPosible(String id){
		for(Accion accion : this.getAccionesPosibles()){
			if(accion.getID().equals(id))
				return accion;
		}
		
		throw new NoExisteAccionException();
	}

	public void aceptar(){
		
		System.out.println("esta accion: " + this.getID() + " la decorada: " + this.getDecorada());
		
		this.getDecorada().indefinir();
		this._estado = new EstadoAceptado();
	}
	
	public void indefinir(){
		this._estado = new EstadoIndefinido();
	}
	
	public void negar(){
		this.getDecorada().indefinir();
		this._estado = new EstadoNegado();
	}
	
	public EstadoAccion getEstado(){
		return this._estado;
	}
	
	public void procesarAccion(Partido partido, Ronda ronda){
		System.out.println("Procesando accion " + this.getID());
		
		this.getEstado().procesar(this, partido, ronda);
	}
	
	protected void procesarAccion(EstadoIndefinido estado, Partido partido, Ronda ronda){
		throw new EstadoIndefinidoException();
	}
	
	protected abstract void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda);
	protected abstract void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda);
	
	public abstract void limpiarAccionesRelacionadasEnVuelta(Vuelta vuelta);
	
	public void reemplazarAccionOriginalEnVuelta(Vuelta vuelta){
		try{
			this.getDecorada().reemplazarAccionOriginalEnVuelta(vuelta);
				
			if(!vuelta.getAccionesDeVuelta().contains(this)){
				System.out.println("vuelta no contiene a: " + this.getID() + " contiene decorada: " + vuelta.getAccionesDeVuelta().contains(this.getDecorada()));
				if(vuelta.getAccionesDeVuelta().contains(this.getDecorada())){
					System.out.println("vuelta contiene a la decorada de: " + this.getID() + ", decorada: " + this.getDecorada().getID());
					if(!this.getDecorada().getID().equals(Accion.ACCION_DUMMY)){
						int indiceDecorada = vuelta.getAccionesDeVuelta().indexOf(this.getDecorada());
						vuelta.getAccionesDeVuelta().set(indiceDecorada, this);
					}
				}
			}
			
			try{
				for(Accion accion : this.getAccionesPosibles()){
					if(!vuelta.getAccionesDeVuelta().contains(accion))
						vuelta.getAccionesDeVuelta().add(accion);
				}
			}catch(NoHayAccionesException e){
				System.out.println(e.getClass());
			}
			
		}catch(AccionDummyException e){
			System.out.println(e.getClass());
		}		
		
		//this.limpiarAccionesRelacionadasEnVuelta(vuelta);
	}
	
	public abstract void iniciarNuevaRondaSiCorresponde();
}
