package com.spring.email;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext-email.xml")
public class EmailTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private EmailTemplate emailTemplate;

	@Test
	public void send() {
		List<String> tos = Arrays.asList("610603860@qq.com");
		List<File> attachments = Arrays.asList(new File("pom.xml"));
		boolean result = emailTemplate.sendMail(tos, "javax.mail", "邮件的内容", attachments);
		if (result) {
			System.out.println("OK");
		} else {
			System.out.println("FALL");
		}
	}
}