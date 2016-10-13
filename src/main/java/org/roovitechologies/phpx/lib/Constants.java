package org.roovitechologies.phpx.lib;

import java.util.Map;
import java.util.HashMap;
import org.roovitechologies.phpx.PhpxApi;

public final class Constants {
	
	private static Map<String, Memory> constants = new HashMap<>();
	
	static {
		set("true", NumberMemory.fromBoolean(true));
		set("false", NumberMemory.fromBoolean(false));
                
                Functions.set("define", new Function() {
                    @Override
                    public Memory execute(Memory... args) {
                        if(args.length != 2){
                            PhpxApi.errorArgumentCountException("define", 2, args.length);
                        }
                        Memory content;
                        if(args[1] instanceof StringMemory){
                            content = new StringMemory(args[1].toString());
                        }else{
                            content = NumberMemory.of(args[1].toInteger());
                        }
                        Constants.set(args[0].toString(), content);
                        return NumberMemory.of(0);
                    }
                });
        
	}
	
	public static boolean isExists(String key){
		return constants.containsKey(key);
	}
	
	public static void set(String key, Memory value){
		constants.put(key, value);
        }
	
	public static Memory get(String key){
		if(!isExists(key)) throw new RuntimeException("Constant '"+key+"' does not exists.");
		return constants.get(key);
	}
	
}