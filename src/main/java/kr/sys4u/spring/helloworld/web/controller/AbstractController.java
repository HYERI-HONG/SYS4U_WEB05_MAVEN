package kr.sys4u.spring.helloworld.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AbstractController {
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
	protected Environment environment;
}
