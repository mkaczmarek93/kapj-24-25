import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.zt.mk.config.providers.BundleProvider;
import pl.zt.mk.config.providers.JasperConfigProvider;
import pl.zt.mk.config.providers.LocaleProvider;
import pl.zt.mk.repo.JasperRepository;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by zt on 2016-04-08.
 */

public class ReportTests {


	private final static String testReport = "templates/jasper/I18nReport.jrxml";
	private final static String DEST = "src/main/resources/reports_output/";
	JasperRepository jasperRepository;
	private JasperConfigProvider jasperConfigProvider = Mockito.mock(JasperConfigProvider.class);
	private LocaleProvider localeProvider = Mockito.mock(LocaleProvider.class);
	private BundleProvider bundleProvider = Mockito.mock(BundleProvider.class);

	@Test
	public void test_pl() {
		//perfect
		test("src/main/resources/", "pl", "messages", "test_pl.html");

	}

	@Test
	public void test_en() {
		//locale jest zdefiniowany w aplikacji, ale nie ma tłumaczeń
		test("src/main/resources/", "en", "messages", "test_en.html");

	}

	@Test
	public void test_de() {
		// w aplikacjie nie jest zdefiniowany  ten locale
		test("src/main/resources/", "de", "messages", "test_de.html");

	}


	private void test(String src, String locale, String bundle, String dest) {

		try {
			Mockito.when(jasperConfigProvider.getTemplatePath()).thenReturn(src);
			Mockito.when(localeProvider.getLocale()).thenReturn(new Locale(locale));
			Mockito.when(bundleProvider.getBundle()).thenReturn(ResourceBundle.getBundle(bundle, new Locale(locale)));
			jasperRepository = new JasperRepository(jasperConfigProvider, localeProvider, bundleProvider);
			JasperPrint print = jasperRepository.getReportTemplate(testReport, new JREmptyDataSource());
			Assert.assertNotNull(print);
		} catch (JRException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}


}
