package NuevasAcciones;

import com.exceptions.AccionDummyException;
import com.modelo.Partido;
import com.modelo.Ronda;

public class AccionDummy extends Accion{

	public AccionDummy() {
		super();
	}
	
	@Override
	public int getCantidadDecoradas(){
		return 0;
	}
	
	@Override
	public String getID() {
		throw new AccionDummyException();
	}

	@Override
	public int getPuntosQueridos() {
		return 0;
	}

	@Override
	public int getPuntosNoQueridos() {
		return 0;
	}

	@Override
	protected void procesarAccion(EstadoAceptado estado, Partido partido, Ronda ronda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void procesarAccion(EstadoNegado estado, Partido partido, Ronda ronda) {
		// TODO Auto-generated method stub
		
	}

}
