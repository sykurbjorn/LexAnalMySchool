import java.io.*;
import java.util.Stack;

public class MyMain {
  public static void main(String [] args) throws IOException {
    Lexer lexer = new Lexer(new FileReader(args[0]));
    Stack<TokenCode> tokenCodeStack = new Stack();
    boolean first = true;
    while(true) {
      if (first)
        first = false;
      else
        System.out.print(" ");
      Token t = lexer.yylex();
      tokenCodeStack.add(t.getTokenCode());
      System.out.print(t.getTokenCode().toString());
      if (t.getTokenCode() == TokenCode.RELOP || 
          t.getTokenCode() == TokenCode.ADDOP ||
          t.getTokenCode() == TokenCode.MULOP ||
          t.getTokenCode() == TokenCode.INCDECOP)
          System.out.print("(" + t.getOpType().toString() + ")");
//        System.out.print("(" + t.getOpType().toString() + ")[L" + t.getLine() + "C" + t.getColumn() + "]");
      else if (t.getTokenCode() == TokenCode.IDENTIFIER || 
               t.getTokenCode() == TokenCode.NUMBER)
          System.out.print("(" + t.getSymTabEntry().getLexeme() + ")");
//        System.out.print("(" + t.getSymTabEntry().getLexeme() + ")[L" + t.getLine() + "C" + t.getColumn() + "]");
      if (t != null && t.getTokenCode() == TokenCode.EOF)
        break;
    }

      System.out.println();
      System.out.println("TokenCode Stack: " + tokenCodeStack.toString());
    System.out.println();
    for(int n=0;n<SymbolTable.size();n++) {
      System.out.print(n);
      System.out.print(" ");
      System.out.println(SymbolTable.getEntry(n).getLexeme());
    }
  }
}