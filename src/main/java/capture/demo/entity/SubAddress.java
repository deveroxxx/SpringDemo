package capture.demo.entity;

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
@Table(name = "subAddress")
public class SubAddress extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY)
	private Address address;

	private Long code;

	@Override
	public String toString() {
		return EntityToString.log(this);
	}
}
