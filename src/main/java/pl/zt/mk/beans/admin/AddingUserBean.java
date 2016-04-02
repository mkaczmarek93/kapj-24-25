package pl.zt.mk.beans.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import pl.zt.mk.entity.meta.Authorities;
import pl.zt.mk.services.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by zt on 2016-03-22.
 */
@Component
@Scope(value = "view")
public class AddingUserBean implements Serializable {
    @Autowired
    UserService userService;

    @Getter @Setter
    private String name,email;
    @Getter
    @Setter
    private Authorities authorities;

    public void addUser(){
        if (Objects.nonNull(name) && Objects.nonNull(email) && Objects.nonNull(authorities)) {
            String message = "";
            FacesMessage.Severity severity;
            try {
                userService.addUser(name, email, authorities);
                message = "good";
                severity = FacesMessage.SEVERITY_INFO;
            } catch (DataAccessException e) {
                message = "bad";
                severity = FacesMessage.SEVERITY_FATAL;
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));

        }
    }


    public List<SelectItem> getRoles() {


        return Arrays.asList(new SelectItem(Authorities.ADMIN), new SelectItem(Authorities.USER));
    }

}
