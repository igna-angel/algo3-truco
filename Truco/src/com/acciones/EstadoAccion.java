package com.acciones;

import com.modelo.Partido;
import com.modelo.Ronda;

public interface EstadoAccion {

	void procesar(Accion accion, Partido partido, Ronda ronda);

	String getID();
}
