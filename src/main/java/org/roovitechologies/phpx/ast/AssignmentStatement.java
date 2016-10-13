package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.Variables;

public class AssignmentStatement implements Statement {
	
	public final String variable;
	public final Expression expression;
	
	public AssignmentStatement(String variable, Expression expression){
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public void execute(){
		final Memory result = expression.eval();
		Variables.set(variable, result);
	}
	
	@Override
	public String toString(){
		return String.format("%s", expression);
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
}