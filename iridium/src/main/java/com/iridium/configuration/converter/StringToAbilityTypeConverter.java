package com.iridium.configuration.converter;

import com.iridium.library.entity.power.AbilityTypeEO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAbilityTypeConverter implements Converter<String, AbilityTypeEO> {
    @Override
    public final AbilityTypeEO convert(final String source) {
        return AbilityTypeEO.valueOf(source.toUpperCase());
    }
}
