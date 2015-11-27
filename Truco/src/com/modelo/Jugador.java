package com.modelo;

import java.util.Scanner;

import com.exceptions.AccionNoPosibleException;
import com.modelo.acciones.envido.*;
import com.modelo.acciones.flor.*;
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
			return manejadorDeRonda.cantarReTruco(new ReTruco(accion),partido);
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
			return manejadorDeRonda.cantarValeCuatro(new ValeCuatro(accion),partido);
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

	public Accion responderA(Envido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Quiere Envido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			return new QuieroTanto(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuieroTanto(accion);
		} else 
		
		if ((respuesta.equals("envido")) && (this.accionEnvidoValida(accion))){
			return manejadorDeRonda.cantarEnvido((new Envido(new QuieroTanto(accion))),partido);
		} else if (respuesta.equals(("realenvido"))){
			return manejadorDeRonda.cantarRealEnvido(new RealEnvido(accion),partido);
		} else if (respuesta.equals(("faltaenvido"))){
			return manejadorDeRonda.cantarFaltaEnvido(new FaltaEnvido(accion),partido);
		} else {
			throw new AccionNoPosibleException();
		}
	}

	private boolean accionEnvidoValida(Envido accion) {
		if (accion.cantar() < 3){
			return true;
		} else {
			return false;
		}
	}

	public Accion responderA(RealEnvido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Quiere RealEnvido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			return new QuieroTanto(accion);
		} else
		if (respuesta.equals("noquiero")){
			return new NoQuieroTanto(accion);
		} else if (respuesta.equals(("faltaenvido"))){
			return manejadorDeRonda.cantarFaltaEnvido(new FaltaEnvido(accion),partido);
		} else {
			throw new AccionNoPosibleException();
		}
	}

	public Accion responderA(FaltaEnvido accion) {
		System.out.print("Quiere FaltaEnvido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			return new QuieroTanto(accion);
		} else if (respuesta.equals("noquiero")){
			return new NoQuieroTanto(accion);
		} else {
			throw new AccionNoPosibleException();
		}
	}

	public Accion responderA(AccionFlor accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Respuesta Flor: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("contraflor")){
			return new ContraFlor(accion);
		} else if (respuesta.equals("contrafloralresto")){
			return new ContraFlorAlResto(accion);
		} else {
			throw new AccionNoPosibleException();
		}
	}	
}
