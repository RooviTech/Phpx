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
public final class WhileStatement implements Statement {

    public Expression condition;
    public Statement statement;
    
    public WhileStatement(Expression condition, Statement statement){
        this.condition = condition;
        this.statement = statement;
    }
    
    @Override
    public void execute() {
        while(condition.eval().toInteger() != 0) {
            try {
                statement.execute();
            } catch(BreakStatement bs){
                break;
            } catch(ContinueStatement cs){
                // continue;
            }
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
