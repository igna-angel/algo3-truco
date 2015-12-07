package com.modelo.acciones.truco;

import com.exceptions.ReTrucoNoCantadoException;
import com.exceptions.TrucoNoCantadoException;

public class TrucoNormal implements AccionTruco {


	@Override
	public int cantar() {
		return 0;
	}

	@Override
	public boolean trucoNoCantadoPreviamente() {
		return true;
	}

	@Override
	public boolean trucoOReTrucoCantadoPreviamente() {
		throw new TrucoNoCantadoException();
	}

	@Override
	public boolean reTrucoOValeCuatroCantadoPreviamente() {
		throw new ReTrucoNoCantadoException();
	}

}
