package com.modelo.acciones.truco;

import com.modelo.Accion;

public interface AccionTruco extends Accion{
	
	public abstract int cantar();

	public abstract boolean trucoNoCantadoPreviamente();

	public abstract boolean trucoOReTrucoCantadoPreviamente();

	public abstract boolean reTrucoOValeCuatroCantadoPreviamente();
}
