package com.modelo.acciones.envido;

import java.util.Arrays;
import java.util.List;

import com.modelo.Jugador;
import com.modelo.Ronda;

public class NoQuieroTanto extends TantoDecorator {
	
	private String _nombreAccion;

	public NoQuieroTanto(AccionTanto accionDecorar, Jugador origen, Jugador destino, Ronda rondaActual) {
		super(accionDecorar, origen, destino);
		this._nombreAccion = "no quiero tanto";
		List<String> acciones = Arrays.asList("truco");
		rondaActual.setAccionesPosibles(acciones);
	}
	
	public int cantar(){
		if (esEnvidoComun()){
			return super.cantar() - 1;
		} else if (esRealEnvido()){
			return super.cantar() - 2;
		} else if (esEnvidoEnvido()){
			return super.cantar() - 2;
		} else if (esEnvidoRealEnvido()){
			return super.cantar() - 3;
		} else if (esEnvidoEnvidoRealEnvido()){
			return super.cantar() - 4;
		} else if (esEnvidoFaltaEnvido()){
			return super.cantar() - 30;
		} else if (esRealEnvidoFaltaEnvido()){
			return super.cantar() - 31;
		} else if (esEnvidoEnvidoFaltaEnvido()){
			return super.cantar() - 31;
		} else if (esEnvidoRealEnvidoFaltaEnvido()){
			return super.cantar() - 32;
		} else if (esEnvidoEnvidoRealEnvidoFaltaEnvido()){
			return super.cantar() - 33;
		} else /*esFaltaEnvidoComun*/{
			return super.cantar() - 29;
		}
	}
	
	private boolean esEnvidoComun(){
		return super.cantar() == 2;
	}
	
	private boolean esRealEnvido(){
		return super.cantar() == 3;
	}
	
	private boolean esEnvidoEnvido(){
		return super.cantar() == 4;
	}
	
	private boolean esEnvidoRealEnvido(){
		return super.cantar() == 5;
	}
	
	private boolean esEnvidoEnvidoRealEnvido(){
		return super.cantar() == 7;
	}
	
	private boolean esEnvidoFaltaEnvido(){
		return super.cantar() == 32;
	}
	
	private boolean esRealEnvidoFaltaEnvido(){
		return super.cantar() == 33;
	}
	
	private boolean esEnvidoEnvidoFaltaEnvido(){
		return super.cantar() == 34;
	}
	
	private boolean esEnvidoRealEnvidoFaltaEnvido(){
		return super.cantar() == 35;
	}
	
	private boolean esEnvidoEnvidoRealEnvidoFaltaEnvido(){
		return super.cantar() == 37;
	}
	
	@Override
	public String getNombreAccion() {
		return this._nombreAccion;
	}
	
}
