package org.roovitechologies.phpx.lib;

import java.util.HashMap;
import java.util.Map;
import org.roovitechologies.phpx.PhpxApi;
import org.roovitechologies.phpx.exceptions.UnknownFunctionException;

/**
 *
 * @author AndreyPC
 */
public final class Functions {

    private static final Map<String, Function> functions = new HashMap<>();
    
    static {
        
        new Constants();
        new Variables();
        
        Functions.set("print", new Function() {
            @Override
            public Memory execute(Memory... args) {
                if(args.length <= 0){
                    PhpxApi.errorArgumentCountException("print", 1, args.length);
                }
                for(int i=0;i<args.length;i++) System.out.println(args[i].toString());
                return NumberMemory.of(0);
            }
        });
        Functions.set("exit", new Function() {
            @Override
            public Memory execute(Memory... args) {
                if(args.length != 1){
                    PhpxApi.errorArgumentCountException("exit", 1, args.length);
                }
                System.exit((int)args[0].toInteger());
                return NumberMemory.of(0);
            }
        });
    }
    
    public static boolean isExists(String key) {
        return functions.containsKey(preferName(key));
    }
    
    public static Function get(String key) {
        if (!isExists(preferName(key))) throw new UnknownFunctionException("Unknown function " + preferName(key));
        return functions.get(preferName(key));
    }
    
    public static void set(String key, Function function) {
        functions.put(preferName(key), function);
    }
	
    private static String preferName(String name){
        return name.toLowerCase();
    }
        
}
