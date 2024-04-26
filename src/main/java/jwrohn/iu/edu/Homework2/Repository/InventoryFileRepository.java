package jwrohn.iu.edu.Homework2.Repository;

import jwrohn.iu.edu.Homework2.model.GuitarData;
import org.springframework.data.repository.CrudRepository;

public interface InventoryFileRepository extends CrudRepository<GuitarData, Integer> {
    GuitarData findBySerialNumber(String serialNumber);
}