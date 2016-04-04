package pl.zt.mk.beans.auth;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by zt on 2016-03-22.
 */
@Component
@Scope(value = "session")
public class AuthenticationBean implements Serializable {

    private String auth;

    public String getAuth(){
     return    SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
