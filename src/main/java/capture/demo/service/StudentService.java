package capture.demo.service;

import org.springframework.stereotype.Service;

import capture.demo.entity.Student;
import capture.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentService extends AbstractBaseService<Student, Long, StudentRepository> {

	public StudentService(StudentRepository repo) {
		super(repo);
	}



}
