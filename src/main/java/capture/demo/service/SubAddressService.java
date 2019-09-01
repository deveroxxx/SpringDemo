package capture.demo.service;

import org.springframework.stereotype.Service;

import capture.demo.entity.SubAddress;
import capture.demo.repository.SubAddressRepository;

@Service
public class SubAddressService extends AbstractBaseService<SubAddress, Long, SubAddressRepository> {

	public SubAddressService(SubAddressRepository repo) {
		super(repo);
	}
}
