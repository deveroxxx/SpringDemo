package capture.demo;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import capture.demo.entity.Address;
import capture.demo.entity.Student;
import capture.demo.entity.SubAddress;
import capture.demo.service.AddressService;
import capture.demo.service.StudentService;
import capture.demo.service.SubAddressService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CascadeTests {

	@Autowired
	private StudentService studentService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private SubAddressService subAddressService;

	@Autowired
	private EntityManager entityManager;


	/**
	 * Student has CascadeType.PERSIST on address
	 */
	@Test
	public void with_parent_save_child_should_be_saved() {
		Student student = new Student();
		student.setAddress(new Address());
		studentService.save(student);

		entityManager.flush();
		entityManager.clear();

		log.info("-----------------------------------------------------------------------");
		log.info(""+studentService.listAll());
		log.info(""+addressService.listAll());
		log.info("-----------------------------------------------------------------------");

		assertEquals(1, +studentService.listAll().size());
		assertEquals(1, +addressService.listAll().size());
	}

	/**
	 * "Deep" persist
	 */
	@Test
	public void sub_address_should_also_persisted() {
		Student student = new Student();
		Address address = new Address();
		address.setSubAddress(new SubAddress());
		student.setAddress(address);

		studentService.save(student);

		entityManager.flush();
		entityManager.clear();

		log.info("-----------------------------------------------------------------------");
		log.info(""+studentService.listAll());
		log.info(""+addressService.listAll());
		log.info(""+subAddressService.listAll());
		log.info("-----------------------------------------------------------------------");

		assertEquals(1, +studentService.listAll().size());
		assertEquals(1, +addressService.listAll().size());
		assertEquals(1, +subAddressService.listAll().size());
	}


	/**
	 * Address has no CascadeType.PERSIST on student
	 */
	@Test(expected = IllegalStateException.class)
	public void parent_should_not_be_saved() {
		Address address = new Address(); // Child
		address.setStudent(new Student()); // Parent
		addressService.save(address);

		entityManager.flush(); // Exception should thrown because Student is not saved
	}


}
