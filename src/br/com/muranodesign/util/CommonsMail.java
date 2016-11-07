package br.com.muranodesign.util;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.net.SMTPAppender;

public class CommonsMail extends SMTPAppender{

	/**
	 * envia email simples(somente texto)
	 * 
	 * @throws EmailException
	 * @throws MalformedURLException
	 */
	public void enviaEmailSimples(String destinatario, String senha, String login)throws EmailException, MalformedURLException {

		HtmlEmail email = new HtmlEmail();
		 email.setHostName("smtp.plataformaamorim.org"); // o servidor SMTP para envio do
		 email.addTo(destinatario); //destinatário
		 email.setFrom("noreplay@plataformaamorim.org"); //remetente
		 email.setSubject("Nova senha"); //assunto do e-mail
		 email.setMsg("Teste de Email utilizando commons-email em texto"); //conteudo do e-mail
		 
		 StringBuilder conteudoEmail = new StringBuilder();
		 conteudoEmail.append("");
		conteudoEmail.append("");
		 conteudoEmail.append("<!doctype html>" + 
				 			  "<html>" +
							  "<head>" +
                              "<meta charset="+"utf-8"+">" +
                              "<title>Documento sem título</title>"  +
                              "</head>" +
							
								"<body>" +
									"<div style="+"width:476px;height:555px; background-image:url(http://plataformaamorim.org/escola/img/email.gif);background-repeat:no-repeat;padding-top: 90px; margin: auto;"+">"+
								    	 "<div style="+"width:476px;height:555px;" +">"+
								         	"<p style="+"height: 110px;padding-left: 21px;font-family: sans-serif;"+">"+
								              	"<span style="+"display:block;font-size:20px;color:#666;font-weight:bold;margin-top:5px;"+">"+
								                	"Recuperação de senha"+ 
								                "</span>"+
								                "<span style="+"display:block;font-size: 16px;padding-top: 15px;color:#666"+">"+
								                	"14 de agosto de 2015 18:00" +
								                "</span>" +
								            "</p>" +
								            "<p style="+"height: 110px;padding-left: 21px;font-family: sans-serif;"+">"+
								              	"<span style="+"display:block;font-size:16px;color:#666;margin-top:5px;"+">"+
								              		"Usuario " + login + ", sua nova senha é:"+" "+senha+
								                "</span>"+
								               
								                "<span style="+"display:block;font-size:16px;color:#666;margin-top:20px;"+">"+
								                	"Para alterar sua senha faça login e clique em alterar senha:" +
								                    
								                "</span>" +
								                "<span style="+"display:block;font-size:16px;color:#666;margin-top:20px;"+">"+
								                	"<a href="+"plataformaamorim.org/escola"+">"+
								                    	"plataformaamorim.org"+	
								                    "</a>"+
								                "</span>"+
								            "</p>"+
								         "</div>"+   
								    "</div>"+
								"</body>"+
								"</html>"
);
		 conteudoEmail.append("");
		 conteudoEmail.append("");
		 
		 //email.setMsg("Conteudo em texto");
		 email.setHtmlMsg(conteudoEmail.toString());
		 
		 email.setAuthentication("noreplay@plataformaamorim.org",
				  "pal@Mar2015");
		 email.setSmtpPort(587);
		// email.setSSL(true);
		// email.setTLS(true);
		 email.send();

		/*
		 * SimpleEmail email = new SimpleEmail();
		 * email.setHostName("smtp.plataformaamorim.org"); // o servidor SMTP
		 * para envio do e-mail email.addTo(destinatario); //destinatÃ¡rio
		 * email.setFrom("noreplay@plataformaamorim.org"); // remetente
		 * email.setSubject("Recuperacao Senha"); // assunto do e-mail
		 * email.setMsg("Sua nova senha é : " + senha); //conteudo do e-mail
		 * email.setAuthentication("noreplay@plataformaamorim.org",
		 * "pal@Mar2015"); email.setSmtpPort(465); //email.setSSL(true);
		 * //email.setTLS(true); email.send();
		 */

	}

	// public static void main(String[] args) throws EmailException,
	// MalformedURLException {
	// CommonsMail commonsMail = new CommonsMail();

	// commonsMail.enviaEmailSimples() ;
	// }

}
