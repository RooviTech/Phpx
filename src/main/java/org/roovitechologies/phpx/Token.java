package org.roovitechologies.phpx;

public final class Token {

    private Tokens type;
    private String value;
    private String token;
    private final int row, col;

    public Token(Tokens type, String value, int row, int col) {
        this.token = type.getName();
        this.type = type;
        this.value = value;
        this.row = row;
        this.col = col;
    }

    public Tokens getType() {
        return type;
    }

    public void setType(Tokens type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public String position() {
        return "[" + row + ":" + col + "]";
    }

    @Override
    public String toString() {
        //return type + " " + value;
        if(value.equals("")) return token+"";
        return token+" "+value;
    }
}
