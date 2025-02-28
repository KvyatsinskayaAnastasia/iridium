package com.iridium.common.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public final class CommonRandomUtils {

    private CommonRandomUtils() { }

    /**
     * Get random value from enum.
     * @param clazz enum class
     * @param <T> type
     * @return randomized enum value
     */
    public static <T extends Enum<?>> T randomEnum(final Class<T> clazz) {
        return clazz.getEnumConstants()[RandomUtils.nextInt(0, clazz.getEnumConstants().length)];
    }

    /**
     * Get random values from list.
     * @param list list
     * @param count count of values
     * @param <T> type
     * @return result list
     */
    public static <T> List<T> randomFromList(final List<T> list, final int count) {
        if (count > list.size()) {
            return list;
        }
        List<T> result = new ArrayList<>();
        while (result.size() < count)  {
            var index = RandomUtils.nextInt(0, list.size() - 1);
            var value = list.get(index);
            if (!result.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }
}
