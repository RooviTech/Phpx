/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.ast;

/**
 *
 * @author AndreyPC
 */
public interface Visitor {
    
    void visit(TernaryExpression s);
    //void visit(IncludeStatement s);
    void visit(AssignmentStatement s);
    void visit(FunctionStatement s);
    void visit(FunctionDefinedStatement s);
    void visit(ArrayAccessExpression s);
    void visit(ArrayAssignmentStatement s);
    void visit(ArrayExpression s);
    void visit(BinaryExpression s);
    void visit(BlockStatement s);
    void visit(BreakStatement s);
    void visit(ConditionalExpression s);
    void visit(ConstantExpression s);
    void visit(ContinueStatement s);
    void visit(DoWhileStatement s);
    void visit(EchoStatement s);
    void visit(ForStatement s);
    void visit(FunctionalExpression s);
    void visit(IfStatement s);
    void visit(ReturnStatement s);
    void visit(StringExpression s);
    void visit(UnaryExpression s);
    void visit(ValueExpression s);
    void visit(VariableExpression s);
    void visit(WhileStatement s);
    
}
