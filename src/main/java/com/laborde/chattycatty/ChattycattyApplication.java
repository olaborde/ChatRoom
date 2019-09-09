package com.laborde.chattycatty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication

public class ChattycattyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChattycattyApplication.class, args);
	}

}
