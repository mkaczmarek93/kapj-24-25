import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.services.RegistrationMailSender;

/**
 * Created by zt on 2016-04-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MailingTest {

	@Autowired
	RegistrationMailSender sender;

	@Test
	public void test() {
		UserDetail userDetail = new UserDetail("name", "180192@edu.p.lodz.pl", "password", null);
		sender.sendRegistrationEmail(userDetail);
	}


}
