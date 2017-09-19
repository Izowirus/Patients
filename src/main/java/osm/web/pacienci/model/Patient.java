package osm.web.pacienci.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="PATIENTS")
public class Patient {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	@Column(name="id",unique=true)
	private String id;
	
	private String name;
	
	private String surname;
	@Column(unique=true)
	private String pesel;
	
	@OneToMany(mappedBy="patient", cascade=CascadeType.ALL)
	private Set<MedicalTest> tests = new HashSet<>();
	
	public Patient(){};

	public Patient(String id, String name, String surname, String pesel) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
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

	public Set<MedicalTest> getTest() {
		return tests;
	}

	public void setTest(Set<MedicalTest> test) {
		this.tests = test;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((tests == null) ? 0 : tests.hashCode());
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
		Patient other = (Patient) obj;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (tests == null) {
			if (other.tests != null)
				return false;
		} else if (!tests.equals(other.tests))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", PESEL=" + pesel + ", tests=" + tests
				+ "]";
	}
	
	
}
