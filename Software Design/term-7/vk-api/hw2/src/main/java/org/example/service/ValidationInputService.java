package org.example.service;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;


public class ValidationInputService {
    private static final Pattern HASHTAG_REGEX_PATTERN = Pattern.compile("#\\S+");
    private static final int LOWER_LIMIT_HOUR_NUMBERS = 1;
    private static final int UPPER_LIMIT_HOUR_NUMBERS = 24;

    public boolean validationInput(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            return false;
        }

        if (!StringUtils.isNumeric(args[1])) {
            return false;
        }

        String query = args[0];
        int hourNumbers = Integer.parseInt(args[1]);

        return HASHTAG_REGEX_PATTERN.matcher(query).matches() && LOWER_LIMIT_HOUR_NUMBERS <= hourNumbers && hourNumbers <= UPPER_LIMIT_HOUR_NUMBERS;
    }
}
