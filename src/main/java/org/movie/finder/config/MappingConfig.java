package org.movie.finder.config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for mapping.
 */
@Configuration
@EnableAutoConfiguration
public class MappingConfig {

    /**
     * Object writer bean.
     *
     * @param objectMapper {@link ObjectMapper}
     * @return {@link ObjectWriter}
     */
    @Bean
    public ObjectWriter objectMapper(ObjectMapper objectMapper) {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        return writer;
    }
}
