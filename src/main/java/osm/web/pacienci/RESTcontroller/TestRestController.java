package osm.web.pacienci.RESTcontroller;

import java.util.UUID;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osm.web.pacienci.dtos.TestDTO;
import osm.web.pacienci.services.PatientService;
import osm.web.pacienci.services.TestService;
import osm.web.pacienci.storage.StorageService;

@RestController
@RequestMapping("app/tests")
public class TestRestController {

	@Autowired
	private PatientService patients;
	@Autowired
	private TestService tests;

	@GetMapping("patient/{id}")
	public Iterable<TestDTO> getPateintTest(@PathVariable("id") String patientId) {
		return TestDTO.toDTO(patients.getPateintTests(patientId));
	}

	@GetMapping("/{id}")
	public TestDTO getTest(@PathVariable String id) {
		return TestDTO.toDTO(tests.getTest(id));
	}

	@PostMapping
	public TestDTO postTest(@RequestBody TestDTO test) {
		String uuidForFile = UUID.randomUUID().toString();
		test.setPathToImg(uuidForFile);
		return TestDTO.toDTO((tests.saveTest(test.toMedicalTest(), test.getPatientId())));
	}

	@PutMapping
	public TestDTO putTest(@Valid @RequestBody TestDTO test, BindingResult errors) {
		if (errors.hasErrors())
			throw new HTTPException(400);
		else
			return TestDTO.toDTO((tests.saveTest(test.toMedicalTest(), test.getPatientId())));
	}

	@DeleteMapping("/{id}")
	public TestDTO deleteTest(@PathVariable String id) {
		return TestDTO.toDTO(tests.deleteTest(id));
	}
}
