package com.kerbart.match.helper;

public class StringHelper {

    public static boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        if ("".equals(input)) {
            return true;
        }

        return false;
    }
}
