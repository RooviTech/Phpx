package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.Variables;

public final class VariableExpression implements Expression {
	
	public final String name;
	
	public VariableExpression(String name){
            this.name = name;
	}
	
	@Override
	public Memory eval(){
            return Variables.get(name);
	}

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
	
	@Override
	public String toString(){
		return String.format("%s", Variables.get(name).toString());
	}
	
}