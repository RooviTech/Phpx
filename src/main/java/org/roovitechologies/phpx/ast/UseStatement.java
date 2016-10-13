package org.roovitechologies.phpx.ast;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.roovitechologies.phpx.extensions.Extension;

public final class UseStatement implements Statement {
	
        private String namespace;
        private static final String PACKAGE = "org.roovitechologies.phpx.extensions.";
	
	public UseStatement(String namespace){
                this.namespace = namespace;
	}
	
	@Override
	public void execute(){try {
                    String namespaceFinal = namespace+"."+namespace.substring(namespace.lastIndexOf(".")+1, namespace.length());
                    String extension = PACKAGE+namespaceFinal;
                    System.out.println(extension);
                    final Extension ext = (Extension) Class.forName(extension).newInstance();
                    ext.Register();
                } catch(ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
                    e.printStackTrace();
                }
        }

        @Override
        public void accept(Visitor visitor) {
        }
	
	@Override
	public String toString(){
                return "";
	}
	
}