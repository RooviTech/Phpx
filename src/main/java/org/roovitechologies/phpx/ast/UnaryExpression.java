package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.NumberMemory;
import org.roovitechologies.phpx.lib.Memory;

public final class UnaryExpression implements Expression {
    
    public final Expression expr1;
    private final Operator operation;
    
    public static enum Operator {
        PLUS,
        MINUS
    }

    public UnaryExpression(Operator operation, Expression expr1) {
        this.operation = operation;
        this.expr1 = expr1;
    }

    @Override
    public Memory eval() {
        switch (operation) {
            case PLUS: return NumberMemory.of(-expr1.eval().toInteger());
            case MINUS:
            default:
                return NumberMemory.of(expr1.eval().toInteger());
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return String.format("%s", expr1);
    }
}
