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
public final class UnknownFunctionException extends RuntimeException {
    
    public UnknownFunctionException(String message) {
        super(message);
    }
    
}
