package kr.sys4u.spring.helloworld.web.controller.home;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.sys4u.spring.helloworld.web.controller.AbstractController;

@Controller
public class HomeController extends AbstractController{
	private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private JdbcOperations jdbcOperations;
	
	@GetMapping("/home.do")
	public String home() {
		LOGGER.debug(jdbcOperations.queryForObject("select 1 from dual", String.class));
		return "home/index";
	}
	
	@PostMapping("/fileupload.do")
	public String fileupload(@RequestParam("name") String name, @RequestPart("picture") MultipartFile file) throws IOException {
		LOGGER.debug("Input Name is {}", name);
		String uploadDir = environment.getProperty("fileupload.upload.dir", String.class, "/helloworld/upload");
		System.out.println(file.getOriginalFilename());
		File destFile = new File(uploadDir, file.getOriginalFilename());
		file.transferTo(destFile);
		return "redirect:/home.do";
	}
}
