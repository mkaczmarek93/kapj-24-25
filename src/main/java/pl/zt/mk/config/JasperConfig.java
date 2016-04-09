package pl.zt.mk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.zt.mk.config.providers.JasperConfigProvider;

/**
 * Created by zt on 2016-04-08.
 */
@Configuration
@PropertySource("classpath:jasper.properties")

public class JasperConfig {

	@Value("${jasper.template.dir}")
	private String templateSrc;

	@Bean
	public JasperConfigProvider jasperConfigProvider() {
		return () -> templateSrc;
	}
}
