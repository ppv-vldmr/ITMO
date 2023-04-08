package org.example.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ParseUtils {
    public static long parseLong(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }
    public static <T extends Enum<T>> T parseEnum(String text, Class<T> clazz) {
        try {
            return Enum.valueOf(clazz, text);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
