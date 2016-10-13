package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.*;
import java.util.List;

/**
 *
 * @author AndreyPC
 */
public final class ArrayExpression implements Expression {
    
    public final List<Expression> elements;

    public ArrayExpression(List<Expression> elements) {
        this.elements = elements;
    }

    @Override
    public Memory eval() {
        final int size = elements.size();
        final ArrayMemory array = new ArrayMemory(size);
        for(int i=0;i<size;i++){
            array.set(i, elements.get(i).eval());
        }
        return array;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
}
