package capture.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capture.demo.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


}
