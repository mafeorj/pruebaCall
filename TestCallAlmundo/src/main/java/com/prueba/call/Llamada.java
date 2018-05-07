package com.prueba.call;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Llamada {
	private int id;
	int duracionLlamada;
	
	public int getDuracionLlamada() {
		return duracionLlamada;
	}

	public void setDuracionLlamada(int duracionLlamada) {
		this.duracionLlamada = duracionLlamada;
	}

	public Llamada(int id) {
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Queue<Llamada> getListaLlamadas(Integer longitudad) {

		Queue<Llamada> listaLlamadas = new ArrayBlockingQueue<Llamada>(longitudad);
		for (int i = 0; i < longitudad; i++) {
			listaLlamadas.add(new Llamada(i+1));
		}
		return listaLlamadas;
	}

}
