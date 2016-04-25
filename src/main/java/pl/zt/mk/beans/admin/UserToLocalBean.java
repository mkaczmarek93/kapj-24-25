package pl.zt.mk.beans.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.zt.mk.annotations.RequestScoped;
import pl.zt.mk.entity.Place;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.services.InternationalizationService;
import pl.zt.mk.services.PlaceService;
import pl.zt.mk.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Objects;

/**
 * Created by Michal on 22.04.2016.
 */
@Component
@Setter
@Getter
@RequestScoped
public class UserToLocalBean {

	private List<UserDetail> users;
	private List<Place> places;
	private List<UserDetail> usersWithLocal;
	private Place selectedPlace;
	private UserDetail selectedUser;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private UserService userService;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private PlaceService placeService;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private InternationalizationService i18n;

	@PostConstruct
	public void init() {
		usersWithLocal = userService.findUsersWithLocal();
		users = userService.findUsersWithoutPlace();
		places = placeService.findPlacesWithoutUser();
	}

	public void assignUserToLocal() {
		if (Objects.isNull(selectedPlace) || Objects.isNull(selectedUser)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.getMessage("bad"), i18n.getMessage("user-room-required")));
			return;
		}
		String msg = null;
		FacesMessage.Severity severity = null;
		selectedUser.setPlace(selectedPlace);
		int userIndex = users.indexOf(selectedUser);
		UserDetail saved = userService.saveUser(selectedUser);
		if (null != saved) {
			severity = FacesMessage.SEVERITY_INFO;
			msg = "good";
			users.remove(userIndex);
			places.remove(selectedPlace);
			usersWithLocal.add(saved);
			selectedUser = null;
			selectedUser = null;
		} else {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = "bad";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, i18n.getMessage(msg), i18n.getMessage(msg)));
	}

	public void removeAssigned(UserDetail user) {
		FacesMessage.Severity severity = null;
		String msg = null;
		int userIndex = usersWithLocal.indexOf(user);
		Place place = user.getPlace();
		user.setPlace(null);
		place.setUserDetail(null);
		UserDetail saved = userService.saveUser(user);
		if (null != saved) {
			severity = FacesMessage.SEVERITY_INFO;
			msg = "good";
			usersWithLocal.remove(userIndex);
			users.add(user);
			places.add(place);
		} else {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = "bad";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, i18n.getMessage(msg), i18n.getMessage(msg)));
	}

}
