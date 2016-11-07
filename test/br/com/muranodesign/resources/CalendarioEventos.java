package br.com.muranodesign.resources;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class CalendarioEventos extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	
	@Test
	public void insert() {
		WebResource webResource = client().resource("http://localhost:8082/plataformaAmorim/Calendario");
		 webResource.post("action=create&ano=2015&dataInicio=2014-12-19&dataFim=2014-12-20&descricao=Descricao&evento=Farias&evento=&feriado=1&aula=1&tipoEvento=30");
		 
				
	}
	

	@Test
	public void insertUpdate() {
		WebResource webResource = client().resource("http://localhost:8082/plataformaAmorim/Calendario");
		 webResource.post("action=update&id=2&ano=2015&dataInicio=2014-12-19&dataFim=2014-12-20&descricao=Descricao&evento=Farias&evento=&feriado=1&aula=1&tipoEvento=30");
		 
				
	}
	
	
}