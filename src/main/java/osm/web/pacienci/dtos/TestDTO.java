package osm.web.pacienci.dtos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import osm.web.pacienci.model.MedicalTest;

public class TestDTO {

	private String id;

	private String testDate; // Może bedzie potrzeba wymienić na Date

	private String pathToImg;

	private String numberOfFiles;

	private String shortDescription;

	private String description;

	private String patientId;

	public TestDTO() {
	}

	public MedicalTest toMedicalTest() {
		return new MedicalTest(id,
				LocalDate.parse(testDate),
				pathToImg,
				Long.parseLong(numberOfFiles),
				shortDescription,
				description);
	}

	public static Iterable<TestDTO> toDTO(Iterable<MedicalTest> patientTests) {
		List<TestDTO> dtos = new LinkedList<>();
		for (MedicalTest test : patientTests) {
			dtos.add(toDTO(test));
		}
		return dtos;
	}

	public static TestDTO toDTO(MedicalTest test) {
		TestDTO dto = new TestDTO();
		if(test == null) return dto;
		dto.setId(test.getId());
		dto.setTestDate(test.getTestDate().toString());
		dto.setPathToImg(test.getPathToImg());
		dto.setShortDescription(test.getShortDescription());
		dto.setDescription(test.getDescription());
		dto.setPatientId(test.getPatient().getId());
		dto.setNumberOfFiles(test.getNumberOfImg().toString());
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getPathToImg() {
		return pathToImg;
	}

	public void setPathToImg(String pathToImg) {
		this.pathToImg = pathToImg;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getNumberOfFiles() {
		return numberOfFiles;
	}

	public void setNumberOfFiles(String numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}

	@Override
	public String toString() {
		return "TestDTO [id=" + id + ", testDate=" + testDate + ", pathToImg=" + pathToImg + ", shortDescription="
				+ shortDescription + ", description=" + description + ", patientId=" + patientId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pathToImg == null) ? 0 : pathToImg.hashCode());
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
		result = prime * result + ((testDate == null) ? 0 : testDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestDTO other = (TestDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pathToImg == null) {
			if (other.pathToImg != null)
				return false;
		} else if (!pathToImg.equals(other.pathToImg))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (shortDescription == null) {
			if (other.shortDescription != null)
				return false;
		} else if (!shortDescription.equals(other.shortDescription))
			return false;
		if (testDate == null) {
			if (other.testDate != null)
				return false;
		} else if (!testDate.equals(other.testDate))
			return false;
		return true;
	}

}
