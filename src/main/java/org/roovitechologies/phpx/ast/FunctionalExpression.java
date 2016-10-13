package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AndreyPC
 */
public final class FunctionalExpression implements Expression {
    
    private final String name;
    public final List<Expression> arguments;
    
    public FunctionalExpression(String name) {
        this.name = name;
        arguments = new ArrayList<>();
    }
    
    public FunctionalExpression(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }
    
    public void addArgument(Expression arg) {
        arguments.add(arg);
    }

    @Override
    public Memory eval() {
        final int size = arguments.size();
        final Memory[] values = new Memory[size];
        for (int i = 0; i < size; i++) {
            values[i] = arguments.get(i).eval();
        }
        final Function function = Functions.get(name);
        if(function instanceof UserDefinedFunction){
            final UserDefinedFunction userFunction = (UserDefinedFunction)function;
            if(size != userFunction.getArgumentCount()) throw new RuntimeException("Arguments count mismatch!");
            Variables.push();
            for(int i=0;i<size;i++){
                Variables.set(userFunction.getArgumentName(i), values[i]);
            }
            final Memory result = userFunction.execute(values);
            Variables.pop();
            return result;
        }
        return Functions.get(name).execute(values);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
}
