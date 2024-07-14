package com.iridium.configuration;

import com.iridium.configuration.converter.StringToAbilityTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public final void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new StringToAbilityTypeConverter());
    }
}
