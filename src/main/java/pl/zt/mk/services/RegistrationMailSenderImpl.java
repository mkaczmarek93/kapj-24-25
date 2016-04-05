package pl.zt.mk.services;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Michal on 02.04.2016.
 */
@Service
public class RegistrationMailSenderImpl implements RegistrationMailSender {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Override
	public void sendRegistrationEmail(final String name, final String email, final String password) {
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setTo(email);
			message.setSubject("Registration in Housing Association Management");

			Map model = new HashMap<>();
			model.put("name", name);
			model.put("email", email);
			model.put("password", password);

			model.put("resources", messageSource);
			model.put("locale", new Locale("pl"));

			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/registrationMail.vm", "UTF-8", model);
			message.setText(text, true);
		};
		this.mailSender.send(preparator);
	}
}
