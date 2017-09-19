package osm.web.pacienci.RESTcontroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import osm.web.pacienci.dtos.PatientDTO;
import osm.web.pacienci.model.Patient;
import osm.web.pacienci.services.PatientService;

@RestController
@RequestMapping("app/patients")
public class PatientRestController {	

		@Autowired
		PatientService patientsService;
		
		@GetMapping
		public Iterable<PatientDTO> getPatients(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer size){
			Iterable<Patient> patients = patientsService.getPage(page,size);
			return PatientDTO.toDTO(patients);
		}
		
		@GetMapping("/{id}")
		public PatientDTO getPatient(@PathVariable String id){
			return PatientDTO.toDTO(patientsService.addPatient(id));
		}
		
		@PostMapping 
		public PatientDTO postPatient(@Valid @RequestBody PatientDTO patient, BindingResult errors){			
			if(errors.hasErrors())
				throw new HTTPException(400);
			else
				return  PatientDTO.toDTO(patientsService.addPatient(patient));
		}
		
		@PutMapping
		public PatientDTO putPatient(@Valid @RequestBody PatientDTO patient, BindingResult errors){			
			if(errors.hasErrors())
				throw new HTTPException(400);
			else
				return  PatientDTO.toDTO(patientsService.addPatient(patient));
		}
		
		@DeleteMapping("/{id}")
		public PatientDTO deletePatient(@PathVariable String id){
			return  PatientDTO.toDTO(patientsService.deletePatient(id));
		}
}

