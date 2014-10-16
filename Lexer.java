/* The following code was generated by JFlex 1.6.0 */

/* 
  Reference implementation of a Lexical Analyser for Decaf
  Compiler Course, T-603-THYDE - Fall 2014
  Author: Fridjon Gudjohnsen & Thorgeir Audunn Karlsson
*/




/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file <tt>/siddi/Downloads/Skóladownload/Compilers/LexAnalMySchool/lexical.flex</tt>
 */
class Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\2\47\2\0\1\47\22\0\1\47\1\10\3\0\1\15\1\16"+
    "\1\0\1\43\1\44\1\13\1\5\1\46\1\6\1\3\1\14\12\2"+
    "\1\0\1\45\1\11\1\7\1\11\2\0\4\1\1\4\25\1\1\41"+
    "\1\0\1\42\1\0\1\1\1\0\1\21\1\35\1\23\1\27\1\31"+
    "\1\30\2\1\1\22\1\1\1\36\1\24\1\1\1\34\1\26\2\1"+
    "\1\32\1\17\1\20\1\33\1\25\4\1\1\37\1\12\1\40\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff92\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\2\4\1\5\1\6\1\7"+
    "\1\1\2\10\1\1\10\2\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\2\0\1\22\1\7"+
    "\1\4\1\0\1\2\1\23\10\2\2\24\2\0\1\2"+
    "\1\25\3\2\1\26\4\2\1\27\3\2\1\30\1\31"+
    "\1\2\1\32\2\2\1\33\2\2\1\34\1\35\1\2"+
    "\1\36\1\2\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[79];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\170\0\240\0\310\0\360\0\360"+
    "\0\360\0\u0118\0\50\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0"+
    "\0\u0208\0\u0230\0\u0258\0\u0280\0\u02a8\0\50\0\50\0\50"+
    "\0\50\0\50\0\50\0\50\0\50\0\50\0\u02d0\0\u02f8"+
    "\0\50\0\50\0\50\0\u0320\0\u0348\0\120\0\u0370\0\u0398"+
    "\0\u03c0\0\u03e8\0\u0410\0\u0438\0\u0460\0\u0488\0\u04b0\0\u04d8"+
    "\0\u04d8\0\u0500\0\u0528\0\120\0\u0550\0\u0578\0\u05a0\0\120"+
    "\0\u05c8\0\u05f0\0\u0618\0\u0640\0\50\0\u0668\0\u0690\0\u06b8"+
    "\0\120\0\120\0\u06e0\0\120\0\u0708\0\u0730\0\120\0\u0758"+
    "\0\u0780\0\120\0\120\0\u07a8\0\120\0\u07d0\0\120";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[79];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\2\1\3\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\13\1\15\1\16"+
    "\2\3\1\17\1\20\1\3\1\21\2\3\1\22\1\23"+
    "\1\24\2\3\1\25\1\3\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\51\0\2\3\1\0"+
    "\1\3\12\0\20\3\13\0\1\4\1\37\1\40\50\0"+
    "\1\41\50\0\1\41\50\0\1\42\52\0\1\43\50\0"+
    "\1\44\52\0\1\13\32\0\2\3\1\0\1\3\12\0"+
    "\1\3\1\45\16\3\12\0\2\3\1\0\1\3\12\0"+
    "\11\3\1\46\3\3\1\47\2\3\12\0\2\3\1\0"+
    "\1\3\12\0\5\3\1\50\1\3\1\51\10\3\12\0"+
    "\2\3\1\0\1\3\12\0\7\3\1\52\10\3\12\0"+
    "\2\3\1\0\1\3\12\0\7\3\1\53\10\3\12\0"+
    "\2\3\1\0\1\3\12\0\5\3\1\54\12\3\12\0"+
    "\2\3\1\0\1\3\12\0\12\3\1\55\5\3\12\0"+
    "\2\3\1\0\1\3\12\0\13\3\1\56\4\3\13\0"+
    "\1\57\47\0\1\60\2\0\2\61\41\0\13\44\1\62"+
    "\34\44\1\0\2\3\1\0\1\3\12\0\2\3\1\63"+
    "\15\3\12\0\2\3\1\0\1\3\12\0\1\3\1\64"+
    "\16\3\12\0\2\3\1\0\1\3\12\0\2\3\1\65"+
    "\15\3\12\0\2\3\1\0\1\3\12\0\15\3\1\66"+
    "\2\3\12\0\2\3\1\0\1\3\12\0\3\3\1\67"+
    "\14\3\12\0\2\3\1\0\1\3\12\0\13\3\1\70"+
    "\4\3\12\0\2\3\1\0\1\3\12\0\1\71\17\3"+
    "\12\0\2\3\1\0\1\3\12\0\1\3\1\72\1\73"+
    "\15\3\12\0\2\3\1\0\1\3\12\0\12\3\1\74"+
    "\5\3\13\0\1\57\1\0\1\40\45\0\1\60\45\0"+
    "\13\44\1\62\1\75\33\44\1\0\2\3\1\0\1\3"+
    "\12\0\1\3\1\76\16\3\12\0\2\3\1\0\1\3"+
    "\12\0\1\77\17\3\12\0\2\3\1\0\1\3\12\0"+
    "\1\3\1\100\16\3\12\0\2\3\1\0\1\3\12\0"+
    "\10\3\1\101\7\3\12\0\2\3\1\0\1\3\12\0"+
    "\12\3\1\102\5\3\12\0\2\3\1\0\1\3\12\0"+
    "\14\3\1\103\3\3\12\0\2\3\1\0\1\3\12\0"+
    "\5\3\1\104\12\3\12\0\2\3\1\0\1\3\12\0"+
    "\2\3\1\105\15\3\12\0\2\3\1\0\1\3\12\0"+
    "\3\3\1\106\14\3\12\0\2\3\1\0\1\3\12\0"+
    "\1\107\17\3\12\0\2\3\1\0\1\3\12\0\3\3"+
    "\1\110\14\3\12\0\2\3\1\0\1\3\12\0\13\3"+
    "\1\111\4\3\12\0\2\3\1\0\1\3\12\0\17\3"+
    "\1\112\12\0\2\3\1\0\1\3\12\0\4\3\1\113"+
    "\13\3\12\0\2\3\1\0\1\3\12\0\15\3\1\114"+
    "\2\3\12\0\2\3\1\0\1\3\12\0\15\3\1\115"+
    "\2\3\12\0\2\3\1\0\1\3\12\0\14\3\1\116"+
    "\3\3\12\0\2\3\1\0\1\3\12\0\12\3\1\117"+
    "\5\3\11\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2040];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\10\1\1\11\12\1\11\11\2\0\3\11"+
    "\1\0\14\1\2\0\12\1\1\11\22\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[79];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  private SymbolTableEntry createOrInsertSymTabEntry() {
    SymbolTableEntry symTabEntry = SymbolTable.lookup(yytext());
    if (symTabEntry == null)
      symTabEntry = SymbolTable.insert(yytext());
    return symTabEntry;
  } 


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 152) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;           
    int totalRead = 0;
    while (totalRead < requested) {
      int numRead = zzReader.read(zzBuffer, zzEndRead + totalRead, requested - totalRead);
      if (numRead == -1) {
        break;
      }
      totalRead += numRead;
    }

    if (totalRead > 0) {
      zzEndRead += totalRead;
      if (totalRead == requested) { /* possibly more input available */
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      return false;
    }

    // totalRead = 0: End of stream
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { return new Token(TokenCode.ERR_ILL_CHAR, yyline, yycolumn);
          }
        case 32: break;
        case 2: 
          { if (yytext().length() <= 32)
						return Token.createId(createOrInsertSymTabEntry());
					else 
						return Token.createRaw(TokenCode.ERR_LONG_ID, yyline, yycolumn);
          }
        case 33: break;
        case 3: 
          { return Token.createInt(createOrInsertSymTabEntry());
          }
        case 34: break;
        case 4: 
          { return Token.createAddOp(yytext(), yyline, yycolumn);
          }
        case 35: break;
        case 5: 
          { return Token.createOp(TokenCode.ASSIGNOP, OpType.ASSIGN, yyline, yycolumn);
          }
        case 36: break;
        case 6: 
          { return Token.createRaw(TokenCode.NOT, yyline, yycolumn);
          }
        case 37: break;
        case 7: 
          { return Token.createRelOp(yytext(), yyline, yycolumn);
          }
        case 38: break;
        case 8: 
          { return Token.createMulOp(yytext(), yyline, yycolumn);
          }
        case 39: break;
        case 9: 
          { return Token.createRaw(TokenCode.LBRACE, yyline, yycolumn);
          }
        case 40: break;
        case 10: 
          { return Token.createRaw(TokenCode.RBRACE, yyline, yycolumn);
          }
        case 41: break;
        case 11: 
          { return Token.createRaw(TokenCode.LBRACKET, yyline, yycolumn);
          }
        case 42: break;
        case 12: 
          { return Token.createRaw(TokenCode.RBRACKET, yyline, yycolumn);
          }
        case 43: break;
        case 13: 
          { return Token.createRaw(TokenCode.LPAREN, yyline, yycolumn);
          }
        case 44: break;
        case 14: 
          { return Token.createRaw(TokenCode.RPAREN, yyline, yycolumn);
          }
        case 45: break;
        case 15: 
          { return Token.createRaw(TokenCode.SEMICOLON, yyline, yycolumn);
          }
        case 46: break;
        case 16: 
          { return Token.createRaw(TokenCode.COMMA, yyline, yycolumn);
          }
        case 47: break;
        case 17: 
          { /* Ignore whitespace */
          }
        case 48: break;
        case 18: 
          { OpType op = yytext().equals("++") ? OpType.INC : OpType.DEC;
                  return Token.createOp(TokenCode.INCDECOP, op, yyline, yycolumn);
          }
        case 49: break;
        case 19: 
          { return Token.createRaw(TokenCode.IF, yyline, yycolumn);
          }
        case 50: break;
        case 20: 
          { return Token.createReal(createOrInsertSymTabEntry());
          }
        case 51: break;
        case 21: 
          { return Token.createRaw(TokenCode.INT, yyline, yycolumn);
          }
        case 52: break;
        case 22: 
          { return Token.createRaw(TokenCode.FOR, yyline, yycolumn);
          }
        case 53: break;
        case 23: 
          { /* Ignore comments   */
          }
        case 54: break;
        case 24: 
          { return Token.createRaw(TokenCode.VOID, yyline, yycolumn);
          }
        case 55: break;
        case 25: 
          { return Token.createRaw(TokenCode.ELSE, yyline, yycolumn);
          }
        case 56: break;
        case 26: 
          { return Token.createRaw(TokenCode.REAL, yyline, yycolumn);
          }
        case 57: break;
        case 27: 
          { return Token.createRaw(TokenCode.CLASS, yyline, yycolumn);
          }
        case 58: break;
        case 28: 
          { return Token.createRaw(TokenCode.BREAK, yyline, yycolumn);
          }
        case 59: break;
        case 29: 
          { return Token.createRaw(TokenCode.STATIC, yyline, yycolumn);
          }
        case 60: break;
        case 30: 
          { return Token.createRaw(TokenCode.RETURN, yyline, yycolumn);
          }
        case 61: break;
        case 31: 
          { return Token.createRaw(TokenCode.CONTINUE, yyline, yycolumn);
          }
        case 62: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              {   return Token.createRaw(TokenCode.EOF, yyline, yycolumn);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
