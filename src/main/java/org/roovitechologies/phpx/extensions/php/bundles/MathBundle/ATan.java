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
public class ATan implements Function {
    
    @Override
    public Memory execute(Memory... args) {
        if(args.length != 1){
            PhpxApi.errorArgumentCountException("atan", 1, args.length);
        }
        return NumberMemory.of(java.lang.Math.atan(args[0].toDouble()));
    }
            
}
