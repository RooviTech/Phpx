package org.roovitechologies.phpx.lib;

import org.roovitechologies.phpx.exceptions.TypeException;

/**
 *
 * @author aNNiMON
 */
public class FunctionMemory implements Memory {
    
    public static final FunctionMemory EMPTY = new FunctionMemory(new Function(){
        @Override
        public Memory execute(Memory... args){
            return NumberMemory.ZERO;
        }
    });

    private final Function value;

    public FunctionMemory(Function value) {
        this.value = value;
    }
    
    @Override
    public int type() {
        return Types.FUNCTION;
    }
    
    @Override
    public Object raw() {
        return value;
    }
    
    @Override
    public int toInteger() {
        throw new TypeException("Cannot cast function to integer");
    }
    
    @Override
    public double toDouble() {
        throw new TypeException("Cannot cast function to number");
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Function getValue() {
        return value;
    }
    
}
