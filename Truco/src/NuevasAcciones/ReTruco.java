package NuevasAcciones;

import java.util.ArrayList;
import java.util.List;

import com.modelo.Equipo;
import com.modelo.Jugador;
import com.modelo.Partido;
import com.modelo.Ronda;

public class ReTruco extends Accion{

	public ReTruco(Jugador origen, Jugador destino){
		super(origen, destino, Accion.PUNTOS_RE_TRUCO);
		List<Accion> acciones = new ArrayList<Accion>();
		acciones.add(new ValeCuatro(origen, destino));
		this.setAccionesPosibles(acciones);
	}

	@Override
	public String getID() {
		return "Re Truco";
	}
	
	@Override
	public int getPuntosQueridos(){
		return this._puntos;
	}
	
	@Override
	public int getPuntosNoQueridos(){
		return this.getCantidadDecoradas() + Accion.PUNTOS_NO_QUERIDO;
	}	

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		partido.agregarPuntosAlEquipo(ronda.getEquipoGanador(), this.getPuntosQueridos());		
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		Equipo equipoOrigen = partido.getEquipoDeJugador(this.getOrigen());
		
		partido.agregarPuntosAlEquipo(equipoOrigen, this.getPuntosNoQueridos());		
		
	}

	@Override
	public int getPuntosQueridos(Partido partido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntosQueridos(Partido partido, Equipo equipo) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
