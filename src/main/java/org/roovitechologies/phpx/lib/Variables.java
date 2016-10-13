package org.roovitechologies.phpx.lib;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public final class Variables {
	
	public static Map<String, Memory> variables = new HashMap<>();
	private final static Stack<Map<String, Memory>> stack = new Stack<>();
        
	static {
		
	}
	
        public static void push(){
            stack.push(new HashMap<>(variables));
        }
        public static void pop(){
            variables = stack.pop();
        }
        
	public static boolean isExists(String key){
		return variables.containsKey(key);
	}
	
	public static Memory get(String key){
		if(!isExists(key)) throw new RuntimeException("Variable '"+key+"' does not exists.");
		return variables.get(key);
	}
	
	public static void set(String key, Memory value){
		variables.put(key, value);
	}
	
}