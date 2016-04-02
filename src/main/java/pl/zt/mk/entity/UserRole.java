package pl.zt.mk.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import pl.zt.mk.entity.meta.Authorities;

import javax.persistence.*;

/**
 * Created by zt on 2016-04-02.
 */
@Table
@Entity
@RequiredArgsConstructor
public class UserRole implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Authorities role;

	@Override
	public String getAuthority() {
		return role.name();
	}

	public UserRole(Authorities role) {
		this.role = role;
	}
}
