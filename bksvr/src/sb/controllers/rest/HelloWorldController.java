package sb.controllers.rest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

//restful controllers

@Controller
public class HelloWorldController {

	@RequestMapping("/helloFromSpring")
	public @ResponseBody
	String getHello() {

		return "Rest hello from Spring";

	}

	@RequestMapping("/test2")
	public @ResponseBody
	Greeting getGreeting() {

		return new Greeting(32423, "greeting name");

	}

}
