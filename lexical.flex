/* 
  Reference implementation of a Lexical Analyser for Decaf
  Compiler Course, T-603-THYDE - Fall 2014
  Author: Fridjon Gudjohnsen & Thorgeir Audunn Karlsson
*/



%%
%class Lexer
%unicode
%line
%column
%type Token
// %standalone
// %debug

%eofval{
  return Token.createRaw(TokenCode.EOF, yyline, yycolumn);
%eofval}

Letter 			   = [a-z] | [A-Z]
Letter_ 		   = {Letter} | [_]
Digit 			   = [0-9]
Identifier 		   = {Letter_} ({Letter_} | {Digit})*
IntNum 			   = {Digit}+
OptionalFraction   = (\.{IntNum})?
OptionalExponent   = ("E" ( "+" | "-" )? {IntNum})?
RealNum            = {IntNum} {OptionalFraction} {OptionalExponent}

IncDecOp = "++" | "--"
RelOp    = "==" | "!=" | "<" | ">" | "<=" | ">="
AddOp    = "+"  | "-"  | "||"
MulOp    = "*"  | "/"  | "%" | "&&"
AssignOp = "="

// Reserved words
Static   = "static"
Class    = "class"
Void     = "void"
If       = "if"
Else     = "else"
For      = "for"
Return   = "return"
Break    = "break"
Continue = "continue"
Int      = "int"
Real     = "real"

LBrace   = "{"
RBrace   = "}"
LBracket = "["
RBracket = "]"
LParen   = "("
RParen   = ")"

// Other tokens
Semicolon = ";"
Comma     = ","
Not       = "!"

Comment = "/*" ~"*/" 
WS      = [ \t\r\n]

%{
  private SymbolTableEntry createOrInsertSymTabEntry() {
    SymbolTableEntry symTabEntry = SymbolTable.lookup(yytext());
    if (symTabEntry == null)
      symTabEntry = SymbolTable.insert(yytext());
    return symTabEntry;
  } 
%}

%%
{Comment}       { /* Ignore comments   */ }
{WS}            { /* Ignore whitespace */ }

{IncDecOp}      { 
                  OpType op = yytext().equals("++") ? OpType.INC : OpType.DEC;
                  return Token.createOp(TokenCode.INCDECOP, op, yyline, yycolumn);
                }

{RelOp}         { return Token.createRelOp(yytext(), yyline, yycolumn); }

{MulOp}         { return Token.createMulOp(yytext(), yyline, yycolumn); }
{AddOp}         { return Token.createAddOp(yytext(), yyline, yycolumn); }

{AssignOp}      { return Token.createOp(TokenCode.ASSIGNOP, OpType.ASSIGN, yyline, yycolumn); }


{Static}         { return Token.createRaw(TokenCode.STATIC, yyline, yycolumn); }
{Class}         { return Token.createRaw(TokenCode.CLASS, yyline, yycolumn); }
{Void}          { return Token.createRaw(TokenCode.VOID, yyline, yycolumn); }
{If}            { return Token.createRaw(TokenCode.IF, yyline, yycolumn); }
{Else}          { return Token.createRaw(TokenCode.ELSE, yyline, yycolumn); }
{For}           { return Token.createRaw(TokenCode.FOR, yyline, yycolumn); }
{Return}        { return Token.createRaw(TokenCode.RETURN, yyline, yycolumn); }
{Break}         { return Token.createRaw(TokenCode.BREAK, yyline, yycolumn); }
{Continue}      { return Token.createRaw(TokenCode.CONTINUE, yyline, yycolumn); }
{Int}           { return Token.createRaw(TokenCode.INT, yyline, yycolumn); }
{Real}          { return Token.createRaw(TokenCode.REAL, yyline, yycolumn); }

{LBrace}        { return Token.createRaw(TokenCode.LBRACE, yyline, yycolumn); }
{RBrace}        { return Token.createRaw(TokenCode.RBRACE, yyline, yycolumn); }
{LBracket}      { return Token.createRaw(TokenCode.LBRACKET, yyline, yycolumn); }
{RBracket}      { return Token.createRaw(TokenCode.RBRACKET, yyline, yycolumn); }
{LParen}        { return Token.createRaw(TokenCode.LPAREN, yyline, yycolumn); }
{RParen}        { return Token.createRaw(TokenCode.RPAREN, yyline, yycolumn); }

{Semicolon}     { return Token.createRaw(TokenCode.SEMICOLON, yyline, yycolumn); }
{Comma}         { return Token.createRaw(TokenCode.COMMA, yyline, yycolumn); }
{Not}           { return Token.createRaw(TokenCode.NOT, yyline, yycolumn); }

{Identifier}    {
					if (yytext().length() <= 32)
						return Token.createId(createOrInsertSymTabEntry());
					else 
						return Token.createRaw(TokenCode.ERR_LONG_ID, yyline, yycolumn);
                }

{IntNum}        {
                  return Token.createInt(createOrInsertSymTabEntry());
                }

{RealNum}       {
                  return Token.createReal(createOrInsertSymTabEntry());
                }

[^]             { return new Token(TokenCode.ERR_ILL_CHAR, yyline, yycolumn); }
