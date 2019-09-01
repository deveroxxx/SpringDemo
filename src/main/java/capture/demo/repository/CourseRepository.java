package capture.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capture.demo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


}
