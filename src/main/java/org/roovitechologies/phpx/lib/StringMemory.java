/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.lib;

/**
 *
 * @author AndreyPC
 */
public class StringMemory implements Memory {

    public final String value;
    
    public StringMemory(String value){
        this.value = value;
    }
    
    @Override
    public int toInteger() {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e){
            return 0;
        }
    }
    
    @Override
    public double toDouble() {
        try {
            return Double.parseDouble(value);
        } catch(NumberFormatException e){
            return 0;
        }
    }

    @Override
    public String toString() {
        return value;
    }
    
    @Override
    public int type() {
        return Types.STRING;
    }

    @Override
    public Object raw() {
        return value;
    }
    
}
