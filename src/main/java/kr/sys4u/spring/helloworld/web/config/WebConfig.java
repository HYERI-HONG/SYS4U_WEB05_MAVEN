package kr.sys4u.spring.helloworld.web.config;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("kr.sys4u.spring.helloworld.web")
@PropertySource("classpath:/application.properties")
public class WebConfig extends WebMvcConfigurerAdapter{
	private static Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);
	
	@Autowired
	private Environment environment;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		LOGGER.info("------- Configuring ViewResolvers -------");
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
	 
		registry.viewResolver(resolver);
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		//환경변수는 가능한한 default값을 설정한다 : 없으면 안되는 경우엔 default없게(그냥 실패)
		String tmpDir = environment.getProperty("fileupload.tmp.dir", String.class, "/temp/fileupload");
		long maxSize = environment.getProperty("fileupload.max.size", Long.class, 2 * 1024 * 1024L);
		int minSize = environment.getProperty("fileupload.min.size", Integer.class, 0);
	
		CommonsMultipartResolver multipartResolver =  new CommonsMultipartResolver(); //class를 넘겨주는 것보다 어떤 생성자를 사용하는지 알려주는 것이 효율적
		multipartResolver.setUploadTempDir(new FileSystemResource(new File(tmpDir)));
		multipartResolver.setMaxUploadSize(maxSize);
		multipartResolver.setMaxInMemorySize(minSize);

		//return new StandardServletMultipartResolver; //spring에서 기본적으로 제공 
		return multipartResolver;
	}
}
