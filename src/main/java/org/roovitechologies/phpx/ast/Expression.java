package org.roovitechologies.phpx.ast;

import org.roovitechologies.phpx.lib.Memory;

public interface Expression extends Node {
    
    Memory eval();
	
}