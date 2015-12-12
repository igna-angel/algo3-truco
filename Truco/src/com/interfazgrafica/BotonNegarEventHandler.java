package com.interfazgrafica;

import com.acciones.Accion;
import com.modelo.Equipo;
import com.modelo.Partido;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonNegarEventHandler implements EventHandler<ActionEvent> {

	private Accion accionBase;
	
	public BotonNegarEventHandler (Accion accionBase){		
		this.accionBase = accionBase;
	}
	
	public void handle (ActionEvent actionEvent){
		ImprimirTablero.getInstance().mostrarCartasJugador();
		ImprimirTablero.getInstance().negarAccion(accionBase);
		int puntosPorAccionNoQuerida = accionBase.getPuntosNoQueridos();
		Partido partido = ImprimirTablero.getInstance().getPartido();
		Equipo EquipoASumarPuntos = partido.getEquipoDeJugador(this.accionBase.getOrigen());
		partido.agregarPuntosAlEquipo(EquipoASumarPuntos, puntosPorAccionNoQuerida);
		accionBase.iniciarNuevaRondaSiCorresponde();
	}
}

