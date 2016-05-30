package pl.zt.mk.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by zt on 2016-03-22.
 */
@Table
@Entity
@RequiredArgsConstructor
public class UserDetail implements org.springframework.security.core.userdetails.UserDetails {

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter
	@Setter
	@Column()
	private String name;
	@Getter
	@Setter
	@Column(unique = true)
	private String email;
	@Getter
	@Setter
	private String password;

	@OneToOne(cascade = CascadeType.PERSIST)
	@Getter
	@Setter
	private UserRole role;

	@OneToOne(cascade = CascadeType.PERSIST)
	@Getter
	@Setter
	private Place place;


	public UserDetail(String name, String email, String password, UserRole role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserDetail that = (UserDetail) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		return password != null ? password.equals(that.password) : that.password == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(role);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
