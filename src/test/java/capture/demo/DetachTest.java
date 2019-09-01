package capture.demo;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;
import static org.springframework.transaction.annotation.Propagation.NEVER;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import capture.demo.entity.Address;
import capture.demo.entity.Student;
import capture.demo.entity.SubAddress;
import capture.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DetachTest {

	@Autowired
	private StudentService studentService;

	@Autowired
	private EntityManager entityManager;

	@Test
	@Transactional
	public void should_get_lazy_exception() {
		Student student = new Student();
		Address address = new Address();
		SubAddress subAddress = new SubAddress();
		subAddress.setCode(100L);
		address.setSubAddress(subAddress);
		student.setAddress(address);

		studentService.save(student);

		entityManager.flush();
		entityManager.clear();

		Student dbStudent = studentService.listAll().get(0);
		log.info(""+getAddressSubCode(dbStudent));
		log.info(""+getAddressSubCodeNonTransactional(dbStudent));
		log.info(""+getAddressSubCodeExceptionOnTransaction(dbStudent));
		log.info(""+getAddressSubCodeMandatoryTransaction(dbStudent));
		log.info(""+getAddressSubCodeNewTransaction(dbStudent));
	}

	public static Long getAddressSubCode(Student student) {
		return student.getAddress().getSubAddress().getCode();
	}


	@Transactional(propagation = NOT_SUPPORTED)
	public Long getAddressSubCodeNonTransactional(Student student) {
		return student.getAddress().getSubAddress().getCode();
	}

	@Transactional(propagation = NEVER)
	public Long getAddressSubCodeExceptionOnTransaction(Student student) {
		return student.getAddress().getSubAddress().getCode();
	}

	@Transactional(propagation = MANDATORY)
	public Long getAddressSubCodeMandatoryTransaction(Student student) {
		return student.getAddress().getSubAddress().getCode();
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Transactional(propagation = REQUIRES_NEW)
	public Long getAddressSubCodeNewTransaction(Student student) {
		return studentService.findById(student.getId()).get().getAddress().getSubAddress().getCode();
	}



}
