package com.prueba.call;


import java.util.List;

public class EmpleadoDirector extends Empleado{

	public static List<EmpleadoDirector> directorList ;
	

	public EmpleadoDirector(String nombre) {
	  setNombre(nombre);
	}

	@Override
	void atenderllamada() {
		System.out.println("Director " + this.getNombre() + " atiende llamada " + llamada.getId());
		
	}

	@Override
	void finalizarllamada() {
		System.out.println("Director " + this.getNombre() + " finaliza llamada " + llamada.getId() + " en " + llamada.getDuracionLlamada() + " Segundos.");
	}


	
	

}
