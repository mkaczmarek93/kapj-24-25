import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.ProjectStarter;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.services.RegistrationMailSender;

/**
 * Created by zt on 2016-04-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProjectStarter.class})
@DirtiesContext
public class MailingTest extends TestCase {

	@Autowired
	RegistrationMailSender sender;

	@Test
	public void test() {
		UserDetail userDetail = new UserDetail("name", "180192@edu.p.lodz.pl", "password", null);
		sender.sendRegistrationEmail(userDetail);
		assertTrue(true);

	}


}
