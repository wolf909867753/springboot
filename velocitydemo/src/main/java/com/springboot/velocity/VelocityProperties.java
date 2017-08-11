package com.springboot.velocity;

import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanglu-jf on 17/8/10.
 */
//@Component
//@ConfigurationProperties(prefix = "spring.velocity")
//@Qualifier
public class VelocityProperties  extends AbstractTemplateViewResolverProperties {
    public static final String DEFAULT_RESOURCE_LOADER_PATH = "classpath:/templates/";
    public static final String DEFAULT_PREFIX = "";
    public static final String DEFAULT_SUFFIX = ".vm";
    private String dateToolAttribute;
    private String numberToolAttribute;
    private Map<String, String> properties = new HashMap();
    private String resourceLoaderPath = "classpath:/templates/";
    private String toolboxConfigLocation;
    private boolean preferFileSystemAccess = true;

    public VelocityProperties() {
        super("", ".vm");
    }

    public String getDateToolAttribute() {
        return this.dateToolAttribute;
    }

    public void setDateToolAttribute(String dateToolAttribute) {
        this.dateToolAttribute = dateToolAttribute;
    }

    public String getNumberToolAttribute() {
        return this.numberToolAttribute;
    }

    public void setNumberToolAttribute(String numberToolAttribute) {
        this.numberToolAttribute = numberToolAttribute;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getResourceLoaderPath() {
        return this.resourceLoaderPath;
    }

    public void setResourceLoaderPath(String resourceLoaderPath) {
        this.resourceLoaderPath = resourceLoaderPath;
    }

    public String getToolboxConfigLocation() {
        return this.toolboxConfigLocation;
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    public boolean isPreferFileSystemAccess() {
        return this.preferFileSystemAccess;
    }

    public void setPreferFileSystemAccess(boolean preferFileSystemAccess) {
        this.preferFileSystemAccess = preferFileSystemAccess;
    }

    public void applyToViewResolver(Object viewResolver) {
        super.applyToViewResolver(viewResolver);
        VelocityViewResolver resolver = (VelocityViewResolver)viewResolver;
        resolver.setToolboxConfigLocation(this.getToolboxConfigLocation());
        resolver.setDateToolAttribute(this.getDateToolAttribute());
        resolver.setNumberToolAttribute(this.getNumberToolAttribute());
    }

    @Override
    public String toString() {
        return "VelocityProperties{" +
                "dateToolAttribute='" + dateToolAttribute + '\'' +
                ", numberToolAttribute='" + numberToolAttribute + '\'' +
                ", properties=" + properties +
                ", resourceLoaderPath='" + resourceLoaderPath + '\'' +
                ", toolboxConfigLocation='" + toolboxConfigLocation + '\'' +
                ", preferFileSystemAccess=" + preferFileSystemAccess +
                '}';
    }
}
