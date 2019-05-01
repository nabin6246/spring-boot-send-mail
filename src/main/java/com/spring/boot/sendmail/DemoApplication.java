package com.spring.boot.sendmail;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Sending mail...");

		try {
			sendEmailWithAttachement();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("DOne");

	}

	void sendEmail() throws MessagingException, IOException{
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo("nabin62246@gmail.com");

		msg.setSubject("Testing from spring boot");
		msg.setText("Hello from spring boot");

		javaMailSender.send(msg);
	}

	void sendEmailWithAttachement() throws MessagingException, IOException{
		MimeMessage msg=javaMailSender.createMimeMessage();

		MimeMessageHelper helper=new MimeMessageHelper(msg,true);
		helper.setTo("nabin62246@gmail.com");
		helper.setSubject("Testing from spring boot");

		helper.setText("<h1>Check attachment</h1>",true);
		helper.addAttachment("myphoto.png", new ClassPathResource("android.png"));
	
		javaMailSender.send(msg);
	}




}


