package Enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {
	
	private String userName = "mileidemartinsmf2022@gmail.com";
	private String senha= "Fmk99355566";
	
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	public ObjetoEnviaEmail(String listaDestinatario, String nomeRementente, String assuntoEmail, String textoEmail ) {
		this.listaDestinatarios = listaDestinatario;
		this.nomeRemetente = nomeRementente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
		
	}
	
	
	
	public void enviarEmail(boolean envioHtml)throws Exception {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);
		
		if(envioHtml){
		   message.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
		   message.setText(textoEmail);
		}
		
		Transport.send(message);
	}
	
	
	public void enviarEmailAnexo(boolean envioHtml)throws Exception {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);
		
		
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		
		
		
		
		
		if(envioHtml){
		   corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
		   corpoEmail.setText(textoEmail);
		}
		
		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorDePdf());
		arquivos.add(simuladorDePdf());
		arquivos.add(simuladorDePdf());
		arquivos.add(simuladorDePdf());
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		
		int index = 0;
		for (FileInputStream fileInputStream : arquivos) {
		
		  MimeBodyPart anexoEmail = new MimeBodyPart();
		  anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
		  anexoEmail.setFileName("anexoemail"+index+".pdf");
		
		
		  multipart.addBodyPart(anexoEmail);
		  
		  index++;
		
		}
		
		message.setContent(multipart);
		
		
		Transport.send(message);
	}

	
	
	    private FileInputStream simuladorDePdf() throws Exception{
	    	
	    	Document document = new Document();
	    	File file = new File("fileanexo.pdf");
	    	file.createNewFile();
	    	PdfWriter.getInstance(document, new FileOutputStream(file));
	    	document.open();
	    	document.add(new Paragraph("conteudo do PDF anexo com Java Mail, esse texto e do PDF"));
	    	document.close();
	    	
	    	
	    	
	    	return new FileInputStream(file);
	    	
	    }
	
	
}
