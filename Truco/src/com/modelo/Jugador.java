package com.modelo;

import java.util.Scanner;

import com.exceptions.AccionNoPosibleException;
import com.exceptions.NoContieneCartaException;
import com.modelo.acciones.envido.Envido;
import com.modelo.acciones.envido.EnvidoDecorator;
import com.modelo.acciones.envido.FaltaEnvido;
import com.modelo.acciones.envido.NoQuieroTanto;
import com.modelo.acciones.envido.QuieroTanto;
import com.modelo.acciones.envido.RealEnvido;
import com.modelo.acciones.flor.AccionFlor;
import com.modelo.acciones.flor.ContraFlor;
import com.modelo.acciones.flor.ContraFlorAlResto;
import com.modelo.acciones.truco.AccionTruco;
import com.modelo.acciones.truco.NoQuiero;
import com.modelo.acciones.truco.Quiero;
import com.modelo.acciones.truco.ReTruco;
import com.modelo.acciones.truco.ValeCuatro;
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
	
	public int getCartasEnMano(){
		return this.getMano().getCartasEnMano();
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

	public Accion responderA(AccionTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		
		System.out.print("Quiere truco: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		scan.close();

		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else 
		
		if (respuesta.equals("retruco")){
			return manejadorDeRonda.cantarReTruco(new ReTruco(accion));
		} else {
			throw new AccionNoPosibleException();
		}
	}	
	
	public Accion responderA(ReTruco accion, ManejadorDeRonda manejadorDeRonda,Partido partido) {
		
		System.out.print("Quiere ReTruco: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		scan.close();

		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else 
		
		if (respuesta.equals(("valecuatro"))){
			return manejadorDeRonda.cantarValeCuatro(new ValeCuatro(accion));

		} else {
			throw new AccionNoPosibleException();
		}
	}	
	
	public Accion responderA(ValeCuatro accion) {
		
		System.out.print("Quiere ValeCuatro: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		scan.close();
		
		if (respuesta.equals("quiero")){
			return new Quiero(accion);
		} else
		
		if (respuesta.equals("noquiero")){
			return new NoQuiero(accion);
		} else {
			throw new AccionNoPosibleException();
		}
	}

	public EnvidoDecorator responderA(Envido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Quiere Envido: ");
		
		Scanner scan = new Scanner(System.in);
		
		String respuesta = scan.next().toLowerCase();
		//scan.close();
		
		if (respuesta.equals("quiero")){
			QuieroTanto quiero = new QuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("noquiero")){
			NoQuieroTanto noquiero = new NoQuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero);
			return noquiero;
			
		} else if ((respuesta.equals("envido")) && (this.accionEnvidoValida(accion))){
			Envido nuevoEnvido = new Envido(new QuieroTanto(accion, this, accion.getOrigen()), this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(nuevoEnvido);
			return nuevoEnvido;
			
		} else if (respuesta.equals(("realenvido"))){
			RealEnvido realEnvido = new RealEnvido(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(realEnvido);
			return realEnvido;
			
		} else if (respuesta.equals(("faltaenvido"))){
			FaltaEnvido faltaEnvido = new FaltaEnvido(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(faltaEnvido);
			return faltaEnvido;
		}

		
		throw new AccionNoPosibleException();
	}

	private boolean accionEnvidoValida(Envido accion) {
		if (accion.cantar() < 3){
			return true;
		} else {
			return false;
		}
	}

	public EnvidoDecorator responderA(RealEnvido accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Quiere RealEnvido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			QuieroTanto quiero = new QuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("noquiero")){
			NoQuieroTanto noquiero = new NoQuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero); 
			return noquiero;
			
		} else if (respuesta.equals(("faltaenvido"))){
			FaltaEnvido faltaEnvido = new FaltaEnvido(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(faltaEnvido);
			return faltaEnvido;
		}
		
		throw new AccionNoPosibleException();
	}

	public EnvidoDecorator responderA(FaltaEnvido accion, ManejadorDeRonda manejadorDeRonda) {
		System.out.print("Quiere FaltaEnvido: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		
		if (respuesta.equals("quiero")){
			QuieroTanto quiero = new QuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(quiero);
			return quiero;
			
		} else if (respuesta.equals("noquiero")){
			NoQuieroTanto noquiero = new NoQuieroTanto(accion, this, accion.getOrigen());
			manejadorDeRonda.ejecutarRespuestaTanto(noquiero);
			return noquiero;
		}
		
		throw new AccionNoPosibleException();
	}

	public Accion responderA(AccionFlor accion, ManejadorDeRonda manejadorDeRonda, Partido partido) {
		System.out.print("Respuesta Flor: ");
		
		Scanner scan = new Scanner(System.in);
		String respuesta = scan.next().toLowerCase();
		scan.close();
		
		if (respuesta.equals("contraflor")){
			return new ContraFlor(accion);
		} else if (respuesta.equals("contrafloralresto")){
			return new ContraFlorAlResto(accion);
		} else {
			throw new AccionNoPosibleException();
		}
	}
	
	public int getTantoEnMano(){
		return this.getMano().getMaximosPuntosEnvido();
	}

	public void cantarEnvido(){
		
	}
	
	public void cantarFaltaEnvido(){
		
	}
	
	public void cantarFlor(){
		
	}
	
	public void cantarTruco(){
		
	}
	
	private void cantar(Accion accion){
		
	}
	
	public abstract void jugar(Vuelta vuelta);

	public boolean tieneCarta(Carta cartaGanadora) {
		return this.getMano().contiene(cartaGanadora);
	}
}
