package com.prueba.call;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Empleado implements Runnable {

	private String nombre;
	Llamada llamada;

	public Llamada getLlamada() {
		return llamada;
	}

	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void run() {
		this.atenderllamada();

		try {

			int duracionLlamada = ThreadLocalRandom.current().nextInt(5000, 10000 + 1);
			Thread.sleep(duracionLlamada);
			llamada.setDuracionLlamada(duracionLlamada / 1000);
			this.finalizarllamada();
			if (this instanceof EmpleadoOperador)
				EmpleadoOperador.operadorList.add((EmpleadoOperador) this);
			else if (this instanceof EmpleadoSupervisor)
				EmpleadoSupervisor.supervisorList.add((EmpleadoSupervisor) this);
			else if (this instanceof EmpleadoDirector)
				EmpleadoDirector.directorList.add((EmpleadoDirector) this);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	abstract void atenderllamada();

	abstract void finalizarllamada();

	public void start() {
		new Thread(this).start();
	}
}
