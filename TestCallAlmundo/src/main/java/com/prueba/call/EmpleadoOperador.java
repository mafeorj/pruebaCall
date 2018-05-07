package com.prueba.call;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EmpleadoOperador  extends Empleado{
	
	public static List<EmpleadoOperador> operadorList ;
	
	public EmpleadoOperador(String nombre) {
		  setNombre(nombre);
		}
	@Override
	void atenderllamada() {
		System.out.println("Operador " + this.getNombre() + " atiende llamada");
		
	}

	@Override
	void finalizarllamada() {
		System.out.println("Operador " + this.getNombre() + " finaliza llamada " + llamada.getId() + " en " + llamada.getDuracionLlamada() + " Segundos.");
	}


}
