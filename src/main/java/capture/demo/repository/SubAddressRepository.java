package capture.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import capture.demo.entity.SubAddress;

public interface SubAddressRepository  extends JpaRepository<SubAddress, Long> {

}
