package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.NumberMemory;
import org.roovitechologies.phpx.lib.StringMemory;

public final class ValueExpression implements Expression {
	
	private final Memory value;
	
	public ValueExpression(double value){
                this.value = NumberMemory.of(value);
	}
	public ValueExpression(String value){
                this.value = new StringMemory(value);
	}
	
	@Override
	public Memory eval(){
		return value;
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
	@Override
	public String toString(){
		return value.toString();
	}
	
}