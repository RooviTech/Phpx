package org.roovitechologies.phpx.visitors;

import org.roovitechologies.phpx.ast.*;

/**
 *
 * @author aNNiMON
 */
public final class FunctionAdder extends AbstractVisitor {

    @Override
    public void visit(FunctionDefinedStatement s) {
        super.visit(s);
        s.execute();
    }
}
