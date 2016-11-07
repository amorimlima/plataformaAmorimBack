package br.com.muranodesign.resources;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class Calendario extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	
	@Test
	public void insert() {
		WebResource webResource = client().resource("http://localhost:8082/plataformaAmorim/CalendarioEventos");
		 webResource.post("action=createdataInicio=2014-12-19&dataFim=2014-12-20&evento=Farias&descricao=Farias&calendario=2&tipoEventoData=30&anoLetivo=30&tipoEvento=32");
		 
				
	}
	
    
}