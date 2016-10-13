package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.StringMemory;
import org.roovitechologies.phpx.lib.NumberMemory;

public class BinaryExpression implements Expression {
	
	public final Expression expr1, expr2;
	private final Operator operation;
    
        public static enum Operator {
            PLUS,
            PLUSPLUS,
            MINUS,
            MULTIPLY,
            DIVIDE,
            DOT
        }
	
	public BinaryExpression(Operator operation, Expression expr1, Expression expr2){
		this.operation = operation;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	@Override
	public Memory eval(){
            // Какой-то глюк со мной творится, где-то 5-6 месяцев не трогал PHP, и привык что соединять строки +
            
            final Memory value1 = expr1.eval();
            final Memory value2 = expr2.eval();
            if((value1 instanceof StringMemory)){
                final String string1 = value1.toString();
                switch(operation){
                    case PLUS: {
                        final int iterations = value2.toInteger();
                        final StringBuilder buffer = new StringBuilder();
                        return new StringMemory(buffer.toString());
                    }
                    case DOT: {
                        return new StringMemory(string1 + value2.toString());
                    }
                }
            }
            
            final int num1 = value1.toInteger();
            final int num2 = value2.toInteger();
            switch(operation){
		case PLUSPLUS: return NumberMemory.of(num1 + 1);
		case PLUS: return NumberMemory.of(num1 + num2);
		case MINUS: return NumberMemory.of(num1 - num2);
		case MULTIPLY: return NumberMemory.of(num1 * num2);
		case DIVIDE: return NumberMemory.of(num1 / num2);
		default:  return NumberMemory.of(num1 + num2);
            }
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
	@Override
	public String toString(){
		return "";
	}
	
}