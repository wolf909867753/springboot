//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springboot.velocity;

//@Component
//@Configuration
//@ConditionalOnClass({VelocityEngine.class, VelocityEngineFactory.class})
//@AutoConfigureAfter({WebMvcAutoConfiguration.class})
//@EnableConfigurationProperties({VelocityProperties.class})
public class VelocityAutoConfiguration {
//    private static final Log logger = LogFactory.getLog(VelocityAutoConfiguration.class);
//    @Autowired
//    private ApplicationContext applicationContext;
//    @Autowired
//    private VelocityProperties properties;
//
//    public VelocityAutoConfiguration() {
//    }
//
//    @PostConstruct
//    public void checkTemplateLocationExists() {
//        if(this.properties.isCheckTemplateLocation()) {
//            TemplateLocation location = new TemplateLocation(this.properties.getResourceLoaderPath());
//            if(!location.exists(this.applicationContext)) {
//                logger.warn("Cannot find template location: " + location + " (please add some templates, check your Velocity " + "configuration, or set spring.velocity." + "checkTemplateLocation=false)");
//            }
//        }
//
//    }
//
//    @Configuration
//    @ConditionalOnClass({Servlet.class})
//    @ConditionalOnWebApplication
//    public static class VelocityWebConfiguration extends VelocityAutoConfiguration.VelocityConfiguration {
//        public VelocityWebConfiguration() {
//        }
//
//        @Bean
//        @ConditionalOnMissingBean({VelocityConfig.class})
//        public VelocityConfigurer velocityConfigurer() {
//            VelocityConfigurer configurer = new VelocityConfigurer();
//            this.applyProperties(configurer);
//            return configurer;
//        }
//
//        @Bean
//        public VelocityEngine velocityEngine(VelocityConfigurer configurer) throws VelocityException, IOException {
//            return configurer.getVelocityEngine();
//        }
//
//        @Bean
//        @ConditionalOnMissingBean(
//                name = {"velocityViewResolver"}
//        )
//        @ConditionalOnProperty(
//                name = {"spring.velocity.enabled"},
//                matchIfMissing = true
//        )
//        public EmbeddedVelocityViewResolver velocityViewResolver() {
//            EmbeddedVelocityViewResolver resolver = new EmbeddedVelocityViewResolver();
//            this.properties.applyToViewResolver(resolver);
//            return resolver;
//        }
//
//        @Bean
//        @ConditionalOnMissingBean
//        @ConditionalOnEnabledResourceChain
//        public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
//            return new ResourceUrlEncodingFilter();
//        }
//    }
//
//    @Configuration
//    @ConditionalOnNotWebApplication
//    public static class VelocityNonWebConfiguration extends VelocityAutoConfiguration.VelocityConfiguration {
//        public VelocityNonWebConfiguration() {
//        }
//
//        @Bean
//        @ConditionalOnMissingBean
//        public VelocityEngineFactoryBean velocityConfiguration() {
//            VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
//            this.applyProperties(velocityEngineFactoryBean);
//            return velocityEngineFactoryBean;
//        }
//    }
//
//    protected static class VelocityConfiguration {
//        @Autowired
//        protected VelocityProperties properties;
//
//        protected VelocityConfiguration() {
//        }
//
//        protected void applyProperties(VelocityEngineFactory factory) {
//            factory.setResourceLoaderPath(this.properties.getResourceLoaderPath());
//            factory.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
//            Properties velocityProperties = new Properties();
//            velocityProperties.setProperty("input.encoding", this.properties.getCharsetName());
//            velocityProperties.putAll(this.properties.getProperties());
//            factory.setVelocityProperties(velocityProperties);
//        }
//    }
}
