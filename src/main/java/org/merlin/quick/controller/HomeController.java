package org.merlin.quick.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		// modelAndView.addObject("dls", dls);
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
