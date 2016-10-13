package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;

/**
 *
 * @author AndreyPC
 */
public final class TernaryExpression implements Expression {

    public final Expression condition;
    public final Expression trueExpr, falseExpr;

    public TernaryExpression(Expression condition, Expression trueExpr, Expression falseExpr) {
        this.condition = condition;
        this.trueExpr = trueExpr;
        this.falseExpr = falseExpr;
    }

    @Override
    public Memory eval() {
        if (condition.eval().toInteger() != 0) {
            return trueExpr.eval();
        } else {
            return falseExpr.eval();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
}
