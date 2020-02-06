package gov.tn.taxecommune.web.controllers.viewControllers.contribuableControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Keno&Kemo on 30.09.2017..
 */

@Controller
@RequestMapping("/ContribuablePage")
public class HomeController {

	@GetMapping(value = "/home")
	public String login() {
		return "contribuablePage/contribuablePage";
	}

}
