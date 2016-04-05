package pl.zt.mk.annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zt on 2016-04-05.
 */
@Component
@Scope(value = "view")
public @interface RequestScoped {
}
