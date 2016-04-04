package pl.zt.mk.services;

import pl.zt.mk.entity.UserDetail;

/**
 * Created by Michal on 02.04.2016.
 */
public interface RegistrationMailSender {
	void sendRegistrationEmail(final UserDetail user);
}
