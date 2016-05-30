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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserRole userRole = (UserRole) o;

		if (!id.equals(userRole.id)) return false;
		return role == userRole.role;

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + role.hashCode();
		return result;
	}
}
