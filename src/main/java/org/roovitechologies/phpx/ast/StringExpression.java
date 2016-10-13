/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;

/**
 *
 * @author AndreyPC
 */
public class StringExpression implements Expression {
    
    @Override
    public Memory eval(){
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
