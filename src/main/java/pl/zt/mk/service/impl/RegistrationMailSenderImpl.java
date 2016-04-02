package pl.zt.mk.service.impl;

import lombok.Setter;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import pl.zt.mk.entity.User;
import pl.zt.mk.service.RegistrationMailSenderService;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Michal on 02.04.2016.
 */
@Setter
public class RegistrationMailSenderImpl implements RegistrationMailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Override
	public void sendRegistrationEmail(final User user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(user.getEmail());
				Map model = new HashMap<>();
				model.put("user", user);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "file:", "UTF-8", model);
				message.setText(text);
			}
		};
		this.mailSender.send(preparator);
	}

	@Bean
	VelocityEngine velocityEngine() {
		Properties properties = new Properties();
		properties.put("resource.loader", "class");
		properties.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine velocityEngine = new VelocityEngine(properties);
		return velocityEngine;
	}
}
