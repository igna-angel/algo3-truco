package NuevasAcciones;

import java.util.List;

import com.exceptions.EstadoIndefinidoException;
import com.exceptions.NoExisteAccionException;
import com.exceptions.NoHayAccionesException;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;

public abstract class Accion{
	
	public static final int PUNTOS_TRUCO = 2;
	public static final int PUNTOS_RE_TRUCO = 3;
	public static final int PUNTOS_VALE_CUATRO = 4;
	public static final int PUNTOS_ENVIDO = 2;
	public static final int PUNTOS_ENVIDO_ENVIDO = 2;
	public static final int PUNTOS_REAL_ENVIDO = 3;
	public static final int PUNTOS_NO_QUERIDO = 1;
	
	public static final String ACCION_TRUCO = "Truco";
	public static final String ACCION_RE_TRUCO = "Re Truco";
	public static final String ACCION_VALE_CUATRO = "Vale Cuatro";
	public static final String ACCION_ENVIDO = "Envido";
	public static final String ACCION_ENVIDO_ENVIDO = "Envido Envido";
	public static final String ACCION_REAL_ENVIDO = "Real Envido";
	public static final String ACCION_FALTA_ENVIDO = "Vale Cuatro";
	
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
	
	protected Jugador getOrigen(){
		return this._origen;
	}
	
	protected Jugador getDestino(){
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

	public abstract int getPuntosNoQueridos();

	public abstract String getID();
	
	public Accion getNuevaAccion(Accion accion, Jugador origen, Jugador destino){
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
		this._estado = new EstadoAceptado();
	}
	
	public void negar(){
		this._estado = new EstadoNegado();
	}
	
	private EstadoAccion getEstado(){
		return this._estado;
	}
	
	public void procesarAccion(Partido partido, Ronda ronda){
		this.getEstado().procesar(this, partido, ronda);
	}
	
	protected void procesarAccion(EstadoIndefinido estado, Partido partido, Ronda ronda){
		throw new EstadoIndefinidoException();
	}
	protected abstract void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda);
	protected abstract void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda);
}