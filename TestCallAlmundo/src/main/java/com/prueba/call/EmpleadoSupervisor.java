package com.prueba.call;

import java.util.List;

public class EmpleadoSupervisor extends Empleado{
	
	public static List<EmpleadoSupervisor> supervisorList ;
	
	public EmpleadoSupervisor(String nombre) {
		  setNombre(nombre);
		}
	
	@Override
	void atenderllamada() {
		System.out.println("Supervisor " + this.getNombre() + " atiende llamada");
		
	}

	@Override
	void finalizarllamada() {
		System.out.println("Supervisor " + this.getNombre() + " finaliza llamada " + llamada.getId() + " en " + llamada.getDuracionLlamada() + " Segundos.");
	}

}
