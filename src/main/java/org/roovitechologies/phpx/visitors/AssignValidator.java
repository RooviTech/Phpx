package org.roovitechologies.phpx.visitors;

import org.roovitechologies.phpx.lib.Variables;
import org.roovitechologies.phpx.ast.*;

/**
 *
 * @author aNNiMON
 */
public final class AssignValidator extends AbstractVisitor {

    @Override
    public void visit(AssignmentStatement s) {
        super.visit(s);
        if (Variables.isExists(s.variable)) {
            throw new RuntimeException("Cannot assign value to constant");
        }
    }
    
}
