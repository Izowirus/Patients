package osm.web.pacienci.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import osm.web.pacienci.model.MedicalTest;
@Repository
public interface TestRepository extends PagingAndSortingRepository<MedicalTest, String> {

}
