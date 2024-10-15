package com.example.demo.configuration;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Main Spring configuration for beans */
@Configuration
public class BeanConfiguration {

    /*
     * Here we can configure all the extra information we could need to map another class that is not so straightforward
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mm.getConfiguration().setPropertyCondition(ctx -> Hibernate.isInitialized(ctx.getSource()));

        return mm;
    }
}
