package pl.zt.mk.service;

import pl.zt.mk.entity.User;

/**
 * Created by Michal on 02.04.2016.
 */
public interface RegistrationMailSenderService {
	void sendRegistrationEmail(final User user);
}
