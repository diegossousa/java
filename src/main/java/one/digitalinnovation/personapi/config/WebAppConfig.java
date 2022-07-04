package one.digitalinnovation.personapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {
//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//            "classpath:/META-INF/resources/", "classpath:/static/"};

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//        registry.addResourceHandler("/**")
//                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver());
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/device").setViewName("device");
//        registry.addRedirectViewController("/login", "/index.html");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://192.168.1.13:3000", "http://localhost:3000").allowCredentials(true);
    }
}
