package kr.sys4u.spring.helloworld.core.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
@ComponentScan("kr.sys4u.spring.helloworld.core")
public class RootConfig {
	private static Logger LOGGER = LoggerFactory.getLogger(RootConfig.class);

	@Bean
	public JndiObjectFactoryBean dataSource() {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/orcl");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		
		return jndiObjectFB;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		LOGGER.debug(dataSource.toString());
		return new JdbcTemplate(dataSource);
	}
}
