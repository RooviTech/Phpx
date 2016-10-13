package org.roovitechologies.phpx.launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.roovitechologies.phpx.Lexer;
import org.roovitechologies.phpx.Parser;
import org.roovitechologies.phpx.Token;
import org.roovitechologies.phpx.ast.Statement;
import org.roovitechologies.phpx.visitors.FunctionAdder;

public class Launcher {
    
	public static void main(String[] args) throws IOException {
                evaluateFile("sample.php");
	}
        
        public static void evaluateFile(String file) throws IOException {
                file = new File(file).getAbsolutePath();
            
                String input = new String(Files.readAllBytes(Paths.get(file)));
                
		final List<Token> tokens = new Lexer(input).tokenize();
		for(Token token : tokens){
			System.out.println(token);
		}
		
                final Statement program = new Parser(tokens, file).parse();
                program.accept(new FunctionAdder());
                program.execute();
        }
	
}