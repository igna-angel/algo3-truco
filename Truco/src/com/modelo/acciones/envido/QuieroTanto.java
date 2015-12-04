package com.modelo.acciones.envido;

import java.util.Arrays;
import java.util.List;

import com.modelo.Jugador;
import com.modelo.Ronda;

public class QuieroTanto extends TantoDecorator {
	
	private String _nombreAccion;

	public QuieroTanto(AccionTanto accionDecorar,  Jugador origen, Jugador destino, Ronda rondaActual) {
		super(accionDecorar, origen, destino);
		this._nombreAccion = "quiero tanto";
		List<String> acciones = Arrays.asList("truco");
		rondaActual.setAccionesPosibles(acciones);
	}
	
	public int cantar(){
		return super.cantar();
	}
	
	@Override
	public String getNombreAccion() {
		return this._nombreAccion;
	}

}
