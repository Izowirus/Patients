package osm.web.pacienci.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import osm.web.pacienci.model.Patient;
@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient,String> {


}
