package pl.zt.mk.services.impl;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import pl.zt.mk.config.LocaleConfig;
import pl.zt.mk.services.MailSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michal on 02.04.2016.
 */
@Service
public class MailSenderImpl implements MailSender {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Autowired
	private LocaleConfig locale;

	@Autowired
	private InternationalizationServiceImpl i18n;

	@Override
	public void sendRegistrationEmail(final String name, final String email, final String password) {
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setTo(email);
			String subject = i18n.getMessage("mail-registration-title");
			message.setSubject(subject);

			Map model = new HashMap<>();
			model.put("name", name);
			model.put("email", email);
			model.put("password", password);
			model.put("resources", messageSource);
			model.put("locale", locale.localeProvider().getLocale());

			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/registrationMail.vm", "UTF-8", model);
			message.setText(text, true);
		};
		this.mailSender.send(preparator);
	}
}
