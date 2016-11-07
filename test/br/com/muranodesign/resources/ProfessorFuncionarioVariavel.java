package br.com.muranodesign.resources;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class ProfessorFuncionarioVariavel extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	
	@Test
	public void insert() {
		WebResource webResource = client().resource("http://localhost:8082/plataformaAmorim/ProfessorFuncionarioVariavel");
		 webResource.post("action=create&formacao=Superior&quinquenio=2&letra=E&descontoQuinquenio=1&"
		 		+ "observacao=TESTE&cargo=Professor&professorFuncionario=7&periodo=5&anoLetivo=8");
		 
				
	}
	
    
}