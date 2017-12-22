/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
//Teste


/**
 * Classe utilitaria para realização de upload atraves.
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

public class Upload {

	private Logger logger = Logger.getLogger(Upload.class.getName());
	
	// save uploaded file to new location
	/**
	 * metodo utilizado para escrever o arquivo no servidor.
	 *
	 * @param arquivo em formato stream
	 * @param Local onde o arquivo será gravado
	 */
	public void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
	 
			try {
				OutputStream out;
				int read = 0;
				byte[] bytes = new byte[1024];
	 
				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
				
//				BufferedImage image = ImageIO.read(uploadedInputStream);
//				
//				int extensionStart = uploadedFileLocation.lastIndexOf('.');
//				String extension = uploadedFileLocation.substring(extensionStart + 1);
//				
//				ImageIO.write(image, extension, new File(uploadedFileLocation));
				
			} catch (IOException e) {
	 
				logger.warn("Erro de:  "+e);
				//e.printStackTrace();
				
			}
	 
		}
	
	public void resizeAndWriteToFile (File uploadedInputStream,
			String uploadedFileLocation, int maxSize) throws Exception{
		
		BufferedImage image = ImageIO.read(uploadedInputStream);
		int width          = image.getWidth();
		int height         = image.getHeight();
		int format = (image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType());

		
		if (width > maxSize)
		{
			image = resizeImage(image, format, maxSize, maxSize * height/width); 
		}
		
		int extensionStart = uploadedInputStream.toString().lastIndexOf('.');
		String extension = uploadedInputStream.toString().substring(extensionStart + 1);
		
		ImageIO.write(image, extension, new File(uploadedFileLocation));
		
	}
	
	private static BufferedImage resizeImage(BufferedImage image, int format, int width, int height) {
	    BufferedImage resizedImage = new BufferedImage(width, height, format);
	    Graphics2D graphics = resizedImage.createGraphics();
	    graphics.drawImage(image, 0, 0, width, height, null);
	    graphics.dispose();

	    return resizedImage;
	}
	
	public void deleteFile(String filePath){
		new File(filePath).delete();
	}
}

