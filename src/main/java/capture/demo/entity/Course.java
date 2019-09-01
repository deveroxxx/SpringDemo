package capture.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "course")
public class Course extends BaseEntity {

	private String courseName;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Student> students;

	@ManyToOne(fetch = FetchType.LAZY)
	private Teacher teacher;

}
