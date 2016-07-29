package com.spring.email.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.spring.email.template.EmailTemplate;

@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(env.getProperty("mail.host"));
		javaMailSender.setUsername(env.getProperty("mail.username"));
		javaMailSender.setPassword(env.getProperty("mail.password"));
		javaMailSender.setDefaultEncoding(env.getProperty("mail.defaultEncoding"));
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		// if gmail
		// javaMailProperties.put("mail.smtp.starttls.enable",
		// env.getProperty("mail.smtp.starttls.enable"));
		javaMailSender.setJavaMailProperties(javaMailProperties);
		return javaMailSender;
	}

	@Bean
	public EmailTemplate emailTemplate() {
		return new EmailTemplate(javaMailSender(), env.getProperty("mail.username"),
				env.getProperty("mail.defaultEncoding"));
	}
}