package com.modelo;

import java.util.HashMap;
import java.util.List;

import com.modelo.Carta;
import com.modelo.Carta.Palo;

public class ComparadorCartas {
	private static HashMap<Palo, HashMap<Integer, Integer>> _comparador = null;
	
	/* 
	 * Constructor privado del comparador
	 * El HashMap ubicado como value tiene como key el número de carta, y como value, el orden de valía de la carta en el juego (1 de Espadas es el 1,
	 * 1 de Basto el 2, 7 de Espadas el 3 y así siguiendo)
	 */
	
	protected static void comparadorDeCartas() {
		HashMap<Palo, HashMap<Integer,Integer>> comparador = new HashMap<Palo, HashMap<Integer,Integer>>();
		
		/* Completo el comparador
		 * 1ero: Cargo el HashMap con los 4 palos posibles
		 */
		comparador.put (Palo.Espada, new HashMap<Integer, Integer>());
		comparador.put (Palo.Basto, new HashMap<Integer, Integer>());
		comparador.put (Palo.Oro, new HashMap<Integer, Integer>());
		comparador.put (Palo.Copa, new HashMap<Integer, Integer>());
		
		// 2do: Cargo 1 a 1 los valores de carta posibles
		
		// 2.1: 1 de Espadas
		comparador.get(Palo.Espada).put(1, 1);
		// 2.2: 1 de Basto
		comparador.get(Palo.Basto).put(1, 2);
		// 2.3: 7 de Espadas
		comparador.get(Palo.Espada).put(7, 3);
		// 2.4: 7 de Oro
		comparador.get(Palo.Oro).put(7, 4);
		// 2.5: Todos los 3
		comparador.get(Palo.Espada).put(3, 5);
		comparador.get(Palo.Basto).put(3, 5);
		comparador.get(Palo.Oro).put(3, 5);
		comparador.get(Palo.Copa).put(3, 5);
		// 2.6: Todos los 2
		comparador.get(Palo.Espada).put(2, 6);
		comparador.get(Palo.Basto).put(2, 6);
		comparador.get(Palo.Oro).put(2, 6);
		comparador.get(Palo.Copa).put(2, 6);
		// 2.7: 1 de Oro y Copa
		comparador.get(Palo.Oro).put(1, 7);
		comparador.get(Palo.Copa).put(1, 7);
		// 2.8: Todos los 12
		comparador.get(Palo.Espada).put(12, 8);
		comparador.get(Palo.Basto).put(12, 8);
		comparador.get(Palo.Oro).put(12, 8);
		comparador.get(Palo.Copa).put(12, 8);
		// 2.9: Todos los 11
		comparador.get(Palo.Espada).put(11, 9);
		comparador.get(Palo.Basto).put(11, 9);
		comparador.get(Palo.Oro).put(11, 9);
		comparador.get(Palo.Copa).put(11, 9);
		// 2.10 Todos los 10
		comparador.get(Palo.Espada).put(10, 10);
		comparador.get(Palo.Basto).put(10, 10);
		comparador.get(Palo.Oro).put(10, 10);
		comparador.get(Palo.Copa).put(10, 10);
		// 2.11: 7 de Basto y Copa
		comparador.get(Palo.Basto).put(7, 11);
		comparador.get(Palo.Copa).put(7, 11);
		// 2.12: Todos los 6
		comparador.get(Palo.Espada).put(6, 12);
		comparador.get(Palo.Basto).put(6, 12);
		comparador.get(Palo.Oro).put(6, 12);
		comparador.get(Palo.Copa).put(6, 12);
		// 2.13: Todos los 5
		comparador.get(Palo.Espada).put(5, 13);
		comparador.get(Palo.Basto).put(5, 13);
		comparador.get(Palo.Oro).put(5, 13);
		comparador.get(Palo.Copa).put(5, 13);
		// 2.14 Todos los 4
		comparador.get(Palo.Espada).put(4, 14);
		comparador.get(Palo.Basto).put(4, 14);
		comparador.get(Palo.Oro).put(4, 14);
		comparador.get(Palo.Copa).put(4, 14);
		
		_comparador = comparador;
	}

	/*
	 * Compara la lista de cartas recibida por parámetro
	 * @Params:
	 * 	@List<Carta> listaCartas: Las cartas a ser comparadas
	 * @Return:
	 * 	@int: El índice de la lista donde se encuentre la carta más alta, o -1 si no hay cartas o hay parda entre 2 o más cartas
	 */
	
	public static int compararCartas (List<Carta> listaCartas) {
		if (_comparador == null) {
			ComparadorCartas.comparadorDeCartas();
		}
		
		// Si el array tiene un solo elemento, ese es el mayor
		if (listaCartas.size() == 1) return 0;
		
		// Si el array no tiene elementos, devuelvo -1
		if (listaCartas.size() == 0) return -1;

		// Recorro la lista de cartas para encontrar la más alta
		boolean esParda = false;
		int indiceMayor = 0;
		for (int i = 1; i < listaCartas.size(); i++) {
			if (_comparador.get(listaCartas.get(i).getPalo()).get(listaCartas.get(i).getNumero()) < _comparador.get(listaCartas.get(indiceMayor).getPalo()).get(listaCartas.get(indiceMayor).getNumero())) {
				indiceMayor = i;
				esParda = false;
			}
			
			else if (_comparador.get(listaCartas.get(i).getPalo()).get(listaCartas.get(i).getNumero()) == _comparador.get(listaCartas.get(indiceMayor).getPalo()).get(listaCartas.get(indiceMayor).getNumero())) {
				esParda = true;
			}
		}
		
		return (esParda) ? -1 : indiceMayor;
	}
}
