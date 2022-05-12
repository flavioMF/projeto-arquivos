package Enviando.email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail. Transport;
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage;







public class AppTest{
	
	

	@org.junit.Test
	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("Voce esta recebendo acesso ao curso de Java. <br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso click no botao abaixo. <br/><br/>");
		
		stringBuilderTextoEmail.append("<b>login:</b> flaviomartinsmf1@gmail.com<br/>");
		stringBuilderTextoEmail.append("<b>senha:</b> b4e5r93<br/><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"http://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7;padding: 14px 25px;"
				+ " text-align:center; text-decoration: none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier;"
				+ " border : 3px solid green; background-color:#99DA39; \">Acessar Portal do Aluno</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:10px\">Ass: Flavio Softwares</span>");
		
		ObjetoEnviaEmail enviaEmail  = new ObjetoEnviaEmail("flaviomartinsmf1@gmail.com",
				                                           "Flavio Martins", 
				                                           "Acesso a Plataforma EAD Curso java", 
				                                           stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmailAnexo(true);
		
	
				
	}
    
	
}
