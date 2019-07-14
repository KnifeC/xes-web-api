package org.haveagroup.xes.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CorsIntercepter()).addPathPatterns("*");

        //registry.addInterceptor(new PermissionIntercepter()).addPathPatterns("*");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("*")
        ;
    }
}
