import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by siddi on 16/10/14.
 */
public class Parser {

    private LinkedList<TokenCode> tcs;
    public int totalPops = 0;

    public Stack<String> getMethodCallStack() {
        return methodCallStack;
    }

    private Stack<String> methodCallStack = new Stack<String>();

    public Parser(LinkedList<TokenCode> tcs)
    {
        methodCallStack.push("Parser");
        this.tcs = tcs;
        parsingDone();
    }

    public void parse()
    {
        methodCallStack.push("parse()");
        program();
        parsingDone();
    }

    private void program()
    {
        methodCallStack.push("program()");
        if(tcs.peek() == TokenCode.CLASS)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.IDENTIFIER)
            {
                popTokenCodeStack();
                if(tcs.peek() == TokenCode.LBRACE)
                {
                    popTokenCodeStack();
                    variable_declarations();
                    method_declarations();
                    if(tcs.peek() == TokenCode.RBRACE)
                    {
                        popTokenCodeStack();
                        parsingDone();
                        return;
                    }
                    //error
                }
                //error
            }
            //error
        }
        //error
        parsingDone();
    }

    private void variable_declarations()
    {
        methodCallStack.push("variable_declarations()");
        if(tcs.peek() == TokenCode.INT || tcs.peek() == TokenCode.REAL)
        {
            type();
            variable_list();
            if (tcs.peek() == TokenCode.SEMICOLON) {
                variable_declarations();
            }
            // error
        }
        parsingDone();
        return;
    }

    private void type()
    {
        methodCallStack.push("type()");
        if(tcs.peek() == TokenCode.INT)
        {
            popTokenCodeStack();
            parsingDone();
            return;
        }
        if(tcs.peek() == TokenCode.REAL)
        {
            popTokenCodeStack();
            parsingDone();
            return;
        }
        // error
        parsingDone();
    }

    private void variable_list()
    {
        methodCallStack.push("variable_list()");
        variable();
        temp_var_list();
        parsingDone();
    }

    private void temp_var_list()
    {
        methodCallStack.push("temp_var_list()");
        if(tcs.peek() == TokenCode.COMMA)
        {
            popTokenCodeStack();
            variable();
        }
        parsingDone();
        return;
    }

    private void variable()
    {
        methodCallStack.push("variable()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            popTokenCodeStack();
            variable_left_factor();
        }
        // error
        parsingDone();

    }

    private void variable_left_factor()
    {
        methodCallStack.push("variable_left_factor()");
        if(tcs.peek() == TokenCode.LBRACKET)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.NUMBER)
            {
                popTokenCodeStack();
                if(tcs.peek() == TokenCode.RBRACKET)
                {
                    popTokenCodeStack();
                    parsingDone();
                    return;
                }
                // error
            }
            // error
        }
        parsingDone();
        return;
    }

    private void method_declarations()
    {
        methodCallStack.push("method_declarations()");
        method_declaration();
        more_method_declarations();
        parsingDone();
    }

    private void more_method_declarations()
    {
        methodCallStack.push("more_method_declarations()");
        if(tcs.peek() == TokenCode.STATIC)
        {
            method_declaration();
            more_method_declarations();
        }
        parsingDone();
        return;
    }

    private void method_declaration()
    {
        methodCallStack.push("method_declaration()");
        if(tcs.peek() == TokenCode.STATIC)
        {
            popTokenCodeStack();
            method_return_type();
            if(tcs.peek() == TokenCode.IDENTIFIER)
            {
                popTokenCodeStack();
                if(tcs.peek() == TokenCode.LPAREN)
                {
                    popTokenCodeStack();
                    parameters();
                    if(tcs.peek() == TokenCode.RPAREN)
                    {
                        if(tcs.peek() == TokenCode.LBRACE)
                        {
                            variable_declarations();
                            statement_list();
                            if(tcs.peek() == TokenCode.RBRACE)
                                parsingDone();
                                return;
                            // error
                        }
                        // error
                    }
                    // error
                }
                // error
            }
            // error
        }
        // error
        parsingDone();
    }

    private void method_return_type()
    {
        methodCallStack.push("method_return_type()");
        if(tcs.peek() == TokenCode.VOID)
        {
            popTokenCodeStack();
            parsingDone();
            return;
        }
        else
            type();
            parsingDone();
    }

    private void parameters()
    {
        methodCallStack.push("parameters()");
        if(tcs.peek() == TokenCode.INT || tcs.peek() == TokenCode.REAL)
        {
            parameter_list();
        }
        parsingDone();
        return;
    }

    private void parameter_list()
    {
        methodCallStack.push("parameter_list()");
        type();
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            temp_parameter_list();
        }
        // error
        parsingDone();
        return;
    }

    private void temp_parameter_list()
    {
        methodCallStack.push("temp_parameter_list()");
        if(tcs.peek() == TokenCode.COMMA)
        {
            type();
            if(tcs.peek() == TokenCode.IDENTIFIER)
            {
                temp_parameter_list();
            }
        }
        parsingDone();
        return;
    }

    private void statement_list()
    {
        methodCallStack.push("statement_list()");
        if( tcs.peek() == TokenCode.IDENTIFIER ||
            tcs.peek() == TokenCode.IF ||
            tcs.peek() == TokenCode.FOR ||
            tcs.peek() == TokenCode.RETURN ||
            tcs.peek() == TokenCode.BREAK ||
            tcs.peek() == TokenCode.CONTINUE ||
            tcs.peek() == TokenCode.LBRACE)
        {
            statement();
            statement_list();
        }
        parsingDone();
        return;
    }

    private void statement()
    {
        methodCallStack.push("statement()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            popTokenCodeStack();
            statement_left_factor();
        }
        if(tcs.peek() == TokenCode.IF)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.LPAREN)
            {
                popTokenCodeStack();
                expression();
                if(tcs.peek() == TokenCode.RPAREN)
                {
                    popTokenCodeStack();
                    statement_block();
                    optional_else();
                }
                // error
            }
            // error
        }
        if(tcs.peek() == TokenCode.FOR)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.LPAREN)
            {
                popTokenCodeStack();
                variable_loc();
                if(tcs.peek() == TokenCode.ASSIGNOP)
                {
                    popTokenCodeStack();
                    expression();
                    if(tcs.peek() == TokenCode.SEMICOLON)
                    {
                        popTokenCodeStack();
                        expression();
                        if(tcs.peek() == TokenCode.SEMICOLON)
                        {
                            popTokenCodeStack();
                            incr_decr_var();
                            if(tcs.peek() == TokenCode.RPAREN)
                            {
                                popTokenCodeStack();
                                statement_block();
                            }
                            // error
                        }
                        // error
                    }
                    // error
                }
                // error
            }
            // error
        }
        if(tcs.peek() == TokenCode.RETURN)
        {
            popTokenCodeStack();
            optional_expression();
        }
        if(tcs.peek() == TokenCode.BREAK)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        if(tcs.peek() == TokenCode.CONTINUE)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        else statement_block();
        parsingDone();
    }

    private void statement_left_factor()
    {
        methodCallStack.push("statement_left_factor()");
        if(tcs.peek() == TokenCode.LPAREN)
        {
            popTokenCodeStack();
            expression_list();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
        }
        else
        {
            variable_loc_left_factor();
            statement_left_left_factor();
        }
        parsingDone();
    }

    private void statement_left_left_factor()
    {
        methodCallStack.push("statement_left_left_factor()");
        if(tcs.peek() == TokenCode.ASSIGNOP)
        {
            popTokenCodeStack();
            expression();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        if(tcs.peek() == TokenCode.INCDECOP)
        {
            popTokenCodeStack();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        // error
        parsingDone();
    }

    private void optional_expression()
    {
        methodCallStack.push("optional_expression()");

        if(tcs.peek() == TokenCode.ADDOP ||
                tcs.peek() == TokenCode.IDENTIFIER ||
                tcs.peek() == TokenCode.NUMBER ||
                tcs.peek() == TokenCode.NOT ||
                tcs.peek() == TokenCode.LPAREN)
        {
            expression();
        }
        parsingDone();
        return;
    }

    private void statement_block()
    {
        methodCallStack.push("statement_block()");
        if(tcs.peek() == TokenCode.RBRACE)
        {
            popTokenCodeStack();
            statement_block();
            if(tcs.peek() == TokenCode.LBRACE)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        // error
        parsingDone();
    }

    private void incr_decr_var()
    {
        methodCallStack.push("incr_decr_var()");
        variable_loc();
        if(tcs.peek() == TokenCode.INCDECOP)
        {
            popTokenCodeStack();
            parsingDone();
            return;
        }
        // error
        parsingDone();
    }

    private void optional_else()
    {
        methodCallStack.push("optional_else()");
        if(tcs.peek() == TokenCode.ELSE)
        {
            popTokenCodeStack();
            statement_block();
        }
        parsingDone();
        return;
    }

    private void expression_list()
    {
        methodCallStack.push("expression_list()");
        if( tcs.peek() == TokenCode.IDENTIFIER ||
                tcs.peek() == TokenCode.NUMBER ||
                tcs.peek() == TokenCode.NOT ||
                tcs.peek() == TokenCode.LPAREN ||
                tcs.peek() == TokenCode.ADDOP)
        {
            expression();
            more_expressions();
        }
        parsingDone();
        return;
    }

    private void more_expressions()
    {
        methodCallStack.push("more_expressions()");
        if( tcs.peek() == TokenCode.COMMA)
        {
            expression();
            more_expressions();
        }
        parsingDone();
        return;
    }

    private void expression()
    {
        methodCallStack.push("expression()");
        if( tcs.peek() == TokenCode.IDENTIFIER ||
                tcs.peek() == TokenCode.NUMBER ||
                tcs.peek() == TokenCode.NOT ||
                tcs.peek() == TokenCode.LPAREN ||
                tcs.peek() == TokenCode.ADDOP)
        {
            simple_expression();
            ex_left_factor();
        }
        parsingDone();
    }

    private void ex_left_factor()
    {
        methodCallStack.push("ex_left_factor()");
        if(tcs.peek() == TokenCode.RELOP)
        {
            popTokenCodeStack();
            simple_expression();
        }
        parsingDone();
        return;
    }

    private void simple_expression()
    {
        methodCallStack.push("simple_expression()");
        if(tcs.peek() == TokenCode.ADDOP)
        {
            sign();
        }
        term();
        temp_simple_expression();
        parsingDone();

    }

    private void temp_simple_expression()
    {
        methodCallStack.push("temp_simple_expression()");
        if(tcs.peek() == TokenCode.ADDOP)
        {
            popTokenCodeStack();
            term();
            temp_simple_expression();
        }
        parsingDone();
        return;
    }

    private void term()
    {
        methodCallStack.push("term()");
        factor();
        temp_term();
        parsingDone();
    }

    private void temp_term()
    {
        methodCallStack.push("temp_term()");
        if(tcs.peek() == TokenCode.MULOP)
        {
            popTokenCodeStack();
            factor();
            temp_term();
        }
        parsingDone();
        return;
    }

    private void factor()
    {
        methodCallStack.push("factor()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            popTokenCodeStack();
            factor_left_factor();
        }
        if(tcs.peek() == TokenCode.NUMBER)
        {
            popTokenCodeStack();
            parsingDone();
            return;
        }
        if(tcs.peek() == TokenCode.LPAREN)
        {
            popTokenCodeStack();
            expression();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
        }
        if(tcs.peek() == TokenCode.NOT)
        {
            factor();
        }
        parsingDone();
        // error
    }

    private void factor_left_factor()
    {
        methodCallStack.push("factor_left_factor()");
        if(tcs.peek() == TokenCode.LPAREN)
        {
            popTokenCodeStack();
            expression_list();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        variable_loc_left_factor();
        parsingDone();
    }

    private void variable_loc()
    {
        methodCallStack.push("variable_loc()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            popTokenCodeStack();
            variable_loc_left_factor();
        }
        parsingDone();
        // error
    }

    private void variable_loc_left_factor()
    {
        methodCallStack.push("variable_loc_left_factor()");
        if(tcs.peek() == TokenCode.LBRACKET)
        {
            popTokenCodeStack();
            expression();
            if(tcs.peek() == TokenCode.RBRACKET)
            {
                popTokenCodeStack();
                parsingDone();
                return;
            }
            // error
        }
        parsingDone();
        return;
    }

   private void sign()
   {
       methodCallStack.push("sign()");
       if(tcs.peek() == TokenCode.ADDOP)
       {
            popTokenCodeStack();
           parsingDone();
            return;
       }
       // error
       parsingDone();
   }
    
   private void parsingDone()
   {
       methodCallStack.pop();
       if(tcs.peek() == TokenCode.EOF)
       {
           System.out.println();
           System.out.println();
           System.out.println("*** Parsing done. ***");
       }  
   }
    
   private void popTokenCodeStack()
   {
       tcs.pop();
       totalPops++;
   }
   
}
