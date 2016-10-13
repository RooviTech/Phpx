package org.roovitechologies.phpx.ast;

/**
 *
 * @author AndreyPC
 */
public final class ArrayAssignmentStatement implements Statement {
    
    public final ArrayAccessExpression array;
    public final Expression expression;

    public ArrayAssignmentStatement(ArrayAccessExpression array, Expression expression) {
        this.array = array;
        this.expression = expression;
    }
    
    @Override
    public void execute() {
        array.getArray().set(array.lastIndex(), expression.eval());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
}
