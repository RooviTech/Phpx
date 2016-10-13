/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.lib;

import java.util.List;
import org.roovitechologies.phpx.ast.ReturnStatement;
import org.roovitechologies.phpx.ast.Statement;

/**
 *
 * @author AndreyPC
 */
public class UserDefinedFunction implements Function {

    final List<String> argsNames;
    private final Statement body;

    public UserDefinedFunction(List<String> argsNames, Statement body) {
        this.argsNames = argsNames;
        this.body = body;
    }
    
    public int getArgumentCount(){
        return argsNames.size();
    }
    
    public String getArgumentName(int index){
        if(index < 0 || index >= getArgumentCount()) return "";
        return argsNames.get(index);
    }
    
    @Override
    public Memory execute(Memory... args) {
        try{
            body.execute();
            return NumberMemory.of(0);
        } catch(ReturnStatement rs) {
            return rs.getResult();
        }
    }
    
}
