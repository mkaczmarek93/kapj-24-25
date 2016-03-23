package pl.zt.mk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Setter
@Getter
public abstract class BaseEntity {

	@Id
	private Long id;
	
}
