package org.roovitechologies.phpx.ast;

public final class EchoStatement implements Statement {
	
	public final Expression expression;
	
	public EchoStatement(Expression expression){
		this.expression = expression;
	}
	
	@Override
	public void execute(){
		System.out.print(expression.eval().toString());
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
	@Override
	public String toString(){
                return "";
	}
	
}