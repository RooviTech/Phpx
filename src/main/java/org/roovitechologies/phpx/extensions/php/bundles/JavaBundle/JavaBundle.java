package org.roovitechologies.phpx.extensions.php.bundles.JavaBundle;

import org.roovitechologies.phpx.extensions.Extension;
import org.roovitechologies.phpx.lib.Function;
import org.roovitechologies.phpx.lib.Functions;
import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.NullMemory;

public final class JavaBundle implements Extension {

    private static final Memory NULL = new NullMemory();

    @Override
    public void Register() {
        Functions.set("newJavaClass", new Function(){
            @Override
            public Memory execute(Memory... args){
                return args[0];
            }
        });
    }
    
}
