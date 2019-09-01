package capture.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capture.demo.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
