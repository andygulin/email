package com.spring.email;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.spring.email.config.EmailConfiguration;
import com.spring.email.template.EmailTemplate;

@ContextConfiguration(classes = EmailConfiguration.class)
public class EmailTest extends AbstractJUnit4SpringContextTests {

	private static final Logger logger = Logger.getLogger(EmailTest.class);

	@Autowired
	private EmailTemplate emailTemplate;

	@Test
	public void send() {
		List<String> tos = Arrays.asList("610603860@qq.com");
		List<File> attachments = Arrays.asList(new File("pom.xml"));
		boolean result = emailTemplate.sendMail(tos, "javax.mail", "email content", attachments);
		logger.info(result ? "OK" : "FAIL");
	}
}