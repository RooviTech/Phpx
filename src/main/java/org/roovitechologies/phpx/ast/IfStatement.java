/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.ast;

/**
 *
 * @author AndreyPC
 */
public final class IfStatement implements Statement {
    
    public final Expression expression;
    public final Statement ifStatement, elseStatement;
    
    public IfStatement(Expression expression, Statement ifStatement, Statement elseStatement){
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public void execute() {
        final int result = expression.eval().toInteger();
        if(result != 0) {
            ifStatement.execute();
        }else if(elseStatement != null){
            elseStatement.execute();
        }
    }
    
    @Override
    public String toString(){
        return "";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
