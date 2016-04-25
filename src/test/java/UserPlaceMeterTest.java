import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.ProjectStarter;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.services.MeterService;
import pl.zt.mk.services.PlaceService;
import pl.zt.mk.services.UserService;

/**
 * Created by zt on 2016-04-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectStarter.class)
@TestPropertySource(locations = "classpath:application.properties")
public class UserPlaceMeterTest extends TestCase {

	@Autowired
	UserService userService;

	@Autowired
	PlaceService placeService;

	@Autowired
	MeterService meterService;

	@Test
	public void test() {

//		userService.addUser("admin","admin@admin.pl", Authorities.ROLE_ADMIN);
		UserDetail admin = userService.findByEmail("admin@admin.pl");
		//admin exists
		assertNotNull(admin);

		Place place = placeService.findPlaceByUserEmail("admin@admin.pl");
		//place doesnt exist
		assertNull(place);
		place = new Place();
		admin.setPlace(place);

		userService.updateUser(admin);
		place = null;
		place = placeService.findPlaceByUserEmail("admin@admin.pl");
		assertNotNull(place);

		Meter meter = new Meter();
		meter.setPlace(place);
		meterService.addMeter(meter);

		LazyModel<Meter> data = meterService.findByUserEmail("admin@admin.pl");

		assertNotNull(data);


	}


}
