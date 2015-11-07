package com.ListaCircular.modelo;

public class test {
	
	public static void main(String[] args){
		CircularList<Integer> lista = new CircularList<Integer>();
		System.out.println(lista.getSize());
		try{
			System.out.println(lista.getAt(0));
		}catch (IndexOutOfBoundsException e){
			System.out.println(e.getClass());
		}
		
		lista.add(10); //first 
		System.out.println(lista.getFirst());
		System.out.println(lista.getAt(0));
		lista.add(3);
		lista.add(2); //pos 2
		lista.add(6);
		lista.add(15); //last
		
		
		System.out.println("First: " + lista.getFirst());
		System.out.println("Last: " + lista.getLast());
		System.out.println("Pos 2: " + lista.getAt(2));
		
		/*lista.resetToFirst();
		for(int i = 0; i < 10; i++){
			System.out.println(lista.getCurrent());
			lista.advanceNode();
		}*/
		
		lista.removeAt(2);
		lista.removeAt(2);
		
		lista.resetToFirst();
		for(int i = 0; i < 10; i++){
			System.out.println(lista.getCurrent());
			lista.advanceNode();
		}
		
		
	}
}
