package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Constants;
import org.roovitechologies.phpx.lib.Memory;

public final class ConstantExpression implements Expression {
	
	private final String name;
	
	public ConstantExpression(String name){
        this.name = name;
	}
	
	@Override
	public Memory eval(){
		return Constants.get(name);
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
	@Override
	public String toString(){
		return String.format("%s", Constants.get(name));
	}
	
}