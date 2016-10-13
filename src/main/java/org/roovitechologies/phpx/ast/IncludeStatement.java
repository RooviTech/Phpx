/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.ast;

import java.io.IOException;
import org.roovitechologies.phpx.launcher.Launcher;

/**
 *
 * @author AndreyPC
 */
public class IncludeStatement implements Statement {

    private final Expression expr;

    public IncludeStatement(Expression expr) {
        this.expr = expr;
    }
    
    @Override
    public void execute() {
        try {
            Launcher.evaluateFile(expr.eval().toString());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void accept(Visitor visitor) {
    }
    
}
