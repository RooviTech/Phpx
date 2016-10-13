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
public final class ForStatement implements Statement {
    
    public final Statement initialization;
    public final Expression termination;
    public final Statement increment;
    public final Statement statement;

    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement block) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.statement = block;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.eval().toInteger() != 0; increment.execute()) {
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
