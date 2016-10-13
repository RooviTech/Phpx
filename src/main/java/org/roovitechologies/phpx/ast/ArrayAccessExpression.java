/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.ast;

import java.util.List;
import org.roovitechologies.phpx.lib.ArrayMemory;
import org.roovitechologies.phpx.lib.Memory;
import org.roovitechologies.phpx.lib.Variables;

/**
 *
 * @author AndreyPC
 */
public class ArrayAccessExpression implements Expression {
    
    public final String variable;
    public final List<Expression> indexes;

    public ArrayAccessExpression(String variable, List<Expression> indexes) {
        this.variable = variable;
        this.indexes = indexes;
    }

    @Override
    public Memory eval() {
        return getArray().get(lastIndex());
    }
    
    public ArrayMemory getArray() {
        ArrayMemory array = consumeArray(Variables.get(variable));
        final int last = indexes.size() - 1;
        for (int i = 0; i < last; i++) {
            array = consumeArray( array.get(index(i)) );
        }
        return array;
    }
    
    public int lastIndex() {
        return index(indexes.size() - 1);
    }
    
    private int index(int index) {
        return indexes.get(index).eval().toInteger();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    private ArrayMemory consumeArray(Memory value) {
        if (value instanceof ArrayMemory) {
            return (ArrayMemory) value;
        } else {
            throw new RuntimeException("Array expected");
        }
    }
    
}
