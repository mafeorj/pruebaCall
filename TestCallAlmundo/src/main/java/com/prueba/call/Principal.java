package com.prueba.call;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Principal {

	public static void main(String[] args) throws Exception {

		EmpleadoDirector.directorList = new ArrayList<EmpleadoDirector>();
		EmpleadoSupervisor.supervisorList = new ArrayList<EmpleadoSupervisor>();
		EmpleadoOperador.operadorList = new ArrayList<EmpleadoOperador>();

	    EmpleadoDirector.directorList.add(new  EmpleadoDirector("MARIA"));
	    EmpleadoDirector.directorList.add(new  EmpleadoDirector("JUAN"));	
	    EmpleadoDirector.directorList.add(new  EmpleadoDirector("MERY"));
	    EmpleadoOperador.operadorList.add(new  EmpleadoOperador("JOSE"));
		EmpleadoSupervisor.supervisorList.add(new  EmpleadoSupervisor("KATIE"));
		EmpleadoSupervisor.supervisorList.add(new  EmpleadoSupervisor("ALEXA"));	
		EmpleadoSupervisor.supervisorList.add(new  EmpleadoSupervisor("JUAN"));

		
	    Queue<Llamada> pendientCalls = Llamada.getListaLlamadas(10);

	    
		Dispatcher dispatcher = new Dispatcher(EmpleadoDirector.directorList, EmpleadoSupervisor.supervisorList, EmpleadoOperador.operadorList, pendientCalls);
		dispatcher.dispatchCall();
		//dispatcher.detener();
	}

}
