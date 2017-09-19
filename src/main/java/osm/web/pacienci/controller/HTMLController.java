package osm.web.pacienci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTMLController {

	@RequestMapping({ "/", "index" })
	public String index(Model model) {
		return "view";
	}

	@RequestMapping("addPatientDialog")
	public String addPatientController() {
		return "addPatientDialog";
	}

	@RequestMapping("addTestDialog")
	public String addTestController() {
		return "addTestDialog";
	}

	@RequestMapping("testDialog")
	public String testDialog() {
		return "testDialog";
	}
	@RequestMapping("editTestDialog")
	public String editTestDialog() {
		return "editTestDialog";
	}


}
