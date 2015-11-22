package com.modelo;

import com.modelo.acciones.envido.*;
import com.modelo.acciones.envido.NoQuiero;
import com.modelo.acciones.envido.Quiero;
//import com.modelo.acciones.truco.*;

public class TrucoMain {
	
	public static void main(String[] args){
		
		
		AccionEnvido accionEnvido = new Envido(new Tanto());
		AccionEnvido accionEnvidoEnvido = new Envido(new Envido(new Tanto()));
		AccionEnvido accionEnvidoEnvidoEnvido = new Envido(new Envido(new Envido(new Tanto())));
		AccionEnvido accionRealEnvido = new RealEnvido(new Tanto());
		AccionEnvido accionEnvidoQuiero = new Quiero(new Envido(new Tanto()));
		AccionEnvido accionEnvidoNoQuiero = new NoQuiero(new Envido(new Tanto()));
		
		System.out.println(accionEnvido.cantar());
		System.out.println(accionEnvidoEnvido.cantar());
		System.out.println(accionEnvidoEnvidoEnvido.cantar());
		System.out.println(accionRealEnvido.cantar());
		System.out.println(accionEnvidoQuiero.cantar());
		System.out.println(accionEnvidoNoQuiero.cantar());
		
		////////////////////////////////////////////
		System.out.println("");
		///////////////////////////////////////////
		
//		AccionTruco accionTruco = new Truco();
//		AccionTruco accionReTruco = new ReTruco(new Truco());
//		AccionTruco accionVale4 = new ValeCuatro(new ReTruco(new Truco()));
//		AccionTruco accionTrucoQuiero = new Quiero(new Truco());
//		
//		
//		System.out.println(accionTruco.cantar());
//		System.out.println(accionReTruco.cantar());
//		System.out.println(accionVale4.cantar());
		
	}
}
