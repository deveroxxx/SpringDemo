package capture.demo.service;

import org.springframework.stereotype.Service;

import capture.demo.entity.Address;
import capture.demo.repository.AddressRepository;

@Service
public class AddressService extends AbstractBaseService<Address, Long, AddressRepository> {

	public AddressService(AddressRepository repo) {
		super(repo);
	}
}
