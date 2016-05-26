package pl.zt.mk.repo;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zt.mk.config.providers.BundleProvider;
import pl.zt.mk.config.providers.JasperConfigProvider;
import pl.zt.mk.config.providers.LocaleProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zt on 2016-04-07.
 */
@Repository
public class JasperRepository {

	private final static String PATHSEPARATOR = File.separator;
	private final static String JSFILE = ".jrxml";
	private LocaleProvider locale;
	private BundleProvider bundle;
	private String templateSrc;

	@Autowired
	public JasperRepository(JasperConfigProvider configProvider, LocaleProvider locale, BundleProvider bundleProvider) {
		this.templateSrc = configProvider.getTemplatePath();
		this.locale = locale;
		this.bundle = bundleProvider;
	}

	public JasperPrint getReportTemplate(String name, JRDataSource dataSource) throws JRException {
		JasperReport report = JasperCompileManager.compileReport(templateSrc + parse(name));
		Map<String, Object> paramenters = getParameters();
		JasperPrint printable = JasperFillManager.fillReport(report, paramenters, dataSource);
		return printable;

	}

	private Map<String, Object> getParameters() {
		Map parameters = new HashMap<>();
		parameters.put("REPORT_LOCALE", locale.getLocale());
		parameters.put("REPORT_RESOURCE_BUNDLE", bundle.getBundle());
		return parameters;
	}

	private String parse(String name) {
		if (!name.startsWith(PATHSEPARATOR)) name = PATHSEPARATOR.concat(name);
		if (name.endsWith(JSFILE)) name.concat(JSFILE);
		return name;
	}
}
