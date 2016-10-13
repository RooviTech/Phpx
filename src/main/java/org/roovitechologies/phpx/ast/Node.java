package org.roovitechologies.phpx.ast;

/**
 *
 * @author aNNiMON
 */
public interface Node {
    
    void accept(Visitor visitor);
}
