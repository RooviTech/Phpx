package org.roovitechologies.phpx;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.roovitechologies.phpx.exceptions.LexerException;

public class Lexer {

        private String input;
        private int length;

        private List<Token> tokens;
        private StringBuilder buffer;

        private int pos;
        private int row, col;
	
	private final String OPERATOR_CHARS = "+-*/(){}=<>!&|.[];\\?:";
        
        private final Map<String, Tokens> OPERATORS = new HashMap<>();
        {
            OPERATORS.put("+", Tokens.T_PLUS);
            OPERATORS.put("-", Tokens.T_MINUS);
            OPERATORS.put("*", Tokens.T_MULTIPLY);
            OPERATORS.put("/", Tokens.T_DIVIDE);
            OPERATORS.put("(", Tokens.T_LPAREN);
            OPERATORS.put(")", Tokens.T_RPAREN);
            OPERATORS.put("{", Tokens.T_LBRACE);
            OPERATORS.put("}", Tokens.T_RBRACE);
            OPERATORS.put("=", Tokens.T_EQ);
            OPERATORS.put("<", Tokens.T_LT);
            OPERATORS.put(">", Tokens.T_GT);
            OPERATORS.put("!", Tokens.T_EXCL);
            OPERATORS.put("&", Tokens.T_AMP);
            OPERATORS.put("|", Tokens.T_BAR);
            OPERATORS.put("==", Tokens.T_EQEQ);
            OPERATORS.put("!=", Tokens.T_EXCLEQ);
            OPERATORS.put("<=", Tokens.T_LTEQ);
            OPERATORS.put(">=", Tokens.T_GTEQ);
            OPERATORS.put("&&", Tokens.T_AMPAMP);
            OPERATORS.put("||", Tokens.T_BARBAR);
            OPERATORS.put(".", Tokens.T_CONCAT);
            OPERATORS.put("[", Tokens.T_LBRACKET);
            OPERATORS.put("]", Tokens.T_RBRACKET);
            OPERATORS.put(";", Tokens.T_SEMICOLON);
            OPERATORS.put("\\", Tokens.T_BACKSLASH);
            OPERATORS.put("?", Tokens.T_QUESTION);
            OPERATORS.put(":", Tokens.T_COLON);
        }
	
        public Lexer(String inputStr) {
                
            if(inputStr.lastIndexOf("<?php") == 0){
                inputStr = inputStr.substring(7, inputStr.length());
            }else if(inputStr.lastIndexOf("<?") == 0){
                inputStr = inputStr.substring(4, inputStr.length());
            } else {
                System.exit(0);
            }
            
            //System.out.println(inputStr);
            
            input = inputStr;
            length = input.length();

            tokens = new ArrayList<>();
            buffer = new StringBuilder();
            row = 2;
            col = 1;
        }
	
	public List<Token> tokenize(){
            do {
                final char current = peek(0);
                if (Character.isDigit(current)) tokenizeInteger();
                else if (Character.isLetter(current) && (current != '_')) tokenizeWord();
                else if(current == '"'){
                    next();
                    tokenizeString1();
                }
                else if(current == '\''){
                    next();
                    tokenizeString2();
                }
                else if(current == '$'){
                    next();
                    tokenizeVariable();
                }
                else if (OPERATOR_CHARS.indexOf(current) != -1) {
                    tokenizeOperator();
                } else {
                    // whitespaces
                    next();
                }
            } while (pos < length);
            return tokens;
	}
        
        private void tokenizeString1() {
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		while(true){
			if(current == '\\'){
				current = next();
                                switch(current){
                                    case '"': current = next(); buffer.append('"'); continue;
                                    case 'n': current = next(); buffer.append('\n'); continue;
                                    case 't': current = next(); buffer.append('\t'); continue;
                                }
                                buffer.append('\\');
                                continue;
			}
			if(current == '"') break;
			buffer.append(current);
			current = next();
		}
                next();
                
		final String toString = buffer.toString();
		addToken(Tokens.T_STRING, toString);
        }
        
        private void tokenizeString2() {
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		while(true){
			if(current == '\'') break;
			buffer.append(current);
			current = next();
		}
                next();
                
		final String toString = buffer.toString();
		addToken(Tokens.T_STRING, toString);
        }
	
	private void tokenizeVariable(){
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		while(true){
			if(!Character.isLetterOrDigit(current)){
				break;
			}
			buffer.append(current);
			current = next();
		}
		addToken(Tokens.T_VARIABLE, buffer.toString());
	}
	
	private void tokenizeWord(){
		final StringBuilder builder = new StringBuilder();
		char current = peek(0);
		while(true){
			if(!Character.isLetterOrDigit(current) && (current != '_')){
				break;
			}
			builder.append(current);
			current = next();
		}
                final String word = builder.toString();
                
                switch(word.toLowerCase()) {
                    case "echo": addToken(Tokens.T_ECHO); break;
                    case "if": addToken(Tokens.T_IF); break;
                    case "else": addToken(Tokens.T_ELSE); break; 
                    case "while": addToken(Tokens.T_WHILE); break;
                    case "for": addToken(Tokens.T_FOR); break;
                    case "do": addToken(Tokens.T_DO); break;
                    case "break": addToken(Tokens.T_BREAK); break;
                    case "continue": addToken(Tokens.T_CONTINUE); break;
                    case "function": addToken(Tokens.T_FUNCTION); break;
                    case "return": addToken(Tokens.T_RETURN); break;
                    case "use": addToken(Tokens.T_USE); break;
                    case "include": addToken(Tokens.T_INCLUDE); break;
                    default: 
                        addToken(Tokens.T_WORD, word); 
                        break;
                }
	}
	
	private void tokenizeInteger(){
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		while(Character.isDigit(current)){
			if(current=='.'){
				if(buffer.indexOf(".")!=-1) throw new LexerException("Invalid float integer");
			}else if(!Character.isDigit(current)) {
                                break;
			}
			buffer.append(current);
			current = next();
		}
                
		addToken(Tokens.T_INTEGER, buffer.toString());
	}
	
	private void tokenizeOperator(){
                char current = peek(0);
                if(current == '/'){
                    if(peek(1) == '/'){
                        next();
                        next();
                        tokenizeComment();
                        return;
                    }else if(peek(1) == '*'){
                        next();
                        next();
                        tokenizeMultilineComment();
                        return;
                    }
                }
                final StringBuilder buffer = new StringBuilder();
                while(true){
                    final String text = buffer.toString();
                    if(!OPERATORS.containsKey(text + current) && !text.isEmpty()){
                        addToken(OPERATORS.get(text));
                        return;
                    }
                    buffer.append(current);
                    current = next();
                }
	}
        
        private void tokenizeComment(){
            char current = peek(0);
            while("\r\n\0".indexOf(current) == -1){
                current = next();
            }
        }
        
        private void tokenizeMultilineComment(){
            char current = peek(0);
            while(true){
                if(current == '*' && peek(1) == '/') break;
                if(current == '\0') break;
                current = next();
            }
            next();
            next();
        }
	
        private char next() {
            pos++;
            final char result = peek(0);
            if(result == '\n'){
                row++;
                col = 1;
            } else col++;
            return result;
        }
	
	private char peek(int relativePosition){
		final int position = pos + relativePosition;
		if(position >= length) return '\0'; 
		return input.charAt(position);
	}
	
	private void addToken(Tokens type){
		addToken(type, "");
	}
	
	private void addToken(Tokens type, String value){
		tokens.add(new Token(type, value, row, col));
	}

}