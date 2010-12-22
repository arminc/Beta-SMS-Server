package nl.coralic.beta.sms.server.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utils
{
	
	public String formatDate(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(date);
	}

	public void sendMail(String id, String mail)
	{
		System.out.println("In de send mail method");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Dear Beta-SMS user,\n Thank you for submiting a feature/issue request. You can follow the status at http://beta-sms.coralic.nl?TABTOSELECT=tab-todo&ID="+id+"\n \n Thank you for using Beta-SMS.";

		try
		{
			System.out.println("Building the email and trying to send it");
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("beta-sms@coralic.nl", "beta-sms.coralic.nl Beta-SMS"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail, mail));
			msg.setSubject("Beta-SMS feature/issue request processed.");
			msg.setText(msgBody);
			Transport.send(msg);

		}
		catch (AddressException e)
		{
			System.err.println(e.getMessage());
		}
		catch (MessagingException e)
		{
			System.err.println(e.getMessage());
		}
		catch (UnsupportedEncodingException e)
		{
			System.err.println(e.getMessage());
		}
	}
}
