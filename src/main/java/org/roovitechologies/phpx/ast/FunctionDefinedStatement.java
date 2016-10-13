/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.ast;

import java.util.List;
import org.roovitechologies.phpx.lib.Functions;
import org.roovitechologies.phpx.lib.UserDefinedFunction;

/**
 *
 * @author AndreyPC
 */
public class FunctionDefinedStatement implements Statement {
    
    private final String name;
    final List<String> argsNames;
    public final Statement body;

    public FunctionDefinedStatement(String name, List<String> argsNames, Statement body) {
        this.name = name;
        this.argsNames = argsNames;
        this.body = body;
    }

    @Override
    public void execute() {
        Functions.set(name, new UserDefinedFunction(argsNames, body));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
