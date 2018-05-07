package com.prueba.call;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class Dispatcher extends Thread {
	private static final int MAX_THREADS = 10;

	private static Semaphore mutex = new Semaphore(1);
	private List<EmpleadoDirector> directorList = new ArrayList<EmpleadoDirector>();
	private List<EmpleadoSupervisor> supervisorList = new ArrayList<EmpleadoSupervisor>();
	private List<EmpleadoOperador> operadorList = new ArrayList<EmpleadoOperador>();
	private Queue<Llamada> pendientCalls;
	private boolean esActivo = true;

	Semaphore semaforoLlamadas;
	PriorityQueue<Llamada> listaLlamadas;

//	
	
	public Dispatcher(List<EmpleadoDirector> directorList, List<EmpleadoSupervisor> supervisorList,
			List<EmpleadoOperador> operadorList, Queue<Llamada> pendientCalls) {
		semaforoLlamadas = new Semaphore(MAX_THREADS);
		this.directorList = directorList;
		this.supervisorList = supervisorList;
		this.operadorList = operadorList;
		this.pendientCalls = pendientCalls;
	}

	public void dispatchCall() {
		this.start();
	}

	public void nuevaLlamada(Llamada llamada) {

		try {
			semaforoLlamadas.acquire();
			listaLlamadas.add(llamada);
			semaforoLlamadas.release();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public Llamada terminarLlamada() {
		Llamada llamada = null;
		if (llamadasPendientes()) {
			try {
				semaforoLlamadas.acquire();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			llamada = listaLlamadas.poll();
			semaforoLlamadas.release();

		}

		return llamada;

	}

	public boolean llamadasPendientes() {
		return listaLlamadas.size() > 0;
	}

	public void detener() {
		esActivo = false;
	}

	@Override
	public void run() {
		try {

			while (esActivo) {

				
				mutex.acquire();
				

				if (this.pendientCalls.isEmpty() ) {
					continue;
				} else {

					
					semaforoLlamadas.acquire();

					//Asignacion de empleado que atendera la llamada teniendo en cuenta la prioridad de asignacion
					if (!operadorList.isEmpty() || !supervisorList.isEmpty() || !directorList.isEmpty()) {
						Llamada llamada = pendientCalls.remove();

						System.out.println("Recibiendo llamada Nro. --> " + llamada.getId());
						if (!operadorList.isEmpty()) {
							EmpleadoOperador operator = operadorList.remove(0);
							System.out.println("Asigna llamada  " + llamada.getId() + " para el  Operador: "
									+ operator.getNombre());
							operator.setLlamada(llamada);
							operator.start();
						} else if (!supervisorList.isEmpty()) {
							EmpleadoSupervisor supervisor = supervisorList.remove(0);
							System.out.println("Asigna llamada  " + llamada.getId() + " para el  Supervisor: "
									+ supervisor.getNombre());
							supervisor.setLlamada(llamada);
							supervisor.start();
						} else if (!directorList.isEmpty()) {
							EmpleadoDirector director = directorList.remove(0);
							System.out.println("Asigna llamada   " + llamada.getId() + " para el  Director: "
									+ director.getNombre());
							director.setLlamada(llamada);
							director.start();
						}

					} else {

						
						semaforoLlamadas.release();
					}
				}

				mutex.release();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
