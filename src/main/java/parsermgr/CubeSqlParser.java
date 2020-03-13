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
  
  import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


/**
 * The parser class for the declaration of dimensions and cubes
 * 
 *  @author D. Gkesoulis
 *  @version 0.0.1
 *  @see CubeSqlLexer
 *  @see ParserManager
 *  @since v0.0.0 (the Cincecubes system) 
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class CubeSqlParser extends Parser {
  
	public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AND", "AS", "AT", "AVG", "B", "BY", "C", "CHILDOF", "COMMA", "COUNT", "CREATE", "CUBE", "D", "DIMENSION", "DIMENSION_TABLE", "DOT", "Digit", "E", "F", "FROM", "G", "GROUP", "H", "HIERARCHY", "I", "J", "K", "L", "LBRACE", "LEVEL", "LIST", "Letter", "M", "MAX", "MEASURES", "MIN", "N", "NAME", "O", "OF", "OR", "P", "Q", "QUESTMARK", "R", "RBRACE", "REFERENCES", "RELATED", "S", "SELECT", "SQL_TABLE", "SUM", "T", "U", "UNDERSCORE", "V", "W", "WHERE", "WS", "X", "Y", "Z", "'\"'", "'('", "')'", "'<'", "'<='", "'='", "'>='", "'LIKE'", "'\\''"
    };

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
    
    //alex: token Names from the list
    public static final int A=4;
    public static final int AND=5;
    public static final int AS=6;
    public static final int AT=7;
    public static final int AVG=8;
    ///public static final int B=9;
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public CubeSqlParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    
    
    //alex :8a  mporouse na private
    public CubeSqlParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }
 

    public String[] getTokenNames() { return CubeSqlParser.tokenNames; }
    public String getGrammarFileName() { return "./CubeSql.g"; }

    	//alex:	pedia???
      Integer mode;
      String name_creation;
      String sql_table;
      ArrayList<String> dimensionlst;
      ArrayList<String> hierachylst;
      ArrayList<String> originallvllst;
      ArrayList<String> customlvllst;
      ArrayList<String> conditionlst;
      ArrayList<String> tablelst;
      ArrayList<String> groupperlst;
      ArrayList<String> measurelst;
      ArrayList<String> measurefields;
      String aggregatefunc;
      String tmp_con;
      boolean group;



    // $ANTLR start "start"
    // ./CubeSql.g:42:1: start : parse ;
    public final void start() throws RecognitionException {
        try {
            // ./CubeSql.g:42:7: ( parse )
            // ./CubeSql.g:42:8: parse
            {

                mode=0;
                tmp_con="";
                group=false;
                dimensionlst=new ArrayList<String>();
                hierachylst=new ArrayList<String>();
                originallvllst=new ArrayList<String>();
                customlvllst=new ArrayList<String>();
                conditionlst=new ArrayList<String>();
                groupperlst=new ArrayList<String>();
                tablelst=new ArrayList<String>();
                measurelst=new ArrayList<String>();
                measurefields=new ArrayList<String>();
              

            pushFollow(FOLLOW_parse_in_start131);
            parse();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "start"

    
    
    // $ANTLR start "parse"
    // ./CubeSql.g:57:1: parse : ( ( creation_statement )* | sql_query );
    public final void parse() throws RecognitionException {
        try {
            // ./CubeSql.g:57:7: ( ( creation_statement )* | sql_query )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==EOF||LA2_0==CREATE) ) {
                alt2=1;
            }
            else if ( (LA2_0==SELECT) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // ./CubeSql.g:57:10: ( creation_statement )*
                    {
                    // ./CubeSql.g:57:10: ( creation_statement )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==CREATE) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ./CubeSql.g:57:11: creation_statement
                    	    {
                    	      mode=0;
                    	                name_creation="";
                    	                sql_table="";
                    	                if(dimensionlst.size()>0)  dimensionlst.clear();
                    	                if(hierachylst.size()>0)  hierachylst.clear();
                    	                if(originallvllst.size()>0)  originallvllst.clear();
                    	                if(customlvllst.size()>0)  customlvllst.clear();
                    	               

                    	    pushFollow(FOLLOW_creation_statement_in_parse156);
                    	    creation_statement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // ./CubeSql.g:66:11: sql_query
                    {
                    pushFollow(FOLLOW_sql_query_in_parse172);
                    sql_query();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "parse"



    // $ANTLR start "creation_statement"
    // ./CubeSql.g:69:1: creation_statement : creation QUESTMARK ;
    public final void creation_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:69:20: ( creation QUESTMARK )
            // ./CubeSql.g:69:22: creation QUESTMARK
            {
            pushFollow(FOLLOW_creation_in_creation_statement184);
            creation();

            state._fsp--;


            match(input,QUESTMARK,FOLLOW_QUESTMARK_in_creation_statement186); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creation_statement"



    // $ANTLR start "creation"
    // ./CubeSql.g:71:1: creation : ( creation_cube | creation_dimension );
    public final void creation() throws RecognitionException {
        try {
            // ./CubeSql.g:71:9: ( creation_cube | creation_dimension )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==CREATE) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==CUBE) ) {
                    int LA3_2 = input.LA(3);

                    if ( (LA3_2==NAME) ) {
                        int LA3_4 = input.LA(4);

                        if ( (LA3_4==RELATED) ) {
                            int LA3_6 = input.LA(5);

                            if ( (LA3_6==SQL_TABLE) ) {
                                int LA3_7 = input.LA(6);

                                if ( (LA3_7==NAME) ) {
                                    int LA3_8 = input.LA(7);

                                    if ( (LA3_8==MEASURES) ) {
                                        alt3=1;
                                    }
                                    else if ( (LA3_8==LIST) ) {
                                        alt3=2;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 3, 8, input);

                                        throw nvae;

                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 3, 7, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 6, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 4, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA3_1==DIMENSION) ) {
                    int LA3_3 = input.LA(3);

                    if ( (LA3_3==NAME) ) {
                        int LA3_5 = input.LA(4);

                        if ( (LA3_5==RELATED) ) {
                            int LA3_6 = input.LA(5);

                            if ( (LA3_6==SQL_TABLE) ) {
                                int LA3_7 = input.LA(6);

                                if ( (LA3_7==NAME) ) {
                                    int LA3_8 = input.LA(7);

                                    if ( (LA3_8==MEASURES) ) {
                                        alt3=1;
                                    }
                                    else if ( (LA3_8==LIST) ) {
                                        alt3=2;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 3, 8, input);

                                        throw nvae;

                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 3, 7, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 6, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 5, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // ./CubeSql.g:71:11: creation_cube
                    {
                    pushFollow(FOLLOW_creation_cube_in_creation193);
                    creation_cube();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ./CubeSql.g:71:27: creation_dimension
                    {
                    pushFollow(FOLLOW_creation_dimension_in_creation197);
                    creation_dimension();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creation"



    // $ANTLR start "creation_cube"
    // ./CubeSql.g:73:1: creation_cube : create_statement related_statement measure_statement referdimension_statement ;
    public final void creation_cube() throws RecognitionException {
        try {
            // ./CubeSql.g:73:15: ( create_statement related_statement measure_statement referdimension_statement )
            // ./CubeSql.g:73:17: create_statement related_statement measure_statement referdimension_statement
            {
            pushFollow(FOLLOW_create_statement_in_creation_cube206);
            create_statement();

            state._fsp--;


            pushFollow(FOLLOW_related_statement_in_creation_cube208);
            related_statement();

            state._fsp--;


            pushFollow(FOLLOW_measure_statement_in_creation_cube210);
            measure_statement();

            state._fsp--;


            pushFollow(FOLLOW_referdimension_statement_in_creation_cube212);
            referdimension_statement();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creation_cube"



    // $ANTLR start "creation_dimension"
    // ./CubeSql.g:75:1: creation_dimension : create_statement related_statement level_statement hierarchy_statement ;
    public final void creation_dimension() throws RecognitionException {
        try {
            // ./CubeSql.g:75:20: ( create_statement related_statement level_statement hierarchy_statement )
            // ./CubeSql.g:75:22: create_statement related_statement level_statement hierarchy_statement
            {
            pushFollow(FOLLOW_create_statement_in_creation_dimension220);
            create_statement();

            state._fsp--;


            pushFollow(FOLLOW_related_statement_in_creation_dimension222);
            related_statement();

            state._fsp--;


            pushFollow(FOLLOW_level_statement_in_creation_dimension224);
            level_statement();

            state._fsp--;


            pushFollow(FOLLOW_hierarchy_statement_in_creation_dimension226);
            hierarchy_statement();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creation_dimension"



    // $ANTLR start "create_statement"
    // ./CubeSql.g:77:1: create_statement : ( CREATE CUBE NAME | CREATE DIMENSION NAME );
    public final void create_statement() throws RecognitionException {
        Token NAME1=null;
        Token NAME2=null;

        try {
            // ./CubeSql.g:77:17: ( CREATE CUBE NAME | CREATE DIMENSION NAME )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==CREATE) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==CUBE) ) {
                    alt4=1;
                }
                else if ( (LA4_1==DIMENSION) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // ./CubeSql.g:77:19: CREATE CUBE NAME
                    {
                    match(input,CREATE,FOLLOW_CREATE_in_create_statement233); 

                    match(input,CUBE,FOLLOW_CUBE_in_create_statement235); 

                    mode=1;

                    NAME1=(Token)match(input,NAME,FOLLOW_NAME_in_create_statement239); 

                    name_creation=(NAME1!=null?NAME1.getText():null);

                    }
                    break;
                case 2 :
                    // ./CubeSql.g:79:19: CREATE DIMENSION NAME
                    {
                    match(input,CREATE,FOLLOW_CREATE_in_create_statement282); 

                    match(input,DIMENSION,FOLLOW_DIMENSION_in_create_statement284); 

                    mode=2;

                    NAME2=(Token)match(input,NAME,FOLLOW_NAME_in_create_statement288); 

                    name_creation=(NAME2!=null?NAME2.getText():null).replace("~"," ");

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "create_statement"



    // $ANTLR start "related_statement"
    // ./CubeSql.g:81:1: related_statement : RELATED SQL_TABLE NAME ;
    public final void related_statement() throws RecognitionException {
        Token NAME3=null;

        try {
            // ./CubeSql.g:81:19: ( RELATED SQL_TABLE NAME )
            // ./CubeSql.g:81:21: RELATED SQL_TABLE NAME
            {
            match(input,RELATED,FOLLOW_RELATED_in_related_statement298); 

            match(input,SQL_TABLE,FOLLOW_SQL_TABLE_in_related_statement300); 

            NAME3=(Token)match(input,NAME,FOLLOW_NAME_in_related_statement302); 

            sql_table=(NAME3!=null?NAME3.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "related_statement"



    // $ANTLR start "referdimension_statement"
    // ./CubeSql.g:83:1: referdimension_statement : REFERENCES DIMENSION dimensions ;
    public final void referdimension_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:83:26: ( REFERENCES DIMENSION dimensions )
            // ./CubeSql.g:83:28: REFERENCES DIMENSION dimensions
            {
            match(input,REFERENCES,FOLLOW_REFERENCES_in_referdimension_statement313); 

            match(input,DIMENSION,FOLLOW_DIMENSION_in_referdimension_statement315); 

            pushFollow(FOLLOW_dimensions_in_referdimension_statement317);
            dimensions();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "referdimension_statement"



    // $ANTLR start "measure_statement"
    // ./CubeSql.g:85:1: measure_statement : MEASURES measures ;
    public final void measure_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:85:19: ( MEASURES measures )
            // ./CubeSql.g:85:21: MEASURES measures
            {
            match(input,MEASURES,FOLLOW_MEASURES_in_measure_statement325); 

            pushFollow(FOLLOW_measures_in_measure_statement327);
            measures();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "measure_statement"



    // $ANTLR start "level_statement"
    // ./CubeSql.g:87:1: level_statement : LIST OF LEVEL LBRACE levels RBRACE ;
    public final void level_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:87:16: ( LIST OF LEVEL LBRACE levels RBRACE )
            // ./CubeSql.g:87:18: LIST OF LEVEL LBRACE levels RBRACE
            {
            match(input,LIST,FOLLOW_LIST_in_level_statement334); 

            match(input,OF,FOLLOW_OF_in_level_statement336); 

            match(input,LEVEL,FOLLOW_LEVEL_in_level_statement338); 

            match(input,LBRACE,FOLLOW_LBRACE_in_level_statement340); 

            pushFollow(FOLLOW_levels_in_level_statement342);
            levels();

            state._fsp--;


            match(input,RBRACE,FOLLOW_RBRACE_in_level_statement344); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "level_statement"



    // $ANTLR start "hierarchy_statement"
    // ./CubeSql.g:89:1: hierarchy_statement : HIERARCHY hierarchy ;
    public final void hierarchy_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:89:21: ( HIERARCHY hierarchy )
            // ./CubeSql.g:89:23: HIERARCHY hierarchy
            {
            match(input,HIERARCHY,FOLLOW_HIERARCHY_in_hierarchy_statement353); 

            pushFollow(FOLLOW_hierarchy_in_hierarchy_statement355);
            hierarchy();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "hierarchy_statement"



    // $ANTLR start "dimensions"
    // ./CubeSql.g:91:1: dimensions : dimension ( comma_statement dimension )* ;
    public final void dimensions() throws RecognitionException {
        try {
            // ./CubeSql.g:91:12: ( dimension ( comma_statement dimension )* )
            // ./CubeSql.g:91:14: dimension ( comma_statement dimension )*
            {
            pushFollow(FOLLOW_dimension_in_dimensions363);
            dimension();

            state._fsp--;


            // ./CubeSql.g:91:25: ( comma_statement dimension )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA||LA5_0==WS) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ./CubeSql.g:91:26: comma_statement dimension
            	    {
            	    pushFollow(FOLLOW_comma_statement_in_dimensions367);
            	    comma_statement();

            	    state._fsp--;


            	    pushFollow(FOLLOW_dimension_in_dimensions369);
            	    dimension();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "dimensions"



    // $ANTLR start "dimension"
    // ./CubeSql.g:93:1: dimension : NAME AT sqlfield ;
    public final void dimension() throws RecognitionException {
        Token NAME4=null;

        try {
            // ./CubeSql.g:93:11: ( NAME AT sqlfield )
            // ./CubeSql.g:93:13: NAME AT sqlfield
            {
            NAME4=(Token)match(input,NAME,FOLLOW_NAME_in_dimension380); 

            dimensionlst.add((NAME4!=null?NAME4.getText():null).replace("~"," "));

            match(input,AT,FOLLOW_AT_in_dimension384); 

            pushFollow(FOLLOW_sqlfield_in_dimension386);
            sqlfield();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "dimension"



    // $ANTLR start "measures"
    // ./CubeSql.g:95:1: measures : measure ( comma_statement measure )* ;
    public final void measures() throws RecognitionException {
        try {
            // ./CubeSql.g:95:10: ( measure ( comma_statement measure )* )
            // ./CubeSql.g:95:12: measure ( comma_statement measure )*
            {
            pushFollow(FOLLOW_measure_in_measures394);
            measure();

            state._fsp--;


            // ./CubeSql.g:95:20: ( comma_statement measure )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA||LA6_0==WS) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ./CubeSql.g:95:21: comma_statement measure
            	    {
            	    pushFollow(FOLLOW_comma_statement_in_measures397);
            	    comma_statement();

            	    state._fsp--;


            	    pushFollow(FOLLOW_measure_in_measures399);
            	    measure();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "measures"



    // $ANTLR start "measure"
    // ./CubeSql.g:97:1: measure : NAME AT sqlfield ;
    public final void measure() throws RecognitionException {
        Token NAME5=null;

        try {
            // ./CubeSql.g:97:9: ( NAME AT sqlfield )
            // ./CubeSql.g:97:11: NAME AT sqlfield
            {
            NAME5=(Token)match(input,NAME,FOLLOW_NAME_in_measure409); 

            measurelst.add((NAME5!=null?NAME5.getText():null));mode=3;tmp_con="";

            match(input,AT,FOLLOW_AT_in_measure413); 

            pushFollow(FOLLOW_sqlfield_in_measure415);
            sqlfield();

            state._fsp--;


            mode=1;measurefields.add(tmp_con);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "measure"



    // $ANTLR start "levels"
    // ./CubeSql.g:99:1: levels : level ( comma_statement level )* ;
    public final void levels() throws RecognitionException {
        try {
            // ./CubeSql.g:99:7: ( level ( comma_statement level )* )
            // ./CubeSql.g:99:9: level ( comma_statement level )*
            {
            pushFollow(FOLLOW_level_in_levels425);
            level();

            state._fsp--;


            // ./CubeSql.g:99:15: ( comma_statement level )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==COMMA||LA7_0==WS) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ./CubeSql.g:99:16: comma_statement level
            	    {
            	    pushFollow(FOLLOW_comma_statement_in_levels428);
            	    comma_statement();

            	    state._fsp--;


            	    pushFollow(FOLLOW_level_in_levels430);
            	    level();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "levels"



    // $ANTLR start "comma_statement"
    // ./CubeSql.g:101:1: comma_statement : ( WS )* COMMA ( WS )* ;
    public final void comma_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:101:16: ( ( WS )* COMMA ( WS )* )
            // ./CubeSql.g:101:18: ( WS )* COMMA ( WS )*
            {
            // ./CubeSql.g:101:18: ( WS )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==WS) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ./CubeSql.g:101:18: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_comma_statement439); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_comma_statement442); 

            // ./CubeSql.g:101:28: ( WS )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==WS) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ./CubeSql.g:101:28: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_comma_statement444); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "comma_statement"



    // $ANTLR start "level"
    // ./CubeSql.g:103:1: level : ( sqlfield AS name3= NAME | sqlfield );
    public final void level() throws RecognitionException {
        Token name3=null;

        try {
            // ./CubeSql.g:103:7: ( sqlfield AS name3= NAME | sqlfield )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NAME) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA10_2 = input.LA(3);

                    if ( (LA10_2==NAME) ) {
                        int LA10_5 = input.LA(4);

                        if ( (LA10_5==AS) ) {
                            alt10=1;
                        }
                        else if ( (LA10_5==COMMA||LA10_5==RBRACE||LA10_5==WS) ) {
                            alt10=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 10, 5, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 2, input);

                        throw nvae;

                    }
                    }
                    break;
                case AS:
                    {
                    alt10=1;
                    }
                    break;
                case COMMA:
                case RBRACE:
                case WS:
                    {
                    alt10=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // ./CubeSql.g:103:10: sqlfield AS name3= NAME
                    {
                    pushFollow(FOLLOW_sqlfield_in_level454);
                    sqlfield();

                    state._fsp--;


                    match(input,AS,FOLLOW_AS_in_level456); 

                    name3=(Token)match(input,NAME,FOLLOW_NAME_in_level460); 

                    customlvllst.add((name3!=null?name3.getText():null));

                    }
                    break;
                case 2 :
                    // ./CubeSql.g:104:11: sqlfield
                    {
                    pushFollow(FOLLOW_sqlfield_in_level474);
                    sqlfield();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "level"



    // $ANTLR start "hierarchy"
    // ./CubeSql.g:106:1: hierarchy : name1= NAME ( CHILDOF name2= NAME )+ ;
    public final void hierarchy() throws RecognitionException {
        Token name1=null;
        Token name2=null;

        try {
            // ./CubeSql.g:106:10: (name1= NAME ( CHILDOF name2= NAME )+ )
            // ./CubeSql.g:106:12: name1= NAME ( CHILDOF name2= NAME )+
            {
            name1=(Token)match(input,NAME,FOLLOW_NAME_in_hierarchy484); 

            dimensionlst.add((name1!=null?name1.getText():null));

            // ./CubeSql.g:106:56: ( CHILDOF name2= NAME )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==CHILDOF) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ./CubeSql.g:106:57: CHILDOF name2= NAME
            	    {
            	    match(input,CHILDOF,FOLLOW_CHILDOF_in_hierarchy489); 

            	    name2=(Token)match(input,NAME,FOLLOW_NAME_in_hierarchy493); 

            	    dimensionlst.add((name2!=null?name2.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "hierarchy"



    // $ANTLR start "sqlfield"
    // ./CubeSql.g:108:1: sqlfield : (name1= NAME DOT name2= NAME | NAME );
    public final void sqlfield() throws RecognitionException {
        Token name1=null;
        Token name2=null;
        Token NAME6=null;

        try {
            // ./CubeSql.g:108:10: (name1= NAME DOT name2= NAME | NAME )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NAME) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==DOT) ) {
                    alt12=1;
                }
                else if ( (LA12_1==EOF||(LA12_1 >= AND && LA12_1 <= AS)||(LA12_1 >= CHILDOF && LA12_1 <= COMMA)||LA12_1==GROUP||LA12_1==OR||LA12_1==QUESTMARK||(LA12_1 >= RBRACE && LA12_1 <= REFERENCES)||LA12_1==WS||(LA12_1 >= 69 && LA12_1 <= 74)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // ./CubeSql.g:108:12: name1= NAME DOT name2= NAME
                    {
                    name1=(Token)match(input,NAME,FOLLOW_NAME_in_sqlfield508); 

                    match(input,DOT,FOLLOW_DOT_in_sqlfield510); 

                    name2=(Token)match(input,NAME,FOLLOW_NAME_in_sqlfield514); 

                     if(mode==1 || mode==2) originallvllst.add((name1!=null?name1.getText():null)+"."+(name2!=null?name2.getText():null));
                                                          tmp_con+=(name1!=null?name1.getText():null)+"."+(name2!=null?name2.getText():null);


                    }
                    break;
                case 2 :
                    // ./CubeSql.g:111:11: NAME
                    {
                    NAME6=(Token)match(input,NAME,FOLLOW_NAME_in_sqlfield528); 

                    tmp_con+= (NAME6!=null?NAME6.getText():null);if(mode==1 || mode==2) {originallvllst.add((NAME6!=null?NAME6.getText():null));}

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "sqlfield"



    // $ANTLR start "sql_query"
    // ./CubeSql.g:114:1: sql_query : select_statement from_statement where_statement group_statement ;
    public final void sql_query() throws RecognitionException {
        try {
            // ./CubeSql.g:114:10: ( select_statement from_statement where_statement group_statement )
            // ./CubeSql.g:114:12: select_statement from_statement where_statement group_statement
            {
            pushFollow(FOLLOW_select_statement_in_sql_query546);
            select_statement();

            state._fsp--;


            pushFollow(FOLLOW_from_statement_in_sql_query560);
            from_statement();

            state._fsp--;


            pushFollow(FOLLOW_where_statement_in_sql_query573);
            where_statement();

            state._fsp--;


            pushFollow(FOLLOW_group_statement_in_sql_query586);
            group_statement();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "sql_query"



    // $ANTLR start "select_statement"
    // ./CubeSql.g:119:1: select_statement : SELECT grouppers COMMA aggregate_statement ;
    public final void select_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:119:17: ( SELECT grouppers COMMA aggregate_statement )
            // ./CubeSql.g:119:19: SELECT grouppers COMMA aggregate_statement
            {
            match(input,SELECT,FOLLOW_SELECT_in_select_statement604); 

            pushFollow(FOLLOW_grouppers_in_select_statement606);
            grouppers();

            state._fsp--;


            match(input,COMMA,FOLLOW_COMMA_in_select_statement608); 

            pushFollow(FOLLOW_aggregate_statement_in_select_statement610);
            aggregate_statement();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "select_statement"



    // $ANTLR start "aggregate_statement"
    // ./CubeSql.g:121:1: aggregate_statement : aggregate_func '(' sqlfield ')' ;
    public final void aggregate_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:121:21: ( aggregate_func '(' sqlfield ')' )
            // ./CubeSql.g:121:23: aggregate_func '(' sqlfield ')'
            {
            tmp_con="";

            pushFollow(FOLLOW_aggregate_func_in_aggregate_statement620);
            aggregate_func();

            state._fsp--;


            match(input,68,FOLLOW_68_in_aggregate_statement622); 

            pushFollow(FOLLOW_sqlfield_in_aggregate_statement624);
            sqlfield();

            state._fsp--;


            match(input,69,FOLLOW_69_in_aggregate_statement626); 

            aggregatefunc=tmp_con;tmp_con="";

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "aggregate_statement"



    // $ANTLR start "aggregate_func"
    // ./CubeSql.g:123:1: aggregate_func : ( SUM | AVG | COUNT | MAX | MIN );
    public final void aggregate_func() throws RecognitionException {
        Token SUM7=null;
        Token AVG8=null;
        Token COUNT9=null;
        Token MAX10=null;
        Token MIN11=null;

        try {
            // ./CubeSql.g:123:16: ( SUM | AVG | COUNT | MAX | MIN )
            int alt13=5;
            switch ( input.LA(1) ) {
            case SUM:
                {
                alt13=1;
                }
                break;
            case AVG:
                {
                alt13=2;
                }
                break;
            case COUNT:
                {
                alt13=3;
                }
                break;
            case MAX:
                {
                alt13=4;
                }
                break;
            case MIN:
                {
                alt13=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // ./CubeSql.g:123:18: SUM
                    {
                    SUM7=(Token)match(input,SUM,FOLLOW_SUM_in_aggregate_func636); 

                    tmp_con=(SUM7!=null?SUM7.getText():null)+"~";

                    }
                    break;
                case 2 :
                    // ./CubeSql.g:124:19: AVG
                    {
                    AVG8=(Token)match(input,AVG,FOLLOW_AVG_in_aggregate_func658); 

                    tmp_con=(AVG8!=null?AVG8.getText():null)+"~";

                    }
                    break;
                case 3 :
                    // ./CubeSql.g:125:19: COUNT
                    {
                    COUNT9=(Token)match(input,COUNT,FOLLOW_COUNT_in_aggregate_func681); 

                    tmp_con=(COUNT9!=null?COUNT9.getText():null)+"~";

                    }
                    break;
                case 4 :
                    // ./CubeSql.g:126:19: MAX
                    {
                    MAX10=(Token)match(input,MAX,FOLLOW_MAX_in_aggregate_func703); 

                    tmp_con=(MAX10!=null?MAX10.getText():null)+"~";

                    }
                    break;
                case 5 :
                    // ./CubeSql.g:127:19: MIN
                    {
                    MIN11=(Token)match(input,MIN,FOLLOW_MIN_in_aggregate_func725); 

                    tmp_con=(MIN11!=null?MIN11.getText():null)+"~";

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "aggregate_func"



    // $ANTLR start "from_statement"
    // ./CubeSql.g:129:1: from_statement : FROM tables ;
    public final void from_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:129:16: ( FROM tables )
            // ./CubeSql.g:129:18: FROM tables
            {
            match(input,FROM,FOLLOW_FROM_in_from_statement735); 

            pushFollow(FOLLOW_tables_in_from_statement737);
            tables();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "from_statement"



    // $ANTLR start "tables"
    // ./CubeSql.g:131:1: tables : table ( COMMA table )* ;
    public final void tables() throws RecognitionException {
        try {
            // ./CubeSql.g:131:8: ( table ( COMMA table )* )
            // ./CubeSql.g:131:10: table ( COMMA table )*
            {
            pushFollow(FOLLOW_table_in_tables745);
            table();

            state._fsp--;


            // ./CubeSql.g:131:16: ( COMMA table )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ./CubeSql.g:131:17: COMMA table
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_tables748); 

            	    pushFollow(FOLLOW_table_in_tables750);
            	    table();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "tables"



    // $ANTLR start "table"
    // ./CubeSql.g:133:1: table : (name1= NAME AS name2= NAME |name1= NAME name2= NAME | NAME );
    public final void table() throws RecognitionException {
        Token name1=null;
        Token name2=null;
        Token NAME12=null;

        try {
            // ./CubeSql.g:133:7: (name1= NAME AS name2= NAME |name1= NAME name2= NAME | NAME )
            int alt15=3;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NAME) ) {
                switch ( input.LA(2) ) {
                case AS:
                    {
                    alt15=1;
                    }
                    break;
                case NAME:
                    {
                    alt15=2;
                    }
                    break;
                case COMMA:
                case GROUP:
                case WHERE:
                    {
                    alt15=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // ./CubeSql.g:133:9: name1= NAME AS name2= NAME
                    {
                    name1=(Token)match(input,NAME,FOLLOW_NAME_in_table762); 

                    match(input,AS,FOLLOW_AS_in_table764); 

                    name2=(Token)match(input,NAME,FOLLOW_NAME_in_table768); 

                    tablelst.add((name1!=null?name1.getText():null)+"~"+(name2!=null?name2.getText():null));

                    }
                    break;
                case 2 :
                    // ./CubeSql.g:134:9: name1= NAME name2= NAME
                    {
                    name1=(Token)match(input,NAME,FOLLOW_NAME_in_table783); 

                    name2=(Token)match(input,NAME,FOLLOW_NAME_in_table787); 

                    tablelst.add((name1!=null?name1.getText():null)+"~"+(name2!=null?name2.getText():null));

                    }
                    break;
                case 3 :
                    // ./CubeSql.g:135:9: NAME
                    {
                    NAME12=(Token)match(input,NAME,FOLLOW_NAME_in_table799); 

                    tablelst.add((NAME12!=null?NAME12.getText():null));

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "table"



    // $ANTLR start "where_statement"
    // ./CubeSql.g:137:1: where_statement : ( WHERE cond_statement ( boolean_expr cond_statement )* |);
    public final void where_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:137:17: ( WHERE cond_statement ( boolean_expr cond_statement )* |)
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==WHERE) ) {
                alt17=1;
            }
            else if ( (LA17_0==GROUP) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // ./CubeSql.g:137:19: WHERE cond_statement ( boolean_expr cond_statement )*
                    {
                    match(input,WHERE,FOLLOW_WHERE_in_where_statement809); 

                    tmp_con="";

                    pushFollow(FOLLOW_cond_statement_in_where_statement813);
                    cond_statement();

                    state._fsp--;


                    conditionlst.add(tmp_con);

                    // ./CubeSql.g:137:83: ( boolean_expr cond_statement )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==AND||LA16_0==OR) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ./CubeSql.g:137:84: boolean_expr cond_statement
                    	    {
                    	    pushFollow(FOLLOW_boolean_expr_in_where_statement818);
                    	    boolean_expr();

                    	    state._fsp--;


                    	    tmp_con="";

                    	    pushFollow(FOLLOW_cond_statement_in_where_statement822);
                    	    cond_statement();

                    	    state._fsp--;


                    	    conditionlst.add(tmp_con);

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // ./CubeSql.g:138:21: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "where_statement"



    // $ANTLR start "cond_statement"
    // ./CubeSql.g:139:1: cond_statement : ( sqlfield operator sqlfield | sqlfield operator quote_statement NAME quote_statement | sqlfield operator quote_statement ( Digit )+ NAME quote_statement | sqlfield operator ( Digit )+ | sqlfield operator quote_statement quote_statement );
    public final void cond_statement() throws RecognitionException {
        Token NAME15=null;
        Token Digit17=null;
        Token NAME18=null;
        Token Digit20=null;
        CubeSqlParser.operator_return operator13 =null;

        CubeSqlParser.operator_return operator14 =null;

        CubeSqlParser.operator_return operator16 =null;

        CubeSqlParser.operator_return operator19 =null;

        CubeSqlParser.operator_return operator21 =null;


        try {
            // ./CubeSql.g:139:16: ( sqlfield operator sqlfield | sqlfield operator quote_statement NAME quote_statement | sqlfield operator quote_statement ( Digit )+ NAME quote_statement | sqlfield operator ( Digit )+ | sqlfield operator quote_statement quote_statement )
            int alt20=5;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NAME) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==DOT) ) {
                    int LA20_2 = input.LA(3);

                    if ( (LA20_2==NAME) ) {
                        int LA20_4 = input.LA(4);

                        if ( (LA20_4==CHILDOF||(LA20_4 >= 70 && LA20_4 <= 74)) ) {
                            switch ( input.LA(5) ) {
                            case NAME:
                                {
                                alt20=1;
                                }
                                break;
                            case 67:
                            case 75:
                                {
                                switch ( input.LA(6) ) {
                                case NAME:
                                    {
                                    alt20=2;
                                    }
                                    break;
                                case Digit:
                                    {
                                    alt20=3;
                                    }
                                    break;
                                case 67:
                                case 75:
                                    {
                                    alt20=5;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 20, 6, input);

                                    throw nvae;

                                }

                                }
                                break;
                            case Digit:
                                {
                                alt20=4;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("", 20, 3, input);

                                throw nvae;

                            }

                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 20, 4, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA20_1==CHILDOF||(LA20_1 >= 70 && LA20_1 <= 74)) ) {
                    switch ( input.LA(3) ) {
                    case NAME:
                        {
                        alt20=1;
                        }
                        break;
                    case 67:
                    case 75:
                        {
                        switch ( input.LA(4) ) {
                        case NAME:
                            {
                            alt20=2;
                            }
                            break;
                        case Digit:
                            {
                            alt20=3;
                            }
                            break;
                        case 67:
                        case 75:
                            {
                            alt20=5;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 20, 6, input);

                            throw nvae;

                        }

                        }
                        break;
                    case Digit:
                        {
                        alt20=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 3, input);

                        throw nvae;

                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // ./CubeSql.g:139:18: sqlfield operator sqlfield
                    {
                    pushFollow(FOLLOW_sqlfield_in_cond_statement854);
                    sqlfield();

                    state._fsp--;


                    pushFollow(FOLLOW_operator_in_cond_statement856);
                    operator13=operator();

                    state._fsp--;


                    tmp_con+="~"+(operator13!=null?input.toString(operator13.start,operator13.stop):null)+"~";

                    pushFollow(FOLLOW_sqlfield_in_cond_statement860);
                    sqlfield();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ./CubeSql.g:140:19: sqlfield operator quote_statement NAME quote_statement
                    {
                    pushFollow(FOLLOW_sqlfield_in_cond_statement881);
                    sqlfield();

                    state._fsp--;


                    pushFollow(FOLLOW_operator_in_cond_statement883);
                    operator14=operator();

                    state._fsp--;


                    tmp_con+="~"+(operator14!=null?input.toString(operator14.start,operator14.stop):null)+"~";

                    pushFollow(FOLLOW_quote_statement_in_cond_statement888);
                    quote_statement();

                    state._fsp--;


                    NAME15=(Token)match(input,NAME,FOLLOW_NAME_in_cond_statement890); 

                    pushFollow(FOLLOW_quote_statement_in_cond_statement892);
                    quote_statement();

                    state._fsp--;


                    tmp_con+="'"+(NAME15!=null?NAME15.getText():null)+"'";

                    }
                    break;
                case 3 :
                    // ./CubeSql.g:141:19: sqlfield operator quote_statement ( Digit )+ NAME quote_statement
                    {
                    pushFollow(FOLLOW_sqlfield_in_cond_statement914);
                    sqlfield();

                    state._fsp--;


                    pushFollow(FOLLOW_operator_in_cond_statement916);
                    operator16=operator();

                    state._fsp--;


                    tmp_con+="~"+(operator16!=null?input.toString(operator16.start,operator16.stop):null)+"~";

                    pushFollow(FOLLOW_quote_statement_in_cond_statement921);
                    quote_statement();

                    state._fsp--;


                    // ./CubeSql.g:141:89: ( Digit )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==Digit) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ./CubeSql.g:141:90: Digit
                    	    {
                    	    Digit17=(Token)match(input,Digit,FOLLOW_Digit_in_cond_statement924); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);


                    NAME18=(Token)match(input,NAME,FOLLOW_NAME_in_cond_statement928); 

                    pushFollow(FOLLOW_quote_statement_in_cond_statement931);
                    quote_statement();

                    state._fsp--;


                    tmp_con+="'"+(Digit17!=null?Digit17.getText():null)+(NAME18!=null?NAME18.getText():null)+"'";

                    }
                    break;
                case 4 :
                    // ./CubeSql.g:142:19: sqlfield operator ( Digit )+
                    {
                    pushFollow(FOLLOW_sqlfield_in_cond_statement953);
                    sqlfield();

                    state._fsp--;


                    pushFollow(FOLLOW_operator_in_cond_statement955);
                    operator19=operator();

                    state._fsp--;


                    tmp_con+="~"+(operator19!=null?input.toString(operator19.start,operator19.stop):null)+"~";

                    // ./CubeSql.g:142:72: ( Digit )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==Digit) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ./CubeSql.g:142:73: Digit
                    	    {
                    	    Digit20=(Token)match(input,Digit,FOLLOW_Digit_in_cond_statement960); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);


                    tmp_con+=(Digit20!=null?Digit20.getText():null);

                    }
                    break;
                case 5 :
                    // ./CubeSql.g:143:19: sqlfield operator quote_statement quote_statement
                    {
                    pushFollow(FOLLOW_sqlfield_in_cond_statement984);
                    sqlfield();

                    state._fsp--;


                    pushFollow(FOLLOW_operator_in_cond_statement986);
                    operator21=operator();

                    state._fsp--;


                    tmp_con+="~"+(operator21!=null?input.toString(operator21.start,operator21.stop):null)+"~";

                    pushFollow(FOLLOW_quote_statement_in_cond_statement991);
                    quote_statement();

                    state._fsp--;


                    pushFollow(FOLLOW_quote_statement_in_cond_statement994);
                    quote_statement();

                    state._fsp--;


                    tmp_con+="'"+"'";

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "cond_statement"



    // $ANTLR start "boolean_expr"
    // ./CubeSql.g:146:1: boolean_expr : ( OR | AND );
    public final void boolean_expr() throws RecognitionException {
        try {
            // ./CubeSql.g:146:14: ( OR | AND )
            // ./CubeSql.g:
            {
            if ( input.LA(1)==AND||input.LA(1)==OR ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "boolean_expr"



    // $ANTLR start "quote_statement"
    // ./CubeSql.g:148:1: quote_statement : ( '\"' | '\\'' );
    public final void quote_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:148:16: ( '\"' | '\\'' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)==67||input.LA(1)==75 ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "quote_statement"


    public static class operator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "operator"
    // ./CubeSql.g:150:1: operator : ( '=' | '>=' | '<=' | '>' | '<' | 'LIKE' );
    public final CubeSqlParser.operator_return operator() throws RecognitionException {
        CubeSqlParser.operator_return retval = new CubeSqlParser.operator_return();
        retval.start = input.LT(1);


        try {
            // ./CubeSql.g:150:10: ( '=' | '>=' | '<=' | '>' | '<' | 'LIKE' )
            // ./CubeSql.g:
            {
            if ( input.LA(1)==CHILDOF||(input.LA(1) >= 70 && input.LA(1) <= 74) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "operator"



    // $ANTLR start "group_statement"
    // ./CubeSql.g:157:1: group_statement : GROUP BY grouppers ;
    public final void group_statement() throws RecognitionException {
        try {
            // ./CubeSql.g:157:17: ( GROUP BY grouppers )
            // ./CubeSql.g:157:19: GROUP BY grouppers
            {
            match(input,GROUP,FOLLOW_GROUP_in_group_statement1142); 

            match(input,BY,FOLLOW_BY_in_group_statement1144); 

            group=true;

            pushFollow(FOLLOW_grouppers_in_group_statement1148);
            grouppers();

            state._fsp--;


            group=false;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "group_statement"



    // $ANTLR start "grouppers"
    // ./CubeSql.g:159:1: grouppers : sqlfield ( comma_statement sqlfield ) ;
    public final void grouppers() throws RecognitionException {
        try {
            // ./CubeSql.g:159:11: ( sqlfield ( comma_statement sqlfield ) )
            // ./CubeSql.g:159:13: sqlfield ( comma_statement sqlfield )
            {
            tmp_con="";

            pushFollow(FOLLOW_sqlfield_in_grouppers1160);
            sqlfield();

            state._fsp--;


            if(group){ groupperlst.add(tmp_con);}

            // ./CubeSql.g:159:76: ( comma_statement sqlfield )
            // ./CubeSql.g:159:77: comma_statement sqlfield
            {
            pushFollow(FOLLOW_comma_statement_in_grouppers1165);
            comma_statement();

            state._fsp--;


            tmp_con="";

            pushFollow(FOLLOW_sqlfield_in_grouppers1169);
            sqlfield();

            state._fsp--;


            if(group)groupperlst.add(tmp_con);

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "grouppers"

    // Delegated rules


 

    public static final BitSet FOLLOW_parse_in_start131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creation_statement_in_parse156 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_sql_query_in_parse172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creation_in_creation_statement184 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_QUESTMARK_in_creation_statement186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creation_cube_in_creation193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creation_dimension_in_creation197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_create_statement_in_creation_cube206 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_related_statement_in_creation_cube208 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_measure_statement_in_creation_cube210 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_referdimension_statement_in_creation_cube212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_create_statement_in_creation_dimension220 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_related_statement_in_creation_dimension222 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_level_statement_in_creation_dimension224 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_hierarchy_statement_in_creation_dimension226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_create_statement233 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_CUBE_in_create_statement235 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_create_statement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_create_statement282 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DIMENSION_in_create_statement284 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_create_statement288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELATED_in_related_statement298 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_SQL_TABLE_in_related_statement300 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_related_statement302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REFERENCES_in_referdimension_statement313 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DIMENSION_in_referdimension_statement315 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_dimensions_in_referdimension_statement317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEASURES_in_measure_statement325 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_measures_in_measure_statement327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_level_statement334 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_OF_in_level_statement336 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEVEL_in_level_statement338 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LBRACE_in_level_statement340 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_levels_in_level_statement342 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_RBRACE_in_level_statement344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HIERARCHY_in_hierarchy_statement353 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_hierarchy_in_hierarchy_statement355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dimension_in_dimensions363 = new BitSet(new long[]{0x8000000000002002L});
    public static final BitSet FOLLOW_comma_statement_in_dimensions367 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_dimension_in_dimensions369 = new BitSet(new long[]{0x8000000000002002L});
    public static final BitSet FOLLOW_NAME_in_dimension380 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_AT_in_dimension384 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_sqlfield_in_dimension386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_measure_in_measures394 = new BitSet(new long[]{0x8000000000002002L});
    public static final BitSet FOLLOW_comma_statement_in_measures397 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_measure_in_measures399 = new BitSet(new long[]{0x8000000000002002L});
    public static final BitSet FOLLOW_NAME_in_measure409 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_AT_in_measure413 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_sqlfield_in_measure415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level_in_levels425 = new BitSet(new long[]{0x8000000000002002L});
    public static final BitSet FOLLOW_comma_statement_in_levels428 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_level_in_levels430 = new BitSet(new long[]{0x8000000000002002L});
    public static final BitSet FOLLOW_WS_in_comma_statement439 = new BitSet(new long[]{0x8000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_comma_statement442 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_WS_in_comma_statement444 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_sqlfield_in_level454 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_AS_in_level456 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_level460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sqlfield_in_level474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_hierarchy484 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CHILDOF_in_hierarchy489 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_hierarchy493 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_NAME_in_sqlfield508 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_sqlfield510 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_sqlfield514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_sqlfield528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_statement_in_sql_query546 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_from_statement_in_sql_query560 = new BitSet(new long[]{0x4000000004000000L});
    public static final BitSet FOLLOW_where_statement_in_sql_query573 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_group_statement_in_sql_query586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_select_statement604 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_grouppers_in_select_statement606 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_select_statement608 = new BitSet(new long[]{0x0100014000004100L});
    public static final BitSet FOLLOW_aggregate_statement_in_select_statement610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregate_func_in_aggregate_statement620 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_aggregate_statement622 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_sqlfield_in_aggregate_statement624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_aggregate_statement626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUM_in_aggregate_func636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVG_in_aggregate_func658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_aggregate_func681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAX_in_aggregate_func703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIN_in_aggregate_func725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_from_statement735 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_tables_in_from_statement737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_in_tables745 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_tables748 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_table_in_tables750 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_NAME_in_table762 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_AS_in_table764 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_table768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_table783 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_table787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_table799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_in_where_statement809 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_cond_statement_in_where_statement813 = new BitSet(new long[]{0x0000200000000022L});
    public static final BitSet FOLLOW_boolean_expr_in_where_statement818 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_cond_statement_in_where_statement822 = new BitSet(new long[]{0x0000200000000022L});
    public static final BitSet FOLLOW_sqlfield_in_cond_statement854 = new BitSet(new long[]{0x0000000000001000L,0x00000000000007C0L});
    public static final BitSet FOLLOW_operator_in_cond_statement856 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_sqlfield_in_cond_statement860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sqlfield_in_cond_statement881 = new BitSet(new long[]{0x0000000000001000L,0x00000000000007C0L});
    public static final BitSet FOLLOW_operator_in_cond_statement883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000808L});
    public static final BitSet FOLLOW_quote_statement_in_cond_statement888 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NAME_in_cond_statement890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000808L});
    public static final BitSet FOLLOW_quote_statement_in_cond_statement892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sqlfield_in_cond_statement914 = new BitSet(new long[]{0x0000000000001000L,0x00000000000007C0L});
    public static final BitSet FOLLOW_operator_in_cond_statement916 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000808L});
    public static final BitSet FOLLOW_quote_statement_in_cond_statement921 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_Digit_in_cond_statement924 = new BitSet(new long[]{0x0000040000200000L});
    public static final BitSet FOLLOW_NAME_in_cond_statement928 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000808L});
    public static final BitSet FOLLOW_quote_statement_in_cond_statement931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sqlfield_in_cond_statement953 = new BitSet(new long[]{0x0000000000001000L,0x00000000000007C0L});
    public static final BitSet FOLLOW_operator_in_cond_statement955 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_Digit_in_cond_statement960 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_sqlfield_in_cond_statement984 = new BitSet(new long[]{0x0000000000001000L,0x00000000000007C0L});
    public static final BitSet FOLLOW_operator_in_cond_statement986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000808L});
    public static final BitSet FOLLOW_quote_statement_in_cond_statement991 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000808L});
    public static final BitSet FOLLOW_quote_statement_in_cond_statement994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_group_statement1142 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_BY_in_group_statement1144 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_grouppers_in_group_statement1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sqlfield_in_grouppers1160 = new BitSet(new long[]{0x8000000000002000L});
    public static final BitSet FOLLOW_comma_statement_in_grouppers1165 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_sqlfield_in_grouppers1169 = new BitSet(new long[]{0x0000000000000002L});

}