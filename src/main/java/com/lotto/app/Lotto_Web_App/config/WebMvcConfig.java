package com.lotto.app.Lotto_Web_App.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/health").setViewName("forward:/health");
        registry.addViewController("/playLotto").setViewName("playLotto");
        registry.addViewController("/checkTicket").setViewName("checkTicket");
    }
}
