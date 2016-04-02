package pl.zt.mk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Michal on 02.04.2016.
 */
@Entity
@Getter
@Setter
public class User {

	@Id
	private Long id;

	private String name;

	private String surname;

	private String email;

	private String password;

}
