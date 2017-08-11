package com.springboot.velocity;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

/**
 * Created by wanglu-jf on 17/8/10.
 */
//@Component
public class VelocityTemplateAvailabilityProvider implements TemplateAvailabilityProvider{
    public VelocityTemplateAvailabilityProvider() {
    }

    public boolean isTemplateAvailable(String view, Environment environment, ClassLoader classLoader, ResourceLoader resourceLoader) {
        if(ClassUtils.isPresent("org.apache.velocity.app.VelocityEngine", classLoader)) {
            RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(environment, "spring.velocity.");
            String loaderPath = resolver.getProperty("resource-loader-path", "classpath:/templates/");
            String prefix = resolver.getProperty("prefix", "");
            String suffix = resolver.getProperty("suffix", ".vm");
            return resourceLoader.getResource(loaderPath + prefix + view + suffix).exists();
        } else {
            return false;
        }
    }
}
