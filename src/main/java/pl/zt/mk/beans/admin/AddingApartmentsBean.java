package pl.zt.mk.beans.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.entity.Block;
import pl.zt.mk.entity.Place;
import pl.zt.mk.services.AddressService;
import pl.zt.mk.services.InternationalizationService;
import pl.zt.mk.validators.ApartmentsValidator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by Michal on 21.04.2016.
 */
@Component
@Scope(value = "view")
@Setter
@Getter
@Slf4j
public class AddingApartmentsBean {

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private AddressService addressService;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private InternationalizationService i18n;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private ApartmentsValidator apartmentsValidator;

	private List<Block> blocks;
	private Block selectedBlock;

	@PostConstruct
	public void init() {
		blocks = addressService.getAll();
	}

	public void saveApartments() {
		FacesMessage.Severity severity = null;
		String msg = null;
		if (apartmentsValidator.validateApartmentsNumberInOneBlock(selectedBlock.getPlaces())) {
			if (addressService.addAddress(selectedBlock)) {
				severity = FacesMessage.SEVERITY_INFO;
				msg = "good";
			} else {
				severity = FacesMessage.SEVERITY_ERROR;
				msg = "bad";
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, i18n.getMessage(msg), i18n.getMessage(msg)));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.getMessage("bad"), i18n.getMessage("same-apartment-number")));
		}
	}

	public void addNewApartment() {
		Place place = new Place();
		place.setBlock(selectedBlock);
		selectedBlock.getPlaces().add(place);
	}

	public void removeApartment(Place place) {
		selectedBlock.getPlaces().remove(place);
	}

	public void removeBlock(Block block) {
		log.info("Remove block with id=" + block.getId());
		FacesMessage.Severity severity = null;
		String msg = null;
		if (addressService.removeBlock(block)) {
			severity = FacesMessage.SEVERITY_INFO;
			msg = "good";
			init();
		} else {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = "bad";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, i18n.getMessage(msg), i18n.getMessage(msg)));
	}

}
