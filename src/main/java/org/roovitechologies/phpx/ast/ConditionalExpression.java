package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.NumberMemory;
import org.roovitechologies.phpx.lib.StringMemory;

public final class ConditionalExpression implements Expression {
    
    public static enum Operator {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        
        EQUALS,
        NOT_EQUALS,
        
        LT,
        LTEQ,
        GT,
        GTEQ,
        
        AND,
        OR
    }
    
    public final Expression expr1, expr2;
    private final Operator operation;

    public ConditionalExpression(Operator operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Memory eval() {
        final Memory value1 = expr1.eval();
        final Memory value2 = expr2.eval();
        
        int number1, number2;
        if (value1 instanceof StringMemory) {
            number1 = value1.toString().compareTo(value2.toString());
            number2 = 0;
        } else {
            number1 = value1.toInteger();
            number2 = value2.toInteger();
        }
        
        boolean result;
        switch (operation) {
            case LT: result = number1 < number2; break;
            case LTEQ: result = number1 <= number2; break;
            case GT: result = number1 > number2; break;
            case GTEQ: result = number1 >= number2; break;
            case NOT_EQUALS: result = number1 != number2; break;
            
            case AND: result = (number1 != 0) && (number2 != 0); break;
            case OR: result = (number1 != 0) || (number2 != 0); break;
            
            case EQUALS: 
            default:
                result = number1 == number2; break;
        }
        return NumberMemory.fromBoolean(result);
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