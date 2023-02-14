package com.abc.ms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
@Configuration
public class Beans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

}
