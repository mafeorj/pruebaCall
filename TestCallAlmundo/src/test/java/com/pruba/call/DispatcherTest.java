package com.pruba.call;

import java.util.ArrayList;
import java.util.Queue;

import org.junit.Test;

import com.prueba.call.Dispatcher;
import com.prueba.call.EmpleadoDirector;
import com.prueba.call.EmpleadoOperador;
import com.prueba.call.EmpleadoSupervisor;
import com.prueba.call.Llamada;



public class DispatcherTest {
	
    @Test
    public void testDiezLLamadas() {
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

	
    }
	
    @Test(expected = NullPointerException.class)
    public void testDispatcherCreationWithNullStrategy() {
        new Dispatcher(null, null, null, null).dispatchCall();;
  
    }

}
