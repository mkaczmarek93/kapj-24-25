package pl.zt.mk.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zt on 2016-03-22.
 */
@Configuration
public class MimeMapper implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("eot", "application/vnd.ms-fontobject");
        mappings.add("otf", "font/opentype");
        mappings.add("ttf", "application/x-font-ttf");
        mappings.add("woff", "application/x-font-woff");
        mappings.add("svg", "image/svg+xml");
        mappings.add("woff2", "application/x-font-woff2");
        container.setMimeMappings(mappings);
    }
}