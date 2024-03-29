package org.haveagroup.xes.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new PermissionIntercepter()).addPathPatterns("*");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.warn("已经调用addCorsMappings");
        registry.addMapping("/**");
    }
}
