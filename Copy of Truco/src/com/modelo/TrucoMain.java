package com.modelo;

//import com.modelo.acciones.envido.*;
//import com.modelo.acciones.envido.NoQuiero;
//import com.modelo.acciones.envido.Quiero;
import com.modelo.acciones.truco.*;

public class TrucoMain {
	
	public static void main(String[] args){
		
//		AccionEnvido accionEnvidoNoQuiero = new NoQuiero(new Envido(new Tanto()));
//		AccionEnvido accionEnvidoQuiero = new Quiero(new Envido(new Tanto()));
//		AccionEnvido accionEnvidoEnvidoNoQuiero = new NoQuiero(new Envido(new Envido(new Tanto())));
//		AccionEnvido accionEnvidoEnvidoQuiero = new Quiero(new Envido(new Quiero(new Envido(new Tanto()))));
//		AccionEnvido accionRealEnvidoQuiero = new Quiero(new RealEnvido(new Tanto()));
//		AccionEnvido accionEnvidoRealEnvidoQuiero = new Quiero(new RealEnvido(new Quiero(new Envido(new Tanto()))));
//		
//		System.out.println(accionEnvidoNoQuiero.cantar());
//		System.out.println(accionEnvidoQuiero.cantar());
//		System.out.println(accionEnvidoEnvidoNoQuiero.cantar());
//		System.out.println(accionEnvidoEnvidoQuiero.cantar());
//		System.out.println(accionRealEnvidoQuiero.cantar());
//		System.out.println(accionEnvidoRealEnvidoQuiero.cantar());
		
		////////////////////////////////////////////
		System.out.println("");
		///////////////////////////////////////////
		
		AccionTruco accionTrucoNoQuiero = new NoQuiero(new Truco());
		AccionTruco accionReTrucoNoQuiero = new NoQuiero(new ReTruco(new Truco()));
		AccionTruco accionVale4NoQuiero = new NoQuiero(new ValeCuatro(new ReTruco(new Truco())));
		AccionTruco accionTrucoQuiero = new Quiero(new Truco());
		AccionTruco accionTrucoReTrucoQuiero = new Quiero(new ReTruco(new Truco()));
		AccionTruco accionTrucoReTrucoVale4Quiero = new Quiero(new ValeCuatro(new ReTruco(new Truco())));
		
		System.out.println(accionTrucoNoQuiero.cantar());
		System.out.println(accionReTrucoNoQuiero.cantar());
		System.out.println(accionVale4NoQuiero.cantar());
		System.out.println("");
		System.out.println(accionTrucoQuiero.cantar());
		System.out.println(accionTrucoReTrucoQuiero.cantar());
		System.out.println(accionTrucoReTrucoVale4Quiero.cantar());
		
	}
}
