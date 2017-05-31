package org.merlin.quick.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.merlin.quick.datalayer.UserDao;
import org.merlin.quick.model.User;
import org.merlin.quick.model.UserRole;
import org.merlin.quick.service.SimpleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	JdbcUserDetailsManager userManager;
	@Autowired
	@Qualifier("myCrypt")
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	SimpleUserDetailsService userService;

	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ifttt/test")
	public ResponseEntity ifttt(HttpServletRequest request) throws IOException {
		// InputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		if (bufferedReader != null) {
			// new BufferedReader(new InputStreamReader(inputStream));
			char[] charBuffer = new char[128];
			int bytesRead = -1;
			while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
				stringBuilder.append(charBuffer, 0, bytesRead);
			}
		} else {
			stringBuilder.append("");
		}
		System.out.println(stringBuilder.toString());
		return new ResponseEntity<String>(HttpStatus.OK).ok("it works");

	}
}
