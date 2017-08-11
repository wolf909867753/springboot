package com.springboot.velocity;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.context.ChainedContext;
import org.apache.velocity.tools.view.servlet.ServletToolboxManager;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by wanglu-jf on 17/8/10.
 */
//@Component
public class EmbeddedVelocityToolboxView extends VelocityToolboxView {
    public EmbeddedVelocityToolboxView() {
    }

    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ChainedContext context = new ChainedContext(new VelocityContext(model), this.getVelocityEngine(), request, response, this.getServletContext());
        if(this.getToolboxConfigLocation() != null) {
            this.setContextToolbox(context);
        }

        return context;
    }

    private void setContextToolbox(ChainedContext context) {
        ServletToolboxManager toolboxManager = ServletToolboxManager.getInstance(this.getToolboxConfigFileAwareServletContext(), this.getToolboxConfigLocation());
        Map toolboxContext = toolboxManager.getToolbox(context);
        context.setToolbox(toolboxContext);
    }

    private ServletContext getToolboxConfigFileAwareServletContext() {
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(this.getServletContext());
        factory.addAdvice(new EmbeddedVelocityToolboxView.GetResourceMethodInterceptor(this.getToolboxConfigLocation()));
        return (ServletContext)factory.getProxy(this.getClass().getClassLoader());
    }

    private static class GetResourceMethodInterceptor implements MethodInterceptor {
        private final String toolboxFile;

        GetResourceMethodInterceptor(String toolboxFile) {
            if(toolboxFile != null && !toolboxFile.startsWith("/")) {
                toolboxFile = "/" + toolboxFile;
            }

            this.toolboxFile = toolboxFile;
        }

        public Object invoke(MethodInvocation invocation) throws Throwable {
            if(invocation.getMethod().getName().equals("getResourceAsStream") && invocation.getArguments()[0].equals(this.toolboxFile)) {
                InputStream inputStream = (InputStream)invocation.proceed();
                if(inputStream == null) {
                    try {
                        inputStream = (new ClassPathResource(this.toolboxFile, Thread.currentThread().getContextClassLoader())).getInputStream();
                    } catch (Exception var4) {
                        ;
                    }
                }

                return inputStream;
            } else {
                return invocation.proceed();
            }
        }
    }
}
