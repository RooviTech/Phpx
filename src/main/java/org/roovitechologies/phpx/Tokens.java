package org.roovitechologies.phpx;

public enum Tokens {
        /* Types block */
	T_INTEGER("T_INTEGER"),
        T_WORD("T_WORD"),
        T_STRING("T_STRING"),
	T_PLUS("+"),
        T_MINUS("-"),
        T_DIVIDE("/"),
        T_MULTIPLY("*"),
        T_LT("<"),
        T_LTEQ("<="),
        T_GT(">"),
        T_GTEQ(">="),
        T_EQ("="),
        T_EQEQ("=="),
        T_EXCL("!="),
        T_EXCLEQ("!=="),
        T_BAR("|"),
        T_BARBAR("||"),
        T_AMP("&"),
        T_AMPAMP("&&"),
        T_BACKSLASH("\\"),
	T_LPAREN("("),
        T_RPAREN(")"),
        T_LBRACE("{"),
        T_RBRACE("}"),
        T_LBRACKET("["),
        T_RBRACKET("]"),
        T_CONCAT("."),
        T_COMMA(","),
        T_QUESTION("?"),
        T_COLON(":"),
        T_VARIABLE("$"), 
        T_IF("if"), 
        T_ELSE("else"),
	T_ECHO("echo"),
        T_WHILE("while"),
        T_FOR("for"),
        T_DO("do"),
        T_BREAK("break"),
        T_CONTINUE("continue"),
        T_FUNCTION("function"),
        T_RETURN("return"),
        T_USE("use"),
        T_INCLUDE("include"),
        T_SEMICOLON(";"),
	T_EOF("");
        
        private final String name;

        private Tokens(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name();
        }
}