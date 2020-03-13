/*
*    DelianCubeEngine. A simple cube query engine.
*    Copyright (C) 2018  Panos Vassiliadis
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU Affero General Public License as published
*    by the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU Affero General Public License for more details.
*
*    You should have received a copy of the GNU Affero General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*
*/


// $ANTLR 3.4 ./CubeSql.g 2013-07-10 18:57:41

  package parsermgr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * The lexer class for the declaration of dimensions and cubes
 * 
 *  @author D. Gkesoulis
 *  @version 0.0.1
 *  @see CubeSqlParser
 *  @see ParserManager
 *  @since v0.0.0 (the Cincecubes system) 
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class CubeSqlLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int A=4;
    public static final int AND=5;
    public static final int AS=6;
    public static final int AT=7;
    public static final int AVG=8;
    public static final int B=9;
    public static final int BY=10;
    public static final int C=11;
    public static final int CHILDOF=12;
    public static final int COMMA=13;
    public static final int COUNT=14;
    public static final int CREATE=15;
    public static final int CUBE=16;
    public static final int D=17;
    public static final int DIMENSION=18;
    public static final int DIMENSION_TABLE=19;
    public static final int DOT=20;
    public static final int Digit=21;
    public static final int E=22;
    public static final int F=23;
    public static final int FROM=24;
    public static final int G=25;
    public static final int GROUP=26;
    public static final int H=27;
    public static final int HIERARCHY=28;
    public static final int I=29;
    public static final int J=30;
    public static final int K=31;
    public static final int L=32;
    public static final int LBRACE=33;
    public static final int LEVEL=34;
    public static final int LIST=35;
    public static final int Letter=36;
    public static final int M=37;
    public static final int MAX=38;
    public static final int MEASURES=39;
    public static final int MIN=40;
    public static final int N=41;
    public static final int NAME=42;
    public static final int O=43;
    public static final int OF=44;
    public static final int OR=45;
    public static final int P=46;
    public static final int Q=47;
    public static final int QUESTMARK=48;
    public static final int R=49;
    public static final int RBRACE=50;
    public static final int REFERENCES=51;
    public static final int RELATED=52;
    public static final int S=53;
    public static final int SELECT=54;
    public static final int SQL_TABLE=55;
    public static final int SUM=56;
    public static final int T=57;
    public static final int U=58;
    public static final int UNDERSCORE=59;
    public static final int V=60;
    public static final int W=61;
    public static final int WHERE=62;
    public static final int WS=63;
    public static final int X=64;
    public static final int Y=65;
    public static final int Z=66;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public CubeSqlLexer() {} 
    
    public CubeSqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    
    public CubeSqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    
    public String getGrammarFileName() { 
    	return "./CubeSql.g"; 
    }

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:6:7: ( '\"' )
            // ./CubeSql.g:6:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:7:7: ( '(' )
            // ./CubeSql.g:7:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:8:7: ( ')' )
            // ./CubeSql.g:8:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:9:7: ( '<' )
            // ./CubeSql.g:9:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:10:7: ( '<=' )
            // ./CubeSql.g:10:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:11:7: ( '=' )
            // ./CubeSql.g:11:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:12:7: ( '>=' )
            // ./CubeSql.g:12:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:13:7: ( 'LIKE' )
            // ./CubeSql.g:13:9: 'LIKE'
            {
            match("LIKE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:14:7: ( '\\'' )
            // ./CubeSql.g:14:9: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:161:4: ( O R )
            // ./CubeSql.g:161:6: O R
            {
            mO(); 


            mR(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:163:5: ( A N D )
            // ./CubeSql.g:163:7: A N D
            {
            mA(); 


            mN(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "CREATE"
    public final void mCREATE() throws RecognitionException {
        try {
            int _type = CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:165:7: ( C R E A T E )
            // ./CubeSql.g:165:9: C R E A T E
            {
            mC(); 


            mR(); 


            mE(); 


            mA(); 


            mT(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CREATE"

    // $ANTLR start "CUBE"
    public final void mCUBE() throws RecognitionException {
        try {
            int _type = CUBE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:167:6: ( C U B E )
            // ./CubeSql.g:167:9: C U B E
            {
            mC(); 


            mU(); 


            mB(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CUBE"

    // $ANTLR start "RELATED"
    public final void mRELATED() throws RecognitionException {
        try {
            int _type = RELATED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:169:9: ( R E L A T E D )
            // ./CubeSql.g:169:11: R E L A T E D
            {
            mR(); 


            mE(); 


            mL(); 


            mA(); 


            mT(); 


            mE(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RELATED"

    // $ANTLR start "SQL_TABLE"
    public final void mSQL_TABLE() throws RecognitionException {
        try {
            int _type = SQL_TABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:171:10: ( S Q L UNDERSCORE T A B L E )
            // ./CubeSql.g:171:12: S Q L UNDERSCORE T A B L E
            {
            mS(); 


            mQ(); 


            mL(); 


            mUNDERSCORE(); 


            mT(); 


            mA(); 


            mB(); 


            mL(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SQL_TABLE"

    // $ANTLR start "REFERENCES"
    public final void mREFERENCES() throws RecognitionException {
        try {
            int _type = REFERENCES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:173:11: ( R E F E R E N C E S )
            // ./CubeSql.g:173:13: R E F E R E N C E S
            {
            mR(); 


            mE(); 


            mF(); 


            mE(); 


            mR(); 


            mE(); 


            mN(); 


            mC(); 


            mE(); 


            mS(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REFERENCES"

    // $ANTLR start "DIMENSION"
    public final void mDIMENSION() throws RecognitionException {
        try {
            int _type = DIMENSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:175:11: ( D I M E N S I O N )
            // ./CubeSql.g:175:13: D I M E N S I O N
            {
            mD(); 


            mI(); 


            mM(); 


            mE(); 


            mN(); 


            mS(); 


            mI(); 


            mO(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIMENSION"

    // $ANTLR start "DIMENSION_TABLE"
    public final void mDIMENSION_TABLE() throws RecognitionException {
        try {
            int _type = DIMENSION_TABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:177:16: ( D I M E N S I O N UNDERSCORE T A B L E )
            // ./CubeSql.g:177:18: D I M E N S I O N UNDERSCORE T A B L E
            {
            mD(); 


            mI(); 


            mM(); 


            mE(); 


            mN(); 


            mS(); 


            mI(); 


            mO(); 


            mN(); 


            mUNDERSCORE(); 


            mT(); 


            mA(); 


            mB(); 


            mL(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIMENSION_TABLE"

    // $ANTLR start "LIST"
    public final void mLIST() throws RecognitionException {
        try {
            int _type = LIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:179:5: ( L I S T )
            // ./CubeSql.g:179:7: L I S T
            {
            mL(); 


            mI(); 


            mS(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LIST"

    // $ANTLR start "OF"
    public final void mOF() throws RecognitionException {
        try {
            int _type = OF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:181:3: ( O F )
            // ./CubeSql.g:181:5: O F
            {
            mO(); 


            mF(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OF"

    // $ANTLR start "LEVEL"
    public final void mLEVEL() throws RecognitionException {
        try {
            int _type = LEVEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:183:6: ( L E V E L )
            // ./CubeSql.g:183:7: L E V E L
            {
            mL(); 


            mE(); 


            mV(); 


            mE(); 


            mL(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEVEL"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:185:3: ( A S )
            // ./CubeSql.g:185:5: A S
            {
            mA(); 


            mS(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:187:4: ( A T )
            // ./CubeSql.g:187:5: A T
            {
            mA(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "HIERARCHY"
    public final void mHIERARCHY() throws RecognitionException {
        try {
            int _type = HIERARCHY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:189:10: ( H I E R A R C H Y )
            // ./CubeSql.g:189:12: H I E R A R C H Y
            {
            mH(); 


            mI(); 


            mE(); 


            mR(); 


            mA(); 


            mR(); 


            mC(); 


            mH(); 


            mY(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HIERARCHY"

    // $ANTLR start "SELECT"
    public final void mSELECT() throws RecognitionException {
        try {
            int _type = SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:191:8: ( S E L E C T )
            // ./CubeSql.g:191:10: S E L E C T
            {
            mS(); 


            mE(); 


            mL(); 


            mE(); 


            mC(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SELECT"

    // $ANTLR start "AVG"
    public final void mAVG() throws RecognitionException {
        try {
            int _type = AVG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:193:5: ( A V G )
            // ./CubeSql.g:193:7: A V G
            {
            mA(); 


            mV(); 


            mG(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AVG"

    // $ANTLR start "SUM"
    public final void mSUM() throws RecognitionException {
        try {
            int _type = SUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:195:5: ( S U M )
            // ./CubeSql.g:195:7: S U M
            {
            mS(); 


            mU(); 


            mM(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SUM"

    // $ANTLR start "MAX"
    public final void mMAX() throws RecognitionException {
        try {
            int _type = MAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:197:5: ( M A X )
            // ./CubeSql.g:197:7: M A X
            {
            mM(); 


            mA(); 


            mX(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MAX"

    // $ANTLR start "MIN"
    public final void mMIN() throws RecognitionException {
        try {
            int _type = MIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:199:5: ( M I N )
            // ./CubeSql.g:199:7: M I N
            {
            mM(); 


            mI(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MIN"

    // $ANTLR start "COUNT"
    public final void mCOUNT() throws RecognitionException {
        try {
            int _type = COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:201:7: ( C O U N T )
            // ./CubeSql.g:201:9: C O U N T
            {
            mC(); 


            mO(); 


            mU(); 


            mN(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COUNT"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:203:7: ( W H E R E )
            // ./CubeSql.g:203:9: W H E R E
            {
            mW(); 


            mH(); 


            mE(); 


            mR(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHERE"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:205:6: ( F R O M )
            // ./CubeSql.g:205:8: F R O M
            {
            mF(); 


            mR(); 


            mO(); 


            mM(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "GROUP"
    public final void mGROUP() throws RecognitionException {
        try {
            int _type = GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:207:7: ( G R O U P )
            // ./CubeSql.g:207:9: G R O U P
            {
            mG(); 


            mR(); 


            mO(); 


            mU(); 


            mP(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GROUP"

    // $ANTLR start "BY"
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:209:3: ( B Y )
            // ./CubeSql.g:209:5: B Y
            {
            mB(); 


            mY(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BY"

    // $ANTLR start "MEASURES"
    public final void mMEASURES() throws RecognitionException {
        try {
            int _type = MEASURES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:211:10: ( M E A S U R E S )
            // ./CubeSql.g:211:12: M E A S U R E S
            {
            mM(); 


            mE(); 


            mA(); 


            mS(); 


            mU(); 


            mR(); 


            mE(); 


            mS(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MEASURES"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:213:5: ( Letter ( Letter | Digit | '_' | '-' | '~' )* )
            // ./CubeSql.g:213:7: Letter ( Letter | Digit | '_' | '-' | '~' )*
            {
            mLetter(); 


            // ./CubeSql.g:213:14: ( Letter | Digit | '_' | '-' | '~' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')||LA1_0=='~') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ./CubeSql.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='~' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:215:7: ( '{' )
            // ./CubeSql.g:215:8: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:216:7: ( '}' )
            // ./CubeSql.g:216:8: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:217:4: ( '.' )
            // ./CubeSql.g:217:5: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:218:6: ( ',' )
            // ./CubeSql.g:218:7: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "CHILDOF"
    public final void mCHILDOF() throws RecognitionException {
        try {
            int _type = CHILDOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:219:8: ( '>' )
            // ./CubeSql.g:219:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHILDOF"

    // $ANTLR start "QUESTMARK"
    public final void mQUESTMARK() throws RecognitionException {
        try {
            int _type = QUESTMARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:220:10: ( ';' )
            // ./CubeSql.g:220:11: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUESTMARK"

    // $ANTLR start "UNDERSCORE"
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            int _type = UNDERSCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:221:11: ( '_' )
            // ./CubeSql.g:221:12: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNDERSCORE"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // ./CubeSql.g:223:16: ( '0' .. '9' )
            // ./CubeSql.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // ./CubeSql.g:224:17: ( 'a' .. 'z' | 'A' .. 'Z' )
            // ./CubeSql.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // ./CubeSql.g:225:11: ( 'A' | 'a' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // ./CubeSql.g:226:11: ( 'B' | 'b' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // ./CubeSql.g:227:11: ( 'C' | 'c' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // ./CubeSql.g:228:11: ( 'D' | 'd' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // ./CubeSql.g:229:11: ( 'E' | 'e' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // ./CubeSql.g:230:11: ( 'F' | 'f' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // ./CubeSql.g:231:11: ( 'G' | 'g' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // ./CubeSql.g:232:11: ( 'H' | 'h' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // ./CubeSql.g:233:11: ( 'I' | 'i' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // ./CubeSql.g:234:11: ( 'J' | 'j' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // ./CubeSql.g:235:11: ( 'K' | 'k' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // ./CubeSql.g:236:11: ( 'L' | 'l' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // ./CubeSql.g:237:11: ( 'M' | 'm' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // ./CubeSql.g:238:11: ( 'N' | 'n' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // ./CubeSql.g:239:11: ( 'O' | 'o' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // ./CubeSql.g:240:11: ( 'P' | 'p' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // ./CubeSql.g:241:11: ( 'Q' | 'q' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // ./CubeSql.g:242:11: ( 'R' | 'r' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // ./CubeSql.g:243:11: ( 'S' | 's' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // ./CubeSql.g:244:11: ( 'T' | 't' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // ./CubeSql.g:245:11: ( 'U' | 'u' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // ./CubeSql.g:246:11: ( 'V' | 'v' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // ./CubeSql.g:247:11: ( 'W' | 'w' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // ./CubeSql.g:248:11: ( 'X' | 'x' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // ./CubeSql.g:249:11: ( 'Y' | 'y' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // ./CubeSql.g:250:11: ( 'Z' | 'z' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Z"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ./CubeSql.g:252:4: ( ( ( ' ' | '\\t' | '\\f' )+ | ( '\\r\\n' | '\\r' | '\\n' ) ) )
            // ./CubeSql.g:252:6: ( ( ' ' | '\\t' | '\\f' )+ | ( '\\r\\n' | '\\r' | '\\n' ) )
            {
            // ./CubeSql.g:252:6: ( ( ' ' | '\\t' | '\\f' )+ | ( '\\r\\n' | '\\r' | '\\n' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\t'||LA4_0=='\f'||LA4_0==' ') ) {
                alt4=1;
            }
            else if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // ./CubeSql.g:253:5: ( ' ' | '\\t' | '\\f' )+
                    {
                    // ./CubeSql.g:253:5: ( ' ' | '\\t' | '\\f' )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0=='\t'||LA2_0=='\f'||LA2_0==' ') ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ./CubeSql.g:
                    	    {
                    	    if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // ./CubeSql.g:256:5: ( '\\r\\n' | '\\r' | '\\n' )
                    {
                    // ./CubeSql.g:256:5: ( '\\r\\n' | '\\r' | '\\n' )
                    int alt3=3;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='\r') ) {
                        int LA3_1 = input.LA(2);

                        if ( (LA3_1=='\n') ) {
                            alt3=1;
                        }
                        else {
                            alt3=2;
                        }
                    }
                    else if ( (LA3_0=='\n') ) {
                        alt3=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                        throw nvae;

                    }
                    switch (alt3) {
                        case 1 :
                            // ./CubeSql.g:256:7: '\\r\\n'
                            {
                            match("\r\n"); 



                            }
                            break;
                        case 2 :
                            // ./CubeSql.g:257:9: '\\r'
                            {
                            match('\r'); 

                            }
                            break;
                        case 3 :
                            // ./CubeSql.g:258:9: '\\n'
                            {
                            match('\n'); 

                            }
                            break;

                    }


                    }
                    break;

            }


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // ./CubeSql.g:1:8: ( T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | OR | AND | CREATE | CUBE | RELATED | SQL_TABLE | REFERENCES | DIMENSION | DIMENSION_TABLE | LIST | OF | LEVEL | AS | AT | HIERARCHY | SELECT | AVG | SUM | MAX | MIN | COUNT | WHERE | FROM | GROUP | BY | MEASURES | NAME | LBRACE | RBRACE | DOT | COMMA | CHILDOF | QUESTMARK | UNDERSCORE | WS )
        int alt5=44;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // ./CubeSql.g:1:10: T__67
                {
                mT__67(); 


                }
                break;
            case 2 :
                // ./CubeSql.g:1:16: T__68
                {
                mT__68(); 


                }
                break;
            case 3 :
                // ./CubeSql.g:1:22: T__69
                {
                mT__69(); 


                }
                break;
            case 4 :
                // ./CubeSql.g:1:28: T__70
                {
                mT__70(); 


                }
                break;
            case 5 :
                // ./CubeSql.g:1:34: T__71
                {
                mT__71(); 


                }
                break;
            case 6 :
                // ./CubeSql.g:1:40: T__72
                {
                mT__72(); 


                }
                break;
            case 7 :
                // ./CubeSql.g:1:46: T__73
                {
                mT__73(); 


                }
                break;
            case 8 :
                // ./CubeSql.g:1:52: T__74
                {
                mT__74(); 


                }
                break;
            case 9 :
                // ./CubeSql.g:1:58: T__75
                {
                mT__75(); 


                }
                break;
            case 10 :
                // ./CubeSql.g:1:64: OR
                {
                mOR(); 


                }
                break;
            case 11 :
                // ./CubeSql.g:1:67: AND
                {
                mAND(); 


                }
                break;
            case 12 :
                // ./CubeSql.g:1:71: CREATE
                {
                mCREATE(); 


                }
                break;
            case 13 :
                // ./CubeSql.g:1:78: CUBE
                {
                mCUBE(); 


                }
                break;
            case 14 :
                // ./CubeSql.g:1:83: RELATED
                {
                mRELATED(); 


                }
                break;
            case 15 :
                // ./CubeSql.g:1:91: SQL_TABLE
                {
                mSQL_TABLE(); 


                }
                break;
            case 16 :
                // ./CubeSql.g:1:101: REFERENCES
                {
                mREFERENCES(); 


                }
                break;
            case 17 :
                // ./CubeSql.g:1:112: DIMENSION
                {
                mDIMENSION(); 


                }
                break;
            case 18 :
                // ./CubeSql.g:1:122: DIMENSION_TABLE
                {
                mDIMENSION_TABLE(); 


                }
                break;
            case 19 :
                // ./CubeSql.g:1:138: LIST
                {
                mLIST(); 


                }
                break;
            case 20 :
                // ./CubeSql.g:1:143: OF
                {
                mOF(); 


                }
                break;
            case 21 :
                // ./CubeSql.g:1:146: LEVEL
                {
                mLEVEL(); 


                }
                break;
            case 22 :
                // ./CubeSql.g:1:152: AS
                {
                mAS(); 


                }
                break;
            case 23 :
                // ./CubeSql.g:1:155: AT
                {
                mAT(); 


                }
                break;
            case 24 :
                // ./CubeSql.g:1:158: HIERARCHY
                {
                mHIERARCHY(); 


                }
                break;
            case 25 :
                // ./CubeSql.g:1:168: SELECT
                {
                mSELECT(); 


                }
                break;
            case 26 :
                // ./CubeSql.g:1:175: AVG
                {
                mAVG(); 


                }
                break;
            case 27 :
                // ./CubeSql.g:1:179: SUM
                {
                mSUM(); 


                }
                break;
            case 28 :
                // ./CubeSql.g:1:183: MAX
                {
                mMAX(); 


                }
                break;
            case 29 :
                // ./CubeSql.g:1:187: MIN
                {
                mMIN(); 


                }
                break;
            case 30 :
                // ./CubeSql.g:1:191: COUNT
                {
                mCOUNT(); 


                }
                break;
            case 31 :
                // ./CubeSql.g:1:197: WHERE
                {
                mWHERE(); 


                }
                break;
            case 32 :
                // ./CubeSql.g:1:203: FROM
                {
                mFROM(); 


                }
                break;
            case 33 :
                // ./CubeSql.g:1:208: GROUP
                {
                mGROUP(); 


                }
                break;
            case 34 :
                // ./CubeSql.g:1:214: BY
                {
                mBY(); 


                }
                break;
            case 35 :
                // ./CubeSql.g:1:217: MEASURES
                {
                mMEASURES(); 


                }
                break;
            case 36 :
                // ./CubeSql.g:1:226: NAME
                {
                mNAME(); 


                }
                break;
            case 37 :
                // ./CubeSql.g:1:231: LBRACE
                {
                mLBRACE(); 


                }
                break;
            case 38 :
                // ./CubeSql.g:1:238: RBRACE
                {
                mRBRACE(); 


                }
                break;
            case 39 :
                // ./CubeSql.g:1:245: DOT
                {
                mDOT(); 


                }
                break;
            case 40 :
                // ./CubeSql.g:1:249: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 41 :
                // ./CubeSql.g:1:255: CHILDOF
                {
                mCHILDOF(); 


                }
                break;
            case 42 :
                // ./CubeSql.g:1:263: QUESTMARK
                {
                mQUESTMARK(); 


                }
                break;
            case 43 :
                // ./CubeSql.g:1:273: UNDERSCORE
                {
                mUNDERSCORE(); 


                }
                break;
            case 44 :
                // ./CubeSql.g:1:284: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\4\uffff\1\37\1\uffff\1\41\1\26\1\uffff\15\26\14\uffff\3\26\1\76"+
        "\1\77\1\26\1\101\1\102\20\26\1\124\3\26\2\uffff\1\130\2\uffff\1"+
        "\131\7\26\1\141\2\26\1\144\1\145\4\26\1\uffff\1\152\1\153\1\26\2"+
        "\uffff\1\26\1\156\5\26\1\uffff\2\26\2\uffff\2\26\1\170\1\26\2\uffff"+
        "\1\172\1\26\1\uffff\1\174\7\26\1\u0084\1\uffff\1\u0085\1\uffff\1"+
        "\u0086\1\uffff\3\26\1\u008a\3\26\3\uffff\1\u008e\2\26\1\uffff\3"+
        "\26\1\uffff\4\26\1\u0098\1\26\1\u009a\1\u009b\1\u009d\1\uffff\1"+
        "\u009e\2\uffff\1\26\2\uffff\4\26\1\u00a4\1\uffff";
    static final String DFA5_eofS =
        "\u00a5\uffff";
    static final String DFA5_minS =
        "\1\11\3\uffff\1\75\1\uffff\1\75\1\105\1\uffff\1\106\1\116\1\117"+
        "\2\105\1\111\1\105\1\111\1\101\1\110\2\122\1\131\14\uffff\1\113"+
        "\1\123\1\126\2\55\1\104\2\55\1\107\1\105\1\102\1\125\1\106\2\114"+
        "\2\115\1\105\1\130\1\116\1\101\1\105\2\117\1\55\1\105\1\124\1\105"+
        "\2\uffff\1\55\2\uffff\1\55\1\101\1\105\1\116\1\101\1\105\1\137\1"+
        "\105\1\55\1\105\1\122\2\55\1\123\1\122\1\115\1\125\1\uffff\2\55"+
        "\1\114\2\uffff\1\124\1\55\2\124\1\122\1\124\1\103\1\uffff\1\116"+
        "\1\101\2\uffff\1\125\1\105\1\55\1\120\2\uffff\1\55\1\105\1\uffff"+
        "\1\55\2\105\1\101\1\124\1\123\2\122\1\55\1\uffff\1\55\1\uffff\1"+
        "\55\1\uffff\1\104\1\116\1\102\1\55\1\111\1\103\1\105\3\uffff\1\55"+
        "\1\103\1\114\1\uffff\1\117\1\110\1\123\1\uffff\2\105\1\116\1\131"+
        "\1\55\1\123\3\55\1\uffff\1\55\2\uffff\1\124\2\uffff\1\101\1\102"+
        "\1\114\1\105\1\55\1\uffff";
    static final String DFA5_maxS =
        "\1\175\3\uffff\1\75\1\uffff\1\75\1\151\1\uffff\1\162\1\166\1\165"+
        "\1\145\1\165\4\151\1\150\2\162\1\171\14\uffff\2\163\1\166\2\176"+
        "\1\144\2\176\1\147\1\145\1\142\1\165\3\154\2\155\1\145\1\170\1\156"+
        "\1\141\1\145\2\157\1\176\1\105\1\164\1\145\2\uffff\1\176\2\uffff"+
        "\1\176\1\141\1\145\1\156\1\141\1\145\1\137\1\145\1\176\1\145\1\162"+
        "\2\176\1\163\1\162\1\155\1\165\1\uffff\2\176\1\154\2\uffff\1\164"+
        "\1\176\2\164\1\162\1\164\1\143\1\uffff\1\156\1\141\2\uffff\1\165"+
        "\1\145\1\176\1\160\2\uffff\1\176\1\145\1\uffff\1\176\2\145\1\141"+
        "\1\164\1\163\2\162\1\176\1\uffff\1\176\1\uffff\1\176\1\uffff\1\144"+
        "\1\156\1\142\1\176\1\151\1\143\1\145\3\uffff\1\176\1\143\1\154\1"+
        "\uffff\1\157\1\150\1\163\1\uffff\2\145\1\156\1\171\1\176\1\163\3"+
        "\176\1\uffff\1\176\2\uffff\1\164\2\uffff\1\141\1\142\1\154\1\145"+
        "\1\176\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\uffff\1\6\2\uffff\1\11\15\uffff\1\44\1\45"+
        "\1\46\1\47\1\50\1\52\1\53\1\54\1\5\1\4\1\7\1\51\34\uffff\1\12\1"+
        "\24\1\uffff\1\26\1\27\21\uffff\1\42\3\uffff\1\13\1\32\7\uffff\1"+
        "\33\2\uffff\1\34\1\35\4\uffff\1\10\1\23\2\uffff\1\15\11\uffff\1"+
        "\40\1\uffff\1\25\1\uffff\1\36\7\uffff\1\37\1\41\1\14\3\uffff\1\31"+
        "\3\uffff\1\16\11\uffff\1\43\1\uffff\1\17\1\21\1\uffff\1\30\1\20"+
        "\5\uffff\1\22";
    static final String DFA5_specialS =
        "\u00a5\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\35\1\uffff\2\35\22\uffff\1\35\1\uffff\1\1\4\uffff\1\10\1"+
            "\2\1\3\2\uffff\1\32\1\uffff\1\31\14\uffff\1\33\1\4\1\5\1\6\2"+
            "\uffff\1\12\1\25\1\13\1\16\1\26\1\23\1\24\1\20\3\26\1\7\1\21"+
            "\1\26\1\11\2\26\1\14\1\15\3\26\1\22\3\26\4\uffff\1\34\1\uffff"+
            "\1\12\1\25\1\13\1\16\1\26\1\23\1\24\1\20\3\26\1\17\1\21\1\26"+
            "\1\11\2\26\1\14\1\15\3\26\1\22\3\26\1\27\1\uffff\1\30",
            "",
            "",
            "",
            "\1\36",
            "",
            "\1\40",
            "\1\44\3\uffff\1\42\33\uffff\1\44\3\uffff\1\43",
            "",
            "\1\46\13\uffff\1\45\23\uffff\1\46\13\uffff\1\45",
            "\1\47\4\uffff\1\50\1\51\1\uffff\1\52\27\uffff\1\47\4\uffff"+
            "\1\50\1\51\1\uffff\1\52",
            "\1\55\2\uffff\1\53\2\uffff\1\54\31\uffff\1\55\2\uffff\1\53"+
            "\2\uffff\1\54",
            "\1\56\37\uffff\1\56",
            "\1\60\13\uffff\1\57\3\uffff\1\61\17\uffff\1\60\13\uffff\1\57"+
            "\3\uffff\1\61",
            "\1\62\37\uffff\1\62",
            "\1\44\3\uffff\1\43\33\uffff\1\44\3\uffff\1\43",
            "\1\63\37\uffff\1\63",
            "\1\64\3\uffff\1\66\3\uffff\1\65\27\uffff\1\64\3\uffff\1\66"+
            "\3\uffff\1\65",
            "\1\67\37\uffff\1\67",
            "\1\70\37\uffff\1\70",
            "\1\71\37\uffff\1\71",
            "\1\72\37\uffff\1\72",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\73\7\uffff\1\74\37\uffff\1\74",
            "\1\74\37\uffff\1\74",
            "\1\75\37\uffff\1\75",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\100\37\uffff\1\100",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\103\37\uffff\1\103",
            "\1\104\37\uffff\1\104",
            "\1\105\37\uffff\1\105",
            "\1\106\37\uffff\1\106",
            "\1\110\5\uffff\1\107\31\uffff\1\110\5\uffff\1\107",
            "\1\111\37\uffff\1\111",
            "\1\112\37\uffff\1\112",
            "\1\113\37\uffff\1\113",
            "\1\114\37\uffff\1\114",
            "\1\115\37\uffff\1\115",
            "\1\116\37\uffff\1\116",
            "\1\117\37\uffff\1\117",
            "\1\120\37\uffff\1\120",
            "\1\121\37\uffff\1\121",
            "\1\122\37\uffff\1\122",
            "\1\123\37\uffff\1\123",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\125",
            "\1\126\37\uffff\1\126",
            "\1\127\37\uffff\1\127",
            "",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\132\37\uffff\1\132",
            "\1\133\37\uffff\1\133",
            "\1\134\37\uffff\1\134",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\137",
            "\1\140\37\uffff\1\140",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\142\37\uffff\1\142",
            "\1\143\37\uffff\1\143",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\146\37\uffff\1\146",
            "\1\147\37\uffff\1\147",
            "\1\150\37\uffff\1\150",
            "\1\151\37\uffff\1\151",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\154\37\uffff\1\154",
            "",
            "",
            "\1\155\37\uffff\1\155",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\157\37\uffff\1\157",
            "\1\160\37\uffff\1\160",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "",
            "",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\171\37\uffff\1\171",
            "",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\173\37\uffff\1\173",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\175\37\uffff\1\175",
            "\1\176\37\uffff\1\176",
            "\1\177\37\uffff\1\177",
            "\1\u0080\37\uffff\1\u0080",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088\37\uffff\1\u0088",
            "\1\u0089\37\uffff\1\u0089",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "",
            "",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\u0099\37\uffff\1\u0099",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\u009c\1\uffff\32"+
            "\26\3\uffff\1\26",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            "",
            "",
            "\1\u009f\37\uffff\1\u009f",
            "",
            "",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\26\2\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26"+
            "\3\uffff\1\26",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | OR | AND | CREATE | CUBE | RELATED | SQL_TABLE | REFERENCES | DIMENSION | DIMENSION_TABLE | LIST | OF | LEVEL | AS | AT | HIERARCHY | SELECT | AVG | SUM | MAX | MIN | COUNT | WHERE | FROM | GROUP | BY | MEASURES | NAME | LBRACE | RBRACE | DOT | COMMA | CHILDOF | QUESTMARK | UNDERSCORE | WS );";
        }
    }
 

}