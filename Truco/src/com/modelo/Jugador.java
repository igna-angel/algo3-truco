package com.modelo;

import java.util.Scanner;

import com.exceptions.AccionNoPosibleException;
import com.modelo.acciones.truco.*;
import com.modelo.cartas.Carta;

public abstract class Jugador implements IRecibible{
	
	protected Mano _mano;
	
	public Jugador(){
		this._mano = new Mano();
	}
	
	protected Mano getMano(){
		return this._mano;
	}
	
	public void recibirCarta(Carta carta){
		this.getMano().recibirCarta(carta);
	}
	
	public int getCantidadCartas(){
		return this.getMano().getCantidadCartas();
	}
	
	public void imprimirCartas(){
		this.getMano().getCartas().forEach(carta ->
			{
				System.out.println("Palo: " + carta.getPalo() + " Numero: " + carta.getNumero());
			}
		);
	}
	
	public void bajarCarta(Vuelta vuelta, Carta carta){
		this.getMano().quitarCarta(carta);
		vuelta.recibirCarta(carta);
	}
		
	public void devolverCartas(){
		this.getMano().devolverCartas();
	}

	public Accion responderA(AccionTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		
		System.out.print("Quiere truco: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else 
		
		if (respuesta.equals("retruco")){
			return manejadorDeRonda.cantarReTruco(true,partido);
		} else {
			throw new AccionNoPosibleException();
		}
	}	
	
	public Accion responderA(ReTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		
		System.out.print("Quiere ReTruco: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else 
		
		if (respuesta.equals(("valecuatro"))){
			return manejadorDeRonda.cantarValeCuatro(true,partido);
		} else {
			throw new AccionNoPosibleException();
		}
	}	
	
	public Accion responderA(ValeCuatro accion) {
		
		System.out.print("Quiere ValeCuatro: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else {
			throw new AccionNoPosibleException();
		}
	}	
}
