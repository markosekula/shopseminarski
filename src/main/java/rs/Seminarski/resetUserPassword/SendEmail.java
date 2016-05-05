package rs.Seminarski.resetUserPassword;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

		public static String sendEmail(String to) {	
			System.out.println("sendEmail:" +to);
			
			String link = "http://127.0.0.1:36795/index.html#/changePassword/"+to;
			
			String generateCode = to;		
			String sendLink = MD5Hashing.getMD5String(generateCode);
			String linkHref  = "<a href = "+link+">"+sendLink+"</a>";
		
			final String from = "markosinho92@gmail.com";
			final String password = "markos92";
		
			Properties properties = new Properties();
			
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			 
			Session session = Session.getDefaultInstance(properties,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from,password);
						}
					});
			 
			try {
				 
				 MimeMessage message = new MimeMessage(session);
				 
				 message.setFrom(new InternetAddress(from));
				 
				 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				 
				 message.setSubject("Change password");
			
				 message.setText("Click on link for change your password: " +linkHref, "UTF-8", "html"); //, "UTF-8", "html"

				 Transport.send(message);
				 
				 System.out.println("Sent message successfully....");
				
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
			return null;
		}

	
}
