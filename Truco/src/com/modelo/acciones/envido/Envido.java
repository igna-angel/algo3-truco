package com.modelo.acciones.envido;

import java.util.Arrays;
import java.util.List;
import com.modelo.Jugador;
import com.modelo.Ronda;

public class Envido extends TantoDecorator {
	
	private String _nombreAccion;

	public Envido(AccionTanto accionDecorar, Jugador origen, Jugador destino, Ronda rondaActual) {
		super(accionDecorar, origen, destino);
		this._nombreAccion = "envido";
		List<String> acciones = Arrays.asList("real envido", "falta envido", "quiero tanto", "no quiero tanto");
		if (!rondaActual.yaSeCantoEnvido()) {
			acciones.add("envido");
		}
		
		rondaActual.setAccionesPosibles(acciones);
		
	}
	
	public int cantar(){
		return super.cantar() + 2 ;
	}
	
	@Override
	public boolean accionEsPosible(Ronda rondaActual) {
		return accionADecorar.accionEsPosible(rondaActual);
	}
	
	@Override
	public String getNombreAccion() {
		return this._nombreAccion;
	}

}
