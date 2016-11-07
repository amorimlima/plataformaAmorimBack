package br.com.muranodesign.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;



@Path("Converte")
public class ConverteToPdf {
	
	@POST
	@Produces("text/plain")
	public String converte(@FormParam("url") String url, @FormParam("id") int id ) throws IOException, DocumentException{
		Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Kevyn/Downloads/pdf.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream("C:/Users/Kevyn/Downloads/4º/4º/teste.html")); 
        //step 5
         document.close();
 
		return "";
	}
}
