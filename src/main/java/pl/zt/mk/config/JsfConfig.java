package pl.zt.mk.config;

import java.util.HashMap;
import java.util.Map;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import pl.zt.mk.jsf.ViewScope;

@Configuration
public class JsfConfig implements ServletContextAware {

	@Bean
    public ServletRegistrationBean facesServletRegistration() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, new String[] {"*.jsf", "*.xhtml"});
        servletRegistrationBean.setName("FacesServlet");
        servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
    }
	
    @Override
    public void setServletContext(ServletContext servletContext) {
    	servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        servletContext.setInitParameter("primefaces.THEME", "glass-x");
    }	

    @Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		Map<String, Object> scopes = new HashMap<>();
		scopes.put("view", new ViewScope());

		CustomScopeConfigurer bean = new CustomScopeConfigurer();
		bean.setScopes(scopes);

		return bean;
	}
    
}
