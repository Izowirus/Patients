package osm.web.pacienci.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import osm.web.pacienci.dtos.PatientDTO;
import osm.web.pacienci.model.MedicalTest;
import osm.web.pacienci.model.Patient;
import osm.web.pacienci.repositories.PatientRepository;
import osm.web.pacienci.storage.StorageService;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientsRepo;
	@Autowired
	StorageService storage;

	public Iterable<Patient> getPage(Integer number, Integer size) {
		if (number == null)
			number = 1;
		if (size == null)
			size = 25;
		final PageRequest request = new PageRequest(number - 1, size, Sort.Direction.ASC, "surname", "name");
		return patientsRepo.findAll(request).getContent();
	}

	public Patient addPatient(final PatientDTO patientDto) {
		return patientsRepo.save(patientDto.toPatient());
	}

	public Patient addPatient(final String id) {
		return patientsRepo.findOne(id);
	}

	public Patient deletePatient(final String id) {
		Patient patient= patientsRepo.findOne(id);
		for(MedicalTest test: patient.getTest()){
			String url = test.getPathToImg();
			for(int n =0;n<test.getNumberOfImg();n++){
				storage.delete(url+"-"+n);
			}
		}
		patientsRepo.delete(patient);
		return null;
	}

	public Iterable<MedicalTest> getPateintTests(String patientId) {
		Patient patient = patientsRepo.findOne(patientId);
		if (patient != null ) return patient.getTest();
		else return new LinkedList<MedicalTest>();
	}
	


}
