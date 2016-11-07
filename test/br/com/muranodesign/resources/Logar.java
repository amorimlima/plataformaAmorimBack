package br.com.muranodesign.resources;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class Logar extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	/*@Test
	public void EnviarUsuarioSenha() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel/html/");
		 webResource.post("ano=39&periodo=8");
		 
				
	}*/
	/*
	@Test
	public void EnviarUsuarioSenha() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/RegistroDiario/");
		 webResource.post("ano=39&periodo=8");
		 
				
	}*/

	@Test
	public void EnviarUsuarioSenha() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Calendario/delete/");
		 webResource.post("action=delete&id=451");
		 
				
	}
}