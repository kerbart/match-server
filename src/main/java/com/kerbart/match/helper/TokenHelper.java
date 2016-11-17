package com.kerbart.match.helper;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenHelper {

    public static String generateToken() {
        return RandomStringUtils.randomAlphanumeric(42);
    }

}
