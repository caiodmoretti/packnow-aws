package br.edu.iff.PackNow.controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "api")
public class MainRestController {
	
	@GetMapping(path = "/home")
	@ResponseBody
	public String home() {
		return "Esta é a Home. 1234567 ";
		
	}

}

