/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.exceptions;

/**
 *
 * @author AndreyPC
 */
public final class LexerException extends RuntimeException {

    public LexerException(String message) {
        super(message);
    }
    
    public LexerException(int row, int col, String message) {
        super(message+" at ["+row+":"+col+"]");
    }
    
}
