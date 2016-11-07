package br.com.muranodesign.resources;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class Grupo extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	/*
	@Test
	public void insert() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Converte");
		 webResource.post("url=C:/Users/Kevyn/Downloads/4º/4º/Animais.html&id=919"); 		
	}
	*/
	/*
	@Test
	public void Roteiro() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Roteiro");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	@Test
	public void Calendario() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Calendario");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	@Test
	public void Grupos() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Grupo");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	
	@Test
	public void Usuario() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Usuario");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	@Test
	public void Objetivo() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Objetivo");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	
	@Test
	public void Chamada() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Chamada");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	@Test
	public void Mensagens() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Mensagens");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	@Test
	public void PlanejamentoRoteiro() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/PlanejamentoRoteiro");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	
	@Test
	public void AlunoVariavel() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel");
		ClientResponse r = webResource.type("application/json").post(ClientResponse.class);
	}
	*/
}