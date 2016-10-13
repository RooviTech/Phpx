package org.roovitechologies.phpx.lib;

/**
 *
 * @author aNNiMON
 */
public final class NumberMemory implements Memory {

    public static final NumberMemory MINUS_ONE, ZERO, ONE;

    private static final int CACHE_MIN = -128, CACHE_MAX = 127;
    private static final NumberMemory[] NUMBER_CACHE;
    static {
        final int length = CACHE_MAX - CACHE_MIN + 1;
        NUMBER_CACHE = new NumberMemory[length];
        int value = CACHE_MIN;
        for (int i = 0; i < length; i++) {
            NUMBER_CACHE[i] = new NumberMemory(value++);
        }

        final int zeroIndex = -CACHE_MIN;
        MINUS_ONE = NUMBER_CACHE[zeroIndex - 1];
        ZERO = NUMBER_CACHE[zeroIndex];
        ONE = NUMBER_CACHE[zeroIndex + 1];
    }
    
    public static NumberMemory fromBoolean(boolean b) {
        return b ? ONE : ZERO;
    }

    public static NumberMemory of(int value) {
        if (CACHE_MIN <= value && value <= CACHE_MAX) {
            return NUMBER_CACHE[-CACHE_MIN + value];
        }
        return new NumberMemory(value);
    }

    public static NumberMemory of(Number value) {
        return new NumberMemory(value);
    }
    
    private final Number value;
    
    private NumberMemory(Number value) {
        this.value = value;
    }
    
    @Override
    public int type() {
        return Types.NUMBER;
    }
    
    @Override
    public Number raw() {
        return value;
    }
    
    public boolean toBoolean() {
        return value.intValue() != 0;
    }
    
    public byte toByte() {
        return value.byteValue();
    }
    
    public short toShort() {
        return value.shortValue();
    }

    @Override
    public int toInteger() {
        return value.intValue();
    }

    public long toLong() {
        return value.longValue();
    }

    public float toFloat() {
        return value.floatValue();
    }

    @Override
    public double toDouble() {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
