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
@EqualsAndHashCode
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
