package osm.web.pacienci.dtos;

import java.util.LinkedList;
import java.util.List;

import osm.web.pacienci.model.Patient;

public class PatientDTO {

	private String id;

	private String name;

	private String surname;

	private String pesel;

	public Patient toPatient() {
		return new Patient(id, name, surname, pesel);
	}

	public static Iterable<PatientDTO> toDTO(Iterable<Patient> patients) {
		List<PatientDTO> dtos = new LinkedList<>();
		for (Patient patient : patients)
			dtos.add(toDTO(patient));
		return dtos;
	}

	public static PatientDTO toDTO(Patient patient) {
		PatientDTO dto = new PatientDTO();
		if (patient != null) {
			dto.setId(patient.getId());
			dto.setName(patient.getName());
			dto.setSurname(patient.getSurname());
			dto.setPesel(patient.getPesel());
		}
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	@Override
	public String toString() {
		return "PatientDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", pesel=" + pesel + "]";
	}

}
