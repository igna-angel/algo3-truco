package com.modelo;

import java.util.Scanner;

public class JugadorHumano extends Jugador{
			
	public JugadorHumano(){
		super();
	}

	@Override
	public void jugar(Vuelta vuelta) {
<<<<<<< HEAD
		Scanner scan = new Scanner(System.in);
		int cartasEnMano = this.getMano().getCantidadCartasEnMano();
		System.out.println("Jugar Humano");
		while(cartasEnMano == this.getMano().getCantidadCartasEnMano()){
			System.out.println("Jugar pre scanner");
			if (scan.hasNext())
				{scan.next();}
			System.out.println("post scanner");
=======
		int cartasEnMano = this.getMano().getCartasEnMano();
		
		while(cartasEnMano == this.getMano().getCartasEnMano()){
			System.out.println("Turno Jugador");
>>>>>>> origin/master
		}
	}
}
	
	
	
