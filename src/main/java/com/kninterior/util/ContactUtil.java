package com.kninterior.util;

public class ContactUtil {

    public static String getEncodeName(String name) {
        StringBuilder buildName = new StringBuilder(name);
        return buildName.replace(1, getEndIndex(name), getReplaceString(name)).toString();
    }

    private static String getReplaceString(String name) {
        if(getEndIndex(name) > 2) {
            return "***";
        }

        return "" + "*".repeat(Math.max(0, getEndIndex(name) - 1));
    }

    private static int getEndIndex(String name) {
        if(name.length() < 3) {
            return 2;
        }

        return name.length() - 1;
    }
}
