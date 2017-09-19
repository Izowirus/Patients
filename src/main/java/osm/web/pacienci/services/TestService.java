package osm.web.pacienci.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osm.web.pacienci.model.MedicalTest;
import osm.web.pacienci.model.Patient;
import osm.web.pacienci.repositories.PatientRepository;
import osm.web.pacienci.repositories.TestRepository;
import osm.web.pacienci.storage.StorageService;
@Service
public class TestService {
	
	@Autowired
	TestRepository  testsRepo;
	@Autowired
	PatientRepository  patientsRepo;
	@Autowired
	StorageService storage;
	
	public MedicalTest getTest(String testId){
		return testsRepo.findOne(testId);
	}
	
	public MedicalTest deleteTest(String testId){
		MedicalTest test = testsRepo.findOne(testId);
		String url = test.getPathToImg();
		for(int n =0;n<test.getNumberOfImg();n++){
			storage.delete(url+"-"+n);
		}
		testsRepo.delete(test);
		return null;
	}

	public MedicalTest saveTest(MedicalTest test, String patientId){
		Patient patient = patientsRepo.findOne(patientId);
		test.setPatient(patient);
		return testsRepo.save(test);		
	}
}
