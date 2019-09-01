package capture.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import capture.demo.log.EntityToString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "address")
public class Address extends BaseEntity {

	private String postalCode;

	@OneToOne(fetch = FetchType.LAZY)
	private Student student;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private SubAddress subAddress;

	@Override
	public String toString() {
		return EntityToString.log(this);
	}
}
