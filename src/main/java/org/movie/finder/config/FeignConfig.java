package org.movie.finder.config;

import feign.Logger;
import org.movie.finder.client.KinopoiskClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for feign clients.
 */
@Configuration
@EnableFeignClients(clients = KinopoiskClient.class)
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
