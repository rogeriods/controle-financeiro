/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.rdsolutions.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class responsible to send e-mail with the new password.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class EnviaEmail {

	public static void enviaSenhaNova(String senha, String email) {
		Properties props = new Properties();
		// Address and ports for GMAIL access
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// Don't forget to change this line with yours information
				return new PasswordAuthentication("your_email@gmail.com","your_password");
			}
		});
		// Validate and send the e-mail
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("RDSolution - Nova Senha");
			message.setText("Segue sua nova senha, " + senha + // Message to be sent
					"\n\nLembre-se de alterar sua senha e nosso site.");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
