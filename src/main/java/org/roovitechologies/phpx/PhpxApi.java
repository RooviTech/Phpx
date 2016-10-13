package org.roovitechologies.phpx;

import org.roovitechologies.phpx.lib.Constants;
import org.roovitechologies.phpx.lib.NumberMemory;
import org.roovitechologies.phpx.lib.StringMemory;
import org.roovitechologies.phpx.lib.Variables;

public class PhpxApi {
	
	public PhpxApi setConstant(String name, int value){
		Constants.set(name, NumberMemory.of(value));
		return this;
	}
	public PhpxApi setConstant(String name, String value){
		Constants.set(name, new StringMemory(value));
		return this;
	}
	
	public PhpxApi setVariable(String name, int value){
		Variables.set(name, NumberMemory.of(value));
		return this;
	}
	public PhpxApi setVariable(String name, String value){
		Variables.set(name, new StringMemory(value));
		return this;
	}
        
        public static void errorArgumentCountException(String functionName, int requires, int given) {
            System.out.println("PhpErrorArgumentCountException: "+functionName+"() expects at least "+requires+" parameters, "+given+" given.");
            System.exit(-1);
        }
	
}