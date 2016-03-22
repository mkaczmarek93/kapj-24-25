package pl.zt.mk.beans.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.services.UserService;

import java.io.Serializable;
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

    public void addUser(){
        if (Objects.nonNull(name) && Objects.nonNull(email)){
            userService.addUser(name,email);
        }
    }

}
