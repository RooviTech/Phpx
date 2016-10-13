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
public class NullMemory implements Memory {
    
        @Override
        public Object raw() {
            return null;
        }

        @Override
        public int toInteger() {
            return 0;
        }

        @Override
        public double toDouble() {
            return 0;
        }

        @Override
        public String toString() {
            return "null";
        }

        @Override
        public int type() {
            return 482862660;
        }
        
}
