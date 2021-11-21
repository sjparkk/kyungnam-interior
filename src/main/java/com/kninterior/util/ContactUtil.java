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

        StringBuilder replaceString = new StringBuilder("");
        for (int i = 0; i < getEndIndex(name) - 1; i++) {
            replaceString.append("*");
        }

        return replaceString.toString();
    }

    private static int getEndIndex(String name) {
        if(name.length() < 3) {
            return 2;
        }

        return name.length() - 1;
    }
}
