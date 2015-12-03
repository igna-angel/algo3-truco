package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.exceptions.EmptyListException;
import com.exceptions.NoContieneCartaException;
import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaInvalida;
import java.util.Stack;

public class Mano implements IRecibible{

	private List<Carta> _cartas = null;
	
	public Mano() {
		this._cartas = new ArrayList<Carta>();
	}
	
	public List<Carta> getCartas(){
		return this._cartas;
	}
	
	@Override
	public void recibirCarta(Carta carta) {
		carta.cartaEnMano();
		this.getCartas().add(carta);
	}
	
	public int getCantidadCartas(){
		return this.getCartas().size();
	}
	
	public int getCartasEnMano(){
		int cartasEnMano = 0;
		for(Carta carta : this.getCartas()){
			if(carta.estaCartaEnMano()){
				cartasEnMano++;
			}
		}
		
		return cartasEnMano;
	}
	
	public boolean contiene(Carta carta){
		return this.getCartas().contains(carta);
	}

	public void quitarCarta(Carta carta) {
		this.getCartas().remove(carta);
	}

	public void devolverCartas() {
		this.getCartas().clear();
	}
	
	public void bajarCarta(Carta carta){
		if(!this.contiene(carta)) throw new NoContieneCartaException();
		if(!carta.estaCartaEnMano()) throw new NoContieneCartaException();
		carta.cartaJugada();
	}
	
	public Carta devolverCartaMasAlta() {
		Carta ganadora = new CartaInvalida();
		
		for(Carta cartaEnMano : this.getCartas()){
			ganadora = ganadora.ganador(cartaEnMano);
		}
		
		return ganadora;
	}

	private List<Carta> getGanadorasA(Carta carta){
		List<Carta> ganadoras = new ArrayList<Carta>();
		
		for(Carta cartaEnMano : this.getCartas()){
			if(cartaEnMano == cartaEnMano.ganador(carta)){
				ganadoras.add(cartaEnMano);
			}
		}
		
		return ganadoras;
	}
	
	private Carta getMasBajaEn(List<Carta> cartas){
		if(cartas.isEmpty()) throw new EmptyListException();
		
		Carta masBaja = cartas.get(0);
		
		for(Carta carta : cartas){
			masBaja = (masBaja == masBaja.ganador(carta))? carta : masBaja;
		}
		
		return masBaja;
	}
	
	public Carta getCartaGanadoraMinimaA(Carta carta){
		return this.getMasBajaEn(this.getGanadorasA(carta));		
	}
	
	public Carta getCartaMasBaja(){
		return this.getMasBajaEn(this.getCartas());
	}
	
	private Set<Carta> getCartasDelMismoPalo(){
		Set<Carta> mismoPalo = new HashSet<Carta>();
		
		for(int i = 0; i < this.getCartas().size() - 1; i++){
			if(this.getCartas().get(i).esMismoPaloQue(this.getCartas().get(i+1))){
				mismoPalo.add(this.getCartas().get(i));
				mismoPalo.add(this.getCartas().get(i+1));
			}
		}
		
		return mismoPalo;
	}
	
	public int getCantidadDeCartasDelMismoPalo(){
		return this.getCartasDelMismoPalo().size();
	}
	
	public boolean hayFlor(){
		return (this.getCantidadDeCartasDelMismoPalo() == 3);
	}
	
	public int getMaximosPuntosEnvido(){
		int[] combinacionesEnvido = new int[6];
		combinacionesEnvido[0] = this.getCartas().get(0).getPuntosEnvido();
		combinacionesEnvido[1] = this.getCartas().get(1).getPuntosEnvido();
		combinacionesEnvido[2] = this.getCartas().get(2).getPuntosEnvido();
		combinacionesEnvido[3] = this.getCartas().get(0).getPuntosEnvidoCon(this.getCartas().get(1));
		combinacionesEnvido[4] = this.getCartas().get(0).getPuntosEnvidoCon(this.getCartas().get(2));
		combinacionesEnvido[5] = this.getCartas().get(1).getPuntosEnvidoCon(this.getCartas().get(2));
		
		Arrays.sort(combinacionesEnvido);
		return combinacionesEnvido[5];
	}
	
	public int getTantoEnMano() {		
		return this.getMaximosPuntosEnvido();
		/*int tantoCarta1 = this._cartas.get(0).getNumero();
		int tantoCarta2 = this._cartas.get(1).getNumero();
		int tantoCarta3 = this._cartas.get(2).getNumero();
		int tantoParcial = 0;
		if (this._cartas.get(0).getPalo() == this._cartas.get(1).getPalo()) {
			tantoParcial += 20 + tantoCarta1 + tantoCarta2;
		}
		
		else if (this._cartas.get(0).getPalo() == this._cartas.get(2).getPalo()) {
			tantoParcial += 20 + tantoCarta1 + tantoCarta3;
		}
		
		else if (this._cartas.get(1).getPalo() == this._cartas.get(2).getPalo()) {
			tantoParcial += 20 + tantoCarta2 + tantoCarta3;
		}
		
		else if (this._cartas.get(0).getPalo() == this._cartas.get(1).getPalo() && this._cartas.get(1).getPalo() == this._cartas.get(2).getPalo()) {
			tantoParcial += 20 + tantoCarta1 + tantoCarta2 + tantoCarta3;
		}
		
		else {
			tantoParcial = Collections.max(Arrays.asList(tantoCarta1, tantoCarta2, tantoCarta3));
		}
		return tantoParcial;*/
	}

	public Carta buscarCartaQueGane(Stack<Carta> cartasYaJugadas) {
		// Busco las cartas que le gana a la más alta de las ya jugadas
		ComparadorCartas comparador = new ComparadorCartas();
		Carta masAltaJugada = comparador.compararCartas(cartasYaJugadas.toArray(new Carta[cartasYaJugadas.size()]));
		List<Carta> cartasQueGananALaMasAlta = comparador.getCartasMasAltasQueUnaDada(masAltaJugada, this._cartas);
		
		if (cartasQueGananALaMasAlta.size() == 0) {
			// No hay cartas que ganen, devuelvo la más baja
			return comparador.getCartaMasBaja(this._cartas.toArray(new Carta[this._cartas.size()]));
		}
		
		return comparador.getCartaMasBaja(cartasQueGananALaMasAlta.toArray(new Carta[this._cartas.size()]));
	}

	public boolean florEnMano() {
		return this._cartas.get(0).getPalo() == this._cartas.get(1).getPalo() && this._cartas.get(1).getPalo() == this._cartas.get(2).getPalo();
	}

}
