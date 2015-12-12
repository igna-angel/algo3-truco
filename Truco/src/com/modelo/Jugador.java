package com.modelo;

import java.util.List;
import com.exceptions.NoContieneCartaException;
import com.modelo.cartas.Carta;


public abstract class Jugador implements IRecibible{
	
	protected Mano _mano;
	
	public Jugador(){
		this._mano = new Mano();
	}
	
	protected Mano getMano(){
		return this._mano;
	}
	
	public int getPosiconDeCarta(Carta carta){
		return this.getMano().getPosiconDeCarta(carta);
	}
	
	public void recibirCarta(Carta carta){
		this.getMano().recibirCarta(carta);
	}
	
	public int getCantidadCartas(){
		return this.getMano().getCantidadCartas();
	}
	
	public int getCantidadCartasEnMano(){
		return this.getMano().getCantidadCartasEnMano();
	}
	
	public List<Carta> getCartasEnMano(){
		return this.getMano().getCartasEnMano();
	}
	
	public List<Carta> getListaDeCartasEnMano(){
		return this.getMano().getCartas();
	}
	
	public void imprimirCartas(){
		this.getMano().getCartas().forEach(carta ->
			{
				System.out.println("Palo: " + carta.getPalo() + " Numero: " + carta.getNumero());
			}
		);
	}
	
	public void bajarCarta(Vuelta vuelta, Carta carta){
		try{
			this.getMano().bajarCarta(carta);
			vuelta.recibirCarta(carta);
		}catch(NoContieneCartaException e){
			// relanzar exception o dejarla pasar y catchear en otro lado
		}
	}
		
	public void devolverCartas(){
		this.getMano().devolverCartas();
	}

	public int getTantoEnMano(){
		return this.getMano().getMaximosPuntosEnvido();
	}

	public abstract void jugar(Vuelta vuelta);

	public boolean tieneCarta(Carta cartaGanadora) {
		return this.getMano().contiene(cartaGanadora);
	}
	
	public boolean hayFlor(){
		return this.getMano().hayFlor();
	}

	public int getFlorEnMano() {
		if(!this.hayFlor()) return 0;
		
		return this.getMano().getPuntosFlorEnMano();
	}
}
