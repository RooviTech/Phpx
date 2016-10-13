/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.extensions.php.bundles.MathBundle;

import org.roovitechologies.phpx.PhpxApi;
import org.roovitechologies.phpx.lib.NumberMemory;
import org.roovitechologies.phpx.lib.Function;
import org.roovitechologies.phpx.lib.Memory;

/**
 *
 * @author AndreyPC
 */
public class Max implements Function {
    
    @Override
    public Memory execute(Memory... args) {
        if(args.length != 2){
            PhpxApi.errorArgumentCountException("max", 2, args.length);
        }
        return NumberMemory.of(java.lang.Math.max(args[0].toInteger(), args[2].toInteger()));
    }
            
}
