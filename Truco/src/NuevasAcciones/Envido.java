package NuevasAcciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;

public class Envido extends Accion{

	public Envido(Jugador origen, Jugador destino){
		super(origen, destino, Accion.PUNTOS_ENVIDO);
		this.setDecorada(new AccionDummy());
		List<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new EnvidoEnvido(origen, destino));
		acciones.add(new RealEnvido(origen, destino));
		acciones.add(new FaltaEnvido(origen, destino));
		this.setAccionesPosibles(acciones);
	}
	
	@Override
	public int getPuntosQueridos() {
		return this.getDecorada().getPuntosQueridos() + this._puntos;
	}

	@Override
	public int getPuntosNoQueridos() {
		return this.getDecorada().getPuntosQueridos() + Accion.PUNTOS_NO_QUERIDO;
	}

	@Override
	public String getID() {
		return Accion.ACCION_ENVIDO;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		int tantoEquipoA = ronda.getMayorTantoDeEquipo(partido.getEquipos().getFirst());
		int tantoEquipoB = ronda.getMayorTantoDeEquipo(partido.getEquipos().getLast());
				
		if(tantoEquipoA > tantoEquipoB){
			partido.agregarPuntosAlEquipo(partido.getEquipos().getFirst(), this.getPuntosQueridos());
		} else {
			partido.agregarPuntosAlEquipo(partido.getEquipos().getLast(), this.getPuntosQueridos());
		}
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(partido.getEquipoDeJugador(this.getOrigen()), this.getPuntosNoQueridos());
	}
}
