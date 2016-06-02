package pl.zt.mk.services;

import java.io.File;

/**
 * Created by Michal on 02.04.2016.
 */
public interface MailSender {
	void sendRegistrationEmail(final String name, final String email, final String password);

	void sendReport(final String name, final String email, final File report, final boolean isMonth);
}
