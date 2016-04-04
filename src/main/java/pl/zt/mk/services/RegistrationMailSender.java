package pl.zt.mk.services;

/**
 * Created by Michal on 02.04.2016.
 */
public interface RegistrationMailSender {
	void sendRegistrationEmail(final String name, final String email, final String password);
}
