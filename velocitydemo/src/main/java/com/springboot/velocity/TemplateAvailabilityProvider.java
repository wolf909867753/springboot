package com.springboot.velocity;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by wanglu-jf on 17/8/10.
 */
public interface TemplateAvailabilityProvider {
    boolean isTemplateAvailable(String var1, Environment var2, ClassLoader var3, ResourceLoader var4);
}
