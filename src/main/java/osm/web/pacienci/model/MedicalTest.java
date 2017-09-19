package osm.web.pacienci.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TESTS")
public class MedicalTest {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;

	private LocalDate testDate; // Może bedzie potrzeba wymienić na Date

	private String pathToImg;
	
	private Long numberOfImg;

	private String shortDescription;

	private String description;
	@ManyToOne
	@JoinColumn
	private Patient patient;

	public MedicalTest() {
	}

	public MedicalTest(String id, LocalDate testDate, String pathToImg, Long numberOfFiles, String shortDescription, String description) {
		this.id = id;
		this.testDate = testDate;
		this.shortDescription = shortDescription;
		this.description = description;
		this.pathToImg = pathToImg;
		this.numberOfImg = numberOfFiles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDate testDate) {
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Long getNumberOfImg() {
		return numberOfImg;
	}

	public void setNumberOfImg(Long numberOfImg) {
		this.numberOfImg = numberOfImg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numberOfImg == null) ? 0 : numberOfImg.hashCode());
		result = prime * result + ((pathToImg == null) ? 0 : pathToImg.hashCode());
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
		MedicalTest other = (MedicalTest) obj;
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
		if (numberOfImg == null) {
			if (other.numberOfImg != null)
				return false;
		} else if (!numberOfImg.equals(other.numberOfImg))
			return false;
		if (pathToImg == null) {
			if (other.pathToImg != null)
				return false;
		} else if (!pathToImg.equals(other.pathToImg))
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

	@Override
	public String toString() {
		return "MedicalTest [id=" + id + ", testDate=" + testDate + ", pathToImg=" + pathToImg + ", numberOfImg="
				+ numberOfImg + ", shortDescription=" + shortDescription + ", description=" + description +"]";
	}



}
