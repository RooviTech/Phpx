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
public class ReturnStatement extends RuntimeException implements Statement {

    public Expression expression;
    private Memory result;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }
    
    public Memory getResult(){
        return result;
    }
    
    @Override
    public void execute() {
        result = expression.eval();
        throw this;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
