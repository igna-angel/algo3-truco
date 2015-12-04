package com.modelo.acciones.envido;

import java.util.Arrays;
import java.util.List;

import com.modelo.Jugador;
import com.modelo.Ronda;

public class RealEnvido extends TantoDecorator {
	
	private String _nombreAccion;

	public RealEnvido(AccionTanto accionDecorar, Jugador origen, Jugador destino, Ronda rondaActual) {
		super(accionDecorar, origen, destino);
		this._nombreAccion = "real envido";
		List<String> acciones = Arrays.asList("falta envido", "quiero tanto", "no quiero tanto");
		rondaActual.setAccionesPosibles(acciones);
	}

	public int cantar(){
		return super.cantar() + 3;
	}
	
	@Override
	public String getNombreAccion() {
		return this._nombreAccion;
	}

}
