/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.lib;

import java.util.Arrays;

/**
 *
 * @author AndreyPC
 */
public class ArrayMemory implements Memory {

    private final Memory[] elements;

    static {
        Functions.set("array", new Function() {
            @Override
            public Memory execute(Memory... args) {
                return new ArrayMemory(args);
            }
        });
    }
    
    public ArrayMemory(int size) {
        this.elements = new Memory[size];
    }

    public ArrayMemory(ArrayMemory array) {
        this(array.elements);
    }
    
    public ArrayMemory(Memory[] elements) {
        this.elements = elements;
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }
    
    public Memory get(int index){
        return elements[index];
    }
    
    public void set(int index, Memory value){
        elements[index] = value;
    }
    
    @Override
    public int toInteger() {
        return elements.hashCode();
    }
    
    @Override
    public double toDouble() {
        throw new RuntimeException("You can't bring the array to the double");
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
    
    @Override
    public Object raw() {
        return elements;
    }

    @Override
    public int type() {
        return Types.ARRAY;
    }
    
}
