package com.modelo.acciones.truco;

import com.exceptions.ReTrucoYaCantadoException;
import com.exceptions.TrucoYaCantadoException;
import com.exceptions.ValeCuatroYaCantadoException;

public class TrucoDecorator implements AccionTruco{

	protected AccionTruco accionADecorar;
	
	public TrucoDecorator(AccionTruco accionDecorar){
		this.accionADecorar = accionDecorar;
	}
	
	@Override
	public int cantar() {
		return this.accionADecorar.cantar();
	}

	@Override
	public boolean trucoNoCantadoPreviamente() {
		throw new TrucoYaCantadoException();
	}
	
	@Override
	public boolean trucoOReTrucoCantadoPreviamente() {
		if (this.accionADecorar.cantar() == 2){
			return true;
		} else {
			throw new ReTrucoYaCantadoException();
		}
	}

	@Override
	public boolean reTrucoOValeCuatroCantadoPreviamente() {
		if (this.accionADecorar.cantar() == 3){
			return true;
		} else {
			throw new ValeCuatroYaCantadoException();
		}
	}

}
