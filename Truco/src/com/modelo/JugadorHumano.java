package com.modelo;

public class JugadorHumano extends Jugador{
			
	public JugadorHumano(){
		super();
	}

	@Override
	public void jugar(Vuelta vuelta) {
		int cartasEnMano = this.getMano().getCartasEnMano();
		
		while(cartasEnMano == this.getMano().getCartasEnMano()){
			//ACA EJECUTA ADRIANO LOS INPUT
		}
	}
}
	
	
	
