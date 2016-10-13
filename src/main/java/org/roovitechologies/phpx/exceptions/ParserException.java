/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.exceptions;

import org.roovitechologies.phpx.Parser;
import org.roovitechologies.phpx.Token;

/**
 *
 * @author AndreyPC
 */
public final class ParserException extends RuntimeException {

    public ParserException(String message) {
        super(message);
    }
    
    public ParserException(String message, Token token) {
        super(message+" at line: "+token.getRow()+", position "+token.getCol()+", in file: "+Parser.file);
    }
    
}
