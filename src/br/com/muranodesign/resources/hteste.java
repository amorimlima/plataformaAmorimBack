package br.com.muranodesign.resources;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.lowagie.text.DocumentException;

public class hteste {

	public void main(String arquivo, String caminho) throws DocumentException, IOException {
		

	  	OutputStream os = new FileOutputStream(arquivo);
		    	//Html2Pdf.convert("<h1 style=\"color:red\">Hello PDF</h1>", os);
		    	//os.close();
		    
		    	//File arquivo = new File("C:/Users/Kevyn/Downloads/4º/4º/Animais.html");
		    	//File arquivo = new File("C:/Users/Kevyn/Downloads/texto.txt");
		    	
		    	FileReader fr = new FileReader(caminho);
		    	
		    	BufferedReader br = new BufferedReader(fr);
		    	
		    	String texto = "";
		    	while (br.ready()) {
					String linha = br.readLine();
					
					texto = texto + linha + '\n';
				}
		    	
		    	Html2Pdf.convert(texto, os);
		    	os.close();
		    
		    	br.close();
				fr.close();
		    
		
	}

}
