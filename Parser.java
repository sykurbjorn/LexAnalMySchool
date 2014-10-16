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
        methodCallStack.pop();
    }

    public void parse()
    {
        methodCallStack.push("parse()");
//        tcs.pop();
        program();
        methodCallStack.pop();
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
                        methodCallStack.pop();
                        return;
                    }
                    //error
                }
                //error
            }
            //error
        }
        //error
        methodCallStack.pop();
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
        methodCallStack.pop();
        return;
    }

    private void type()
    {
        methodCallStack.push("type()");
        if(tcs.peek() == TokenCode.INT)
        {
            tcs.pop();
            methodCallStack.pop();
            return;
        }
        if(tcs.peek() == TokenCode.REAL)
        {
            tcs.pop();
            methodCallStack.pop();
            return;
        }
        // error
        methodCallStack.pop();
    }

    private void variable_list()
    {
        methodCallStack.push("variable_list()");
        variable();
        temp_var_list();
        methodCallStack.pop();
    }

    private void temp_var_list()
    {
        methodCallStack.push("temp_var_list()");
        if(tcs.peek() == TokenCode.COMMA)
        {
            tcs.pop();
            variable();
        }
        methodCallStack.pop();
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
        methodCallStack.pop();

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
                    methodCallStack.pop();
                    return;
                }
                // error
            }
            // error
        }
        methodCallStack.pop();
        return;
    }

    private void method_declarations()
    {
        methodCallStack.push("method_declarations()");
        method_declaration();
        more_method_declarations();
        methodCallStack.pop();
    }

    private void more_method_declarations()
    {
        methodCallStack.push("more_method_declarations()");
        if(tcs.peek() == TokenCode.STATIC)
        {
            method_declaration();
            more_method_declarations();
        }
        methodCallStack.pop();
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
                                methodCallStack.pop();
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
        methodCallStack.pop();
    }

    private void method_return_type()
    {
        methodCallStack.push("method_return_type()");
        if(tcs.peek() == TokenCode.VOID)
        {
            tcs.pop();
            methodCallStack.pop();
            return;
        }
        else
            type();
            methodCallStack.pop();
    }

    private void parameters()
    {
        methodCallStack.push("parameters()");
        if(tcs.peek() == TokenCode.INT || tcs.peek() == TokenCode.REAL)
        {
            parameter_list();
        }
        methodCallStack.pop();
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
        methodCallStack.pop();
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
        methodCallStack.pop();
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
        methodCallStack.pop();
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
                methodCallStack.pop();
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
                methodCallStack.pop();
                return;
            }
            // error
        }
        else statement_block();
        methodCallStack.pop();
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
                methodCallStack.pop();
                return;
            }
        }
        else
        {
            variable_loc_left_factor();
            statement_left_left_factor();
        }
        methodCallStack.pop();
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
                methodCallStack.pop();
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
                methodCallStack.pop();
                return;
            }
            // error
        }
        // error
        methodCallStack.pop();
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
        methodCallStack.pop();
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
                methodCallStack.pop();
                return;
            }
            // error
        }
        // error
        methodCallStack.pop();
    }

    private void incr_decr_var()
    {
        methodCallStack.push("incr_decr_var()");
        variable_loc();
        if(tcs.peek() == TokenCode.INCDECOP)
        {
            tcs.pop();
            methodCallStack.pop();
            return;
        }
        // error
        methodCallStack.pop();
    }

    private void optional_else()
    {
        methodCallStack.push("optional_else()");
        if(tcs.peek() == TokenCode.ELSE)
        {
            tcs.pop();
            statement_block();
        }
        methodCallStack.pop();
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
        methodCallStack.pop();
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
        methodCallStack.pop();
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
        methodCallStack.pop();
    }

    private void ex_left_factor()
    {
        methodCallStack.push("ex_left_factor()");
        if(tcs.peek() == TokenCode.RELOP)
        {
            tcs.pop();
            simple_expression();
        }
        methodCallStack.pop();
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
        methodCallStack.pop();

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
        methodCallStack.pop();
        return;
    }

    private void term()
    {
        methodCallStack.push("term()");
        factor();
        temp_term();
        methodCallStack.pop();
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
        methodCallStack.pop();
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
            methodCallStack.pop();
            return;
        }
        if(tcs.peek() == TokenCode.LPAREN)
        {
            tcs.pop();
            expression();
            if(tcs.peek() == TokenCode.RPAREN)
            {
                tcs.pop();
                methodCallStack.pop();
                return;
            }
        }
        if(tcs.peek() == TokenCode.NOT)
        {
            factor();
        }
        methodCallStack.pop();
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
                methodCallStack.pop();
                return;
            }
            // error
        }
        variable_loc_left_factor();
        methodCallStack.pop();
    }

    private void variable_loc()
    {
        methodCallStack.push("variable_loc()");
        if(tcs.peek() == TokenCode.IDENTIFIER)
        {
            tcs.pop();
            variable_loc_left_factor();
        }
        methodCallStack.pop();
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
                methodCallStack.pop();
                return;
            }
            // error
        }
        methodCallStack.pop();
        return;
    }

   private void sign()
   {
       methodCallStack.push("sign()");
       if(tcs.peek() == TokenCode.ADDOP)
       {
            tcs.pop();
           methodCallStack.pop();
            return;
       }
       // error
       methodCallStack.pop();
   }
}
