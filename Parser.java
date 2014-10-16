import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by siddi on 16/10/14.
 */
public class Parser {

    private LinkedList<TokenCode> tcs;

    public Stack<String> getMethodCallStack() {
        return methodCallStack;
    }

    private Stack<String> methodCallStack = new Stack<String>();

    public Parser(LinkedList<TokenCode> tcs)
    {
        methodCallStack.push("Parser");
        this.tcs = tcs;
    }

    public void parse()
    {
        methodCallStack.push("parser()");
        tcs.pop();
        program();
    }

    private void program()
    {
        methodCallStack.push("program()");
        if(tcs.peek() == TokenCode.CLASS)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.IDENTIFIER)
            {
                tcs.pop();
                if(tcs.peek() == TokenCode.LBRACE)
                {
                    tcs.pop();
                    variable_declarations();
                    method_declarations();
                    if(tcs.peek() == TokenCode.RBRACE)
                    {
                        tcs.pop();
                        return;
                    }
                    //error
                }
                //error
            }
            //error
        }
        //error
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
        return;
    }

    private void type()
    {
        methodCallStack.push("type()");
        if(tcs.peek() == TokenCode.INT)
        {
            tcs.pop();
            return;
        }
        if(tcs.peek() == TokenCode.REAL)
        {
            tcs.pop();
            return;
        }
        // error
    }

    private void variable_list()
    {
        methodCallStack.push("variable_list()");
        variable();
        temp_var_list();
    }

    private void temp_var_list()
    {
        methodCallStack.push("temp_var_list()");
        if(tcs.peek() == TokenCode.COMMA)
        {
            tcs.pop();
            variable();
        }
        return;
    }

    private void variable()
    {
        methodCallStack.push("variable()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            tcs.pop();
            variable_left_factor();
        }
        // error

    }

    private void variable_left_factor()
    {
        methodCallStack.push("variable_left_factor()");
        if(tcs.peek() == TokenCode.LBRACKET)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.NUMBER)
            {
                tcs.pop();
                if(tcs.peek() == TokenCode.RBRACKET)
                {
                    tcs.pop();
                    return;
                }
                // error
            }
            // error
        }
        return;
    }

    private void method_declarations()
    {
        methodCallStack.push("method_declarations()");
        method_declaration();
        more_method_declarations();
    }

    private void more_method_declarations()
    {
        methodCallStack.push("more_method_declarations()");
        if(tcs.peek() == TokenCode.STATIC)
        {
            method_declaration();
            more_method_declarations();
        }
        return;
    }

    private void method_declaration()
    {
        methodCallStack.push("method_declaration()");
        if(tcs.peek() == TokenCode.STATIC)
        {
            tcs.pop();
            method_return_type();
            if(tcs.peek() == TokenCode.IDENTIFIER)
            {
                tcs.pop();
                if(tcs.peek() == TokenCode.LPAREN)
                {
                    tcs.pop();
                    parameters();
                    if(tcs.peek() == TokenCode.RPAREN)
                    {
                        if(tcs.peek() == TokenCode.LBRACE)
                        {
                            variable_declarations();
                            statement_list();
                            if(tcs.peek() == TokenCode.RBRACE)
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
    }

    private void method_return_type()
    {
        methodCallStack.push("method_return_type()");
        if(tcs.peek() == TokenCode.VOID)
        {
            tcs.pop();
            return;
        }
        else
            type();
    }

    private void parameters()
    {
        methodCallStack.push("parameters()");
        if(tcs.peek() == TokenCode.INT || tcs.peek() == TokenCode.REAL)
        {
            parameter_list();
        }
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
        return;
    }

    private void statement()
    {
        methodCallStack.push("statement()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            tcs.pop();
            statement_left_factor();
        }
        if(tcs.peek() == TokenCode.IF)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.LPAREN)
            {
                tcs.pop();
                expression();
                if(tcs.peek() == TokenCode.RPAREN)
                {
                    tcs.pop();
                    statement_block();
                    optional_else();
                }
                // error
            }
            // error
        }
        if(tcs.peek() == TokenCode.FOR)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.LPAREN)
            {
                tcs.pop();
                variable_loc();
                if(tcs.peek() == TokenCode.ASSIGNOP)
                {
                    tcs.pop();
                    expression();
                    if(tcs.peek() == TokenCode.SEMICOLON)
                    {
                        tcs.pop();
                        expression();
                        if(tcs.peek() == TokenCode.SEMICOLON)
                        {
                            tcs.pop();
                            incr_decr_var();
                            if(tcs.peek() == TokenCode.RPAREN)
                            {
                                tcs.pop();
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
            tcs.pop();
            optional_expression();
        }
        if(tcs.peek() == TokenCode.BREAK)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                tcs.pop();
                return;
            }
            // error
        }
        if(tcs.peek() == TokenCode.CONTINUE)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                tcs.pop();
                return;
            }
            // error
        }
        else statement_block();
    }

    private void statement_left_factor()
    {
        methodCallStack.push("statement_left_factor()");
        if(tcs.peek() == TokenCode.LPAREN)
        {
            tcs.pop();
            expression_list();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                tcs.pop();
                return;
            }
        }
        else
        {
            variable_loc_left_factor();
            statement_left_left_factor();
        }
    }

    private void statement_left_left_factor()
    {
        methodCallStack.push("statement_left_left_factor()");
        if(tcs.peek() == TokenCode.ASSIGNOP)
        {
            tcs.pop();
            expression();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                tcs.pop();
                return;
            }
            // error
        }
        if(tcs.peek() == TokenCode.INCDECOP)
        {
            tcs.pop();
            if(tcs.peek() == TokenCode.SEMICOLON)
            {
                tcs.pop();
                return;
            }
            // error
        }
        // error
    }

    private void optional_expression()
    {
        methodCallStack.push("optional_expression()");
        if(tcs.peek() == TokenCode.ADDOP ||
                tcs.peek() == TokenCode.IDENTIFIER ||
                tcs.peek() == TokenCode.NUMBER ||
                tcs.peek() == TokenCode.LPAREN ||
                tcs.peek() == TokenCode.NOT
                )
        {
            expression();
        }
        return;
    }

    private void statement_block()
    {
        methodCallStack.push("statement_block()");
        if(tcs.peek() == TokenCode.RBRACE)
        {
            tcs.pop();
            statement_block();
            if(tcs.peek() == TokenCode.LBRACE)
            {
                tcs.pop();
                return;
            }
            // error
        }
        // error
    }

    private void incr_decr_var()
    {
        methodCallStack.push("incr_decr_var()");
        variable_loc();
        if(tcs.peek() == TokenCode.INCDECOP)
        {
            tcs.pop();
            return;
        }
        // error
    }

    private void optional_else()
    {
        methodCallStack.push("optional_else()");
        if(tcs.peek() == TokenCode.ELSE)
        {
            tcs.pop();
            statement_block();
        }
        return;
    }

    private void expression_list()
    {
        methodCallStack.push("expression_list()");
        expression();
        more_expressions();
    }

    private void more_expressions()
    {
        methodCallStack.push("more_expressions()");
        if( tcs.peek() == TokenCode.IDENTIFIER ||
            tcs.peek() == TokenCode.NUMBER ||
            tcs.peek() == TokenCode.NOT ||
            tcs.peek() == TokenCode.LPAREN)
        {
            expression();
            more_expressions();
        }
        return;
    }

    private void expression()
    {
        methodCallStack.push("expression()");
        if(tcs.peek() == TokenCode.COMMA)
        {
            tcs.pop();
            expression();
            more_expressions();
        }
        return;
    }

    private void ex_left_factor()
    {
        methodCallStack.push("ex_left_factor()");
        if(tcs.peek() == TokenCode.RELOP)
        {
            tcs.pop();
            simple_expression();
        }
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

    }

    private void temp_simple_expression()
    {
        methodCallStack.push("temp_simple_expression()");
        if(tcs.peek() == TokenCode.ADDOP)
        {
            tcs.pop();
            term();
            temp_simple_expression();
        }
        return;
    }

    private void term()
    {
        methodCallStack.push("term()");
        factor();
        temp_term();
    }

    private void temp_term()
    {
        methodCallStack.push("temp_term()");
        if(tcs.peek() == TokenCode.MULOP)
        {
            tcs.pop();
            factor();
            temp_term();
        }
        return;
    }

    private void factor()
    {
        methodCallStack.push("factor()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            tcs.pop();
            factor_left_factor();
        }
        if(tcs.peek() == TokenCode.NUMBER)
        {
            tcs.pop();
            return;
        }
        if(tcs.peek() == TokenCode.LPAREN)
        {
            tcs.pop();
            expression();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                tcs.pop();
                return;
            }
        }
        if(tcs.peek() == TokenCode.NOT)
        {
            factor();
        }
        // error
    }

    private void factor_left_factor()
    {
        methodCallStack.push("factor_left_factor()");
        if(tcs.peek() == TokenCode.LPAREN)
        {
            tcs.pop();
            expression_list();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                tcs.pop();
                return;
            }
            // error
        }
        variable_loc_left_factor();
    }

    private void variable_loc()
    {
        methodCallStack.push("variable_loc()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            tcs.pop();
            variable_loc_left_factor();
        }
        // error
    }

    private void variable_loc_left_factor()
    {
        methodCallStack.push("variable_loc_left_factor()");
        if(tcs.peek() == TokenCode.LBRACKET)
        {
            tcs.pop();
            expression();
            if(tcs.peek() == TokenCode.RBRACKET)
            {
                tcs.pop();
                return;
            }
            // error
        }
        return;
    }

   private void sign()
   {
       methodCallStack.push("sign()");
       if(tcs.peek() == TokenCode.ADDOP)
       {
            tcs.pop();
            return;
       }
       // error
   }
}
