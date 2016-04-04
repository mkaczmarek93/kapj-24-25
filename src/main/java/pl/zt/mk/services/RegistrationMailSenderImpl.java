package pl.zt.mk.services;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import pl.zt.mk.entity.UserDetail;

import java.util.HashMap;
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

	@Override
	public void sendRegistrationEmail(final UserDetail user) {
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setTo(user.getEmail());
			message.setSubject("Registration in Housing Association Management");
			Map model = new HashMap<>();
			model.put("user", user);
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/registrationMail.vm", "UTF-8", model);
			message.setText(text, true);
		};
		this.mailSender.send(preparator);
	}
}
