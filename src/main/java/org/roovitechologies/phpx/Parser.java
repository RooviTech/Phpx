package org.roovitechologies.phpx;

import java.util.ArrayList;
import java.util.List;
import org.roovitechologies.phpx.ast.*;
import org.roovitechologies.phpx.exceptions.ParserException;

/**
 *
 * @author AndreyPC
 */
public class Parser {

	private static final Token EOF = new Token(Tokens.T_EOF, "", -1, -1);

	private final List<Token> tokens;
	public static String file;
	private final int size;

	private int pos;

	public Parser(List<Token> tokens, String file) {
		this.tokens = tokens;
		this.file = file;
		size = tokens.size();
	}

	public Statement parse() {
		final BlockStatement result = new BlockStatement();
		while (!match(Tokens.T_EOF)){
			result.add(statement());
		}
		return result;
	}

    private Statement block() {
        final BlockStatement block = new BlockStatement();
        consume(Tokens.T_LBRACE);
        while (!match(Tokens.T_RBRACE)){
			block.add(statement());
        }
        return block;
    }

    private Statement statementOrBlock() {
        if (get(0).getType() == Tokens.T_LBRACE) return block();
        return statement();
    }

    private Statement statement() {
        if (match(Tokens.T_ECHO)) {
            return echo();
        }
        if (match(Tokens.T_IF)) {
            return ifElse();
        }
        if (match(Tokens.T_WHILE)) {
            return whileStatement();
        }
        if (match(Tokens.T_DO)) {
            return doWhileStatement();
        }
        if (match(Tokens.T_BREAK)) {
            consume(Tokens.T_SEMICOLON);
            return new BreakStatement();
        }
        if (match(Tokens.T_CONTINUE)) {
            consume(Tokens.T_SEMICOLON);
            return new ContinueStatement();
        }
        if (match(Tokens.T_RETURN)) {
            Expression result = expression();
            consume(Tokens.T_SEMICOLON);
            return new ReturnStatement(result);
        }
        if (match(Tokens.T_FOR)) {
            return forStatement();
        }
        if (match(Tokens.T_FUNCTION)) {
            return functionDefine();
        }
        if (match(Tokens.T_USE)) {
            return use();
        }
        if (match(Tokens.T_INCLUDE)) {
            Expression expression = expression();
            consume(Tokens.T_SEMICOLON);
            return new IncludeStatement(expression);
        }
        if (get(0).getType() == Tokens.T_WORD && get(1).getType() == Tokens.T_LPAREN) {
            return new FunctionStatement(function());
        }
        return assignmentStatement();
    }

    private Statement assignmentStatement() {
        final Token current = get(0);
        if (lookMatch(0, Tokens.T_VARIABLE) && lookMatch(1, Tokens.T_EQ)) {
            final String variable = consume(Tokens.T_VARIABLE).getValue();
            consume(Tokens.T_EQ);
            Expression expr = expression();
            consume(Tokens.T_SEMICOLON);
            return new AssignmentStatement(variable, expr);
        }
        if (lookMatch(0, Tokens.T_VARIABLE) && lookMatch(1, Tokens.T_LBRACKET)) {
            final ArrayAccessExpression array = element();
            consume(Tokens.T_EQ);
            return new ArrayAssignmentStatement(array, expression());
        }
        throw new ParserException("Unknown statement "+current.getValue(), current);
    }

    private Statement echo(){
        return new EchoStatement(expression());
    }

    private Statement use(){
        final StringBuilder namespace = new StringBuilder();
        while (!match(Tokens.T_SEMICOLON)) {
            namespace.append(consume(Tokens.T_WORD).getValue()+".");
            match(Tokens.T_BACKSLASH);
        }
        String result = namespace.toString().substring(0, namespace.toString().length()-1);
        return new UseStatement(result);
    }

    private Statement ifElse() {
        consume(Tokens.T_LPAREN);
        final Expression condition = expression();
        consume(Tokens.T_RPAREN);
        final Statement ifStatement = statementOrBlock();
        final Statement elseStatement;
        if (match(Tokens.T_ELSE)) {
            elseStatement = statementOrBlock();
        } else {
            elseStatement = null;
        }
         return new IfStatement(condition, ifStatement, elseStatement);
    }

	private Statement whileStatement(){
                consume(Tokens.T_LPAREN);
		final Expression condition = expression();
                consume(Tokens.T_RPAREN);
		final Statement statement = block();
		return new WhileStatement(condition, statement);
	}
	private Statement doWhileStatement() {
		final Statement statement = block();
		consume(Tokens.T_WHILE);
		final Expression condition = expression();
		return new DoWhileStatement(condition, statement);
	}

	private Statement forStatement(){
		final Statement initialization = assignmentStatement();
		consume(Tokens.T_SEMICOLON);
		final Expression termination = expression();
		consume(Tokens.T_SEMICOLON);
		final Statement increment = assignmentStatement();
		final Statement statement = statementOrBlock();
		return new ForStatement(initialization, termination, increment, statement);
	}

    private FunctionDefinedStatement functionDefine() {
        final String name = consume(Tokens.T_WORD).getValue();
        consume(Tokens.T_LPAREN);
        final List<String> argsNames = new ArrayList<>();
        while (!match(Tokens.T_RPAREN)) {
            argsNames.add(consume(Tokens.T_VARIABLE).getValue());
            match(Tokens.T_COMMA);
        }
        final Statement body = statementOrBlock();
        return new FunctionDefinedStatement(name, argsNames, body);
    }

    private FunctionalExpression function() {
        final String name = consume(Tokens.T_WORD).getValue();
        consume(Tokens.T_LPAREN);
        final FunctionalExpression function = new FunctionalExpression(name);
        while (!match(Tokens.T_RPAREN)) {
            function.addArgument(expression());
            match(Tokens.T_COMMA);
        }
        return function;
    }

    private Expression array(){
            final List<Expression> elements = new ArrayList<>();
            consume(Tokens.T_LBRACKET);
            while (!match(Tokens.T_RBRACKET)) {
                elements.add(expression());
                match(Tokens.T_COMMA);
            }
            return new ArrayExpression(elements);
    }

    private ArrayAccessExpression element() {
        final String variable = consume(Tokens.T_VARIABLE).getValue();
        final List<Expression> indices = new ArrayList<>();
        do {
            consume(Tokens.T_LBRACKET);
            indices.add(expression());
            consume(Tokens.T_RBRACKET);
        } while(lookMatch(0, Tokens.T_LBRACKET));
        return new ArrayAccessExpression(variable, indices);
    }

	private Expression expression() {
		return ternary();
	}
        
        private Expression ternary() {
            Expression result = logicalOr();

            if (match(Tokens.T_QUESTION)) {
                final Expression trueExpr = expression();
                consume(Tokens.T_COLON);
                final Expression falseExpr = expression();
                return new TernaryExpression(result, trueExpr, falseExpr);
            }
            return result;
        }

	private Expression logicalOr() {
		Expression result = logicalAnd();

		while (true) {
			if (match(Tokens.T_BARBAR)) {
				result = new ConditionalExpression(ConditionalExpression.Operator.OR, result, logicalAnd());
				continue;
			}
			break;
		}

		return result;
	}

        private Expression logicalAnd() {
            Expression result = equality();

            while (true) {
                if (match(Tokens.T_AMPAMP)) {
                    result = new ConditionalExpression(ConditionalExpression.Operator.AND, result, equality());
                    continue;
                }
                break;
            }

            return result;
        }

        private Expression equality() {
            Expression result = conditional();

            if (match(Tokens.T_EQEQ)) {
                return new ConditionalExpression(ConditionalExpression.Operator.EQUALS, result, conditional());
            }
            if (match(Tokens.T_EXCLEQ)) {
                return new ConditionalExpression(ConditionalExpression.Operator.NOT_EQUALS, result, conditional());
            }

            return result;
        }

        private Expression conditional() {
            Expression result = additive();

            while (true) {
                if (match(Tokens.T_LT)) {
                    result = new ConditionalExpression(ConditionalExpression.Operator.LT, result, additive());
                    continue;
                }
                if (match(Tokens.T_LTEQ)) {
                    result = new ConditionalExpression(ConditionalExpression.Operator.LTEQ, result, additive());
                    continue;
                }
                if (match(Tokens.T_GT)) {
                    result = new ConditionalExpression(ConditionalExpression.Operator.GT, result, additive());
                    continue;
                }
                if (match(Tokens.T_GTEQ)) {
                    result = new ConditionalExpression(ConditionalExpression.Operator.GTEQ, result, additive());
                    continue;
                }
                break;
            }

            return result;
        }

	private Expression additive(){
		Expression result = multiplicative();

		while(true){
			if(match(Tokens.T_PLUS)){
				result = new BinaryExpression(BinaryExpression.Operator.PLUS, result, multiplicative());
				continue;
			}
			if(match(Tokens.T_MINUS)){
				result = new BinaryExpression(BinaryExpression.Operator.MINUS, result, multiplicative());
				continue;
			}
			if(match(Tokens.T_CONCAT)){
				result = new BinaryExpression(BinaryExpression.Operator.DOT, result, multiplicative());
				continue;
			}
			break;
		}

		return result;
	}

	private Expression multiplicative(){
		Expression result = unary();

		while(true){
			if(match(Tokens.T_MULTIPLY)){
				result = new BinaryExpression(BinaryExpression.Operator.MULTIPLY, result, unary());
				continue;
			}
			if(match(Tokens.T_DIVIDE)){
				result = new BinaryExpression(BinaryExpression.Operator.DIVIDE, result, unary());
				continue;
			}
			break;
		}

		return result;
	}

	private Expression unary(){
		if(match(Tokens.T_MINUS)){
			return new UnaryExpression(UnaryExpression.Operator.MINUS, primary());
		}
		if(match(Tokens.T_PLUS)){
			return primary();
		}
		return primary();
	}

	private Expression primary(){
		final Token current = get(0);
		if(match(Tokens.T_INTEGER)){
			return new ValueExpression(Integer.parseInt(current.getValue()));
		}
                if (get(0).getType() == Tokens.T_WORD && get(1).getType() == Tokens.T_LPAREN) {
                    return function();
                }
                if (get(0).getType() == Tokens.T_VARIABLE && get(1).getType() == Tokens.T_LBRACKET) {
                    return element();
                }
                if (get(0).getType() == Tokens.T_LBRACKET) {
                    return array();
                }
		if(match(Tokens.T_WORD)){
			return new ConstantExpression(current.getValue());
		}
		if(match(Tokens.T_STRING)){
			return new ValueExpression(current.getValue());
		}
		if(match(Tokens.T_VARIABLE)){
			return new VariableExpression(current.getValue());
		}
		if(match(Tokens.T_LPAREN)){
			Expression result = expression();
			match(Tokens.T_RPAREN);
			return result;
		}
		throw new ParserException("Unknown expression "+current.getType(), get(0));
	}

	private Token consume(Tokens type){
		final Token current = get(0);
		if (type!= current.getType()) throw new ParserException("Excepted '"+type+"' on token "+current, current);
		pos++;
		return current;
	}

	private boolean match(Tokens type){
		final Token current = get(0);
		if (type!= current.getType()) return false;
		pos++;
		return true;
	}

    private boolean lookMatch(int pos, Tokens type) {
        return get(pos).getType() == type;
    }

	public Token get(int relativePosition){
		final int position = pos + relativePosition;
		if(position >= size) return EOF;
		return tokens.get(position);
	}

}