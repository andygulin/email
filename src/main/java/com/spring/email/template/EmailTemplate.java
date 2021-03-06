package com.spring.email.template;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailTemplate {

	private static final Logger logger = Logger.getLogger(EmailTemplate.class);

	private JavaMailSender javaMailSender;
	private String from;
	private String encoding;

	public EmailTemplate() {
	}

	public EmailTemplate(JavaMailSender javaMailSender, String from, String encoding) {
		this.javaMailSender = javaMailSender;
		this.from = from;
		this.encoding = encoding;
	}

	public boolean sendMail(List<String> tos, String subject, String content, List<File> attachments) {
		boolean result = true;
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, encoding);
			helper.setFrom(from);
			helper.setTo(tos.toArray(new String[tos.size()]));
			helper.setSubject(subject);
			helper.setText(content);
			if (attachments != null && attachments.size() > 0) {
				for (File file : attachments) {
					helper.addAttachment(file.getName(), file);
				}
			}
			javaMailSender.send(message);
			logger.info("email send success...");
			logger.info("to : " + tos);
		} catch (MessagingException e) {
			result = false;
			logger.info("email send fail...");
		}
		return result;
	}

	public boolean sendMail(List<String> tos, String subject, String content) {
		return sendMail(tos, subject, content, null);
	}

	public boolean sendMail(String to, String subject, String content) {
		return sendMail(Arrays.asList(to), subject, content);
	}

	public boolean sendMail(String to, String subject, String content, File attachment) {
		return sendMail(Arrays.asList(to), subject, content, Arrays.asList(attachment));
	}

	public boolean sendMail(String to, String subject, String content, List<File> attachments) {
		return sendMail(Arrays.asList(to), subject, content, attachments);
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
