package org.roovitechologies.phpx.lib;

public final class Types {

    public static final int
            NUMBER = 1,
            STRING = 2,
            ARRAY = 3,
            FUNCTION = 4,
            MAP = 5;
    
    private static final int FIRST = NUMBER, LAST = MAP;
    private static final String[] NAMES = {"number", "string", "array", "function", "map"};
    
    public static String typeToString(int type) {
        if (FIRST <= type && type <= LAST) {
            return NAMES[type];
        }
        return "unknown (" + type + ")";
    }
}
