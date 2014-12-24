package com.abctech.dobry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BeanConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RestTemplate addRestTemplate() {
        return new RestTemplate();
    }
}
