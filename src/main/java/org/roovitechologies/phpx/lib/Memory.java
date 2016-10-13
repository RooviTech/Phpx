/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.lib;

/**
 *
 * @author AndreyPC
 */
public interface Memory {
    
    double toDouble();
    int toInteger();
    
    @Override
    String toString();
    
    Object raw();
    int type();
    
}
