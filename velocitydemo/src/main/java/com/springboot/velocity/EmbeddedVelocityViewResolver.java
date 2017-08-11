package com.springboot.velocity;

import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 * Created by wanglu-jf on 17/8/10.
 */
//@Component
public class EmbeddedVelocityViewResolver extends VelocityViewResolver {

    private String toolboxConfigLocation;

    public EmbeddedVelocityViewResolver() {
    }

    protected void initApplicationContext() {
        if(this.toolboxConfigLocation != null && VelocityView.class.equals(this.getViewClass())) {
            this.logger.info("Using EmbeddedVelocityToolboxView instead of default VelocityView due to specified toolboxConfigLocation");
            this.setViewClass(EmbeddedVelocityToolboxView.class);
        }

        super.initApplicationContext();
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        super.setToolboxConfigLocation(toolboxConfigLocation);
        this.toolboxConfigLocation = toolboxConfigLocation;
    }
}
