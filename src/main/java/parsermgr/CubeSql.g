grammar CubeSql;

options {
  output = Java;
}

tokens{
  CREATE;CUBE;RELATED;SQL_TABLE;REFERENCES;DIMENSION;
  DIMENSION_TABLE;LIST;OF;LEVEL;AS;HIERARCHY;NAME;
  LBRACE;RBRACE;DOT;COMMA;CHILDOF;QUESTMARK;UNDERSCORE;WS;AT;
  SELECT;OR;AND;WHERE;AS;GROUP;BY;MIN;MAX;COUNT;SUM;AVG;
}

@header{
  package ParserMgr;
  
  import java.util.ArrayList;
}

@parser::members{
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
}

@lexer::header{
  package ParserMgr;
}

start :{
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
  } parse; 

parse :  ({  mode=0;
            name_creation="";
            sql_table="";
            if(dimensionlst.size()>0)  dimensionlst.clear();
            if(hierachylst.size()>0)  hierachylst.clear();
            if(originallvllst.size()>0)  originallvllst.clear();
            if(customlvllst.size()>0)  customlvllst.clear();
           }
            creation_statement )* 
   |      sql_query
   ;

creation_statement : creation QUESTMARK;

creation: creation_cube | creation_dimension ;

creation_cube : create_statement related_statement measure_statement referdimension_statement;

creation_dimension : create_statement related_statement level_statement hierarchy_statement;

create_statement: CREATE CUBE {mode=1;} NAME {name_creation=$NAME.text;}
                  | 
                  CREATE DIMENSION {mode=2;} NAME {name_creation=$NAME.text.replace("~"," ");};

related_statement : RELATED SQL_TABLE NAME {sql_table=$NAME.text;} ;

referdimension_statement : REFERENCES DIMENSION dimensions;

measure_statement : MEASURES measures;

level_statement: LIST OF LEVEL LBRACE levels RBRACE ;

hierarchy_statement : HIERARCHY hierarchy;

dimensions : dimension  (comma_statement dimension)* ;

dimension : NAME {dimensionlst.add($NAME.text.replace("~"," "));} AT sqlfield;

measures : measure (comma_statement measure)*;

measure : NAME {measurelst.add($NAME.text);mode=3;tmp_con="";} AT sqlfield {mode=1;measurefields.add(tmp_con);} ;

levels: level (comma_statement level)*;

comma_statement: WS* COMMA WS*;

level :  sqlfield AS name3=NAME {customlvllst.add($name3.text);}
        | sqlfield ;

hierarchy: name1=NAME {dimensionlst.add($name1.text);} (CHILDOF name2=NAME {dimensionlst.add($name2.text);} )+;

sqlfield : name1=NAME DOT name2=NAME { if(mode==1 || mode==2) originallvllst.add($name1.text+"."+$name2.text);
                                      tmp_con+=$name1.text+"."+$name2.text;
}
        | NAME {tmp_con+= $NAME.text;if(mode==1 || mode==2) {originallvllst.add($NAME.text);}}
        ;

sql_query: select_statement 
           from_statement
           where_statement
           group_statement;
           
select_statement: SELECT grouppers COMMA aggregate_statement;

aggregate_statement : {tmp_con="";} aggregate_func '(' sqlfield ')' {aggregatefunc=tmp_con;tmp_con="";};

aggregate_func : SUM {tmp_con=$SUM.text+"~";}
                | AVG {tmp_con=$AVG.text+"~";} 
                | COUNT {tmp_con=$COUNT.text+"~";}
                | MAX {tmp_con=$MAX.text+"~";}
                | MIN {tmp_con=$MIN.text+"~";};

from_statement : FROM tables;

tables : table (COMMA table)*;

table : name1=NAME AS name2=NAME  {tablelst.add($name1.text+"~"+$name2.text);}
      | name1=NAME name2=NAME {tablelst.add($name1.text+"~"+$name2.text);}
      | NAME {tablelst.add($NAME.text);};

where_statement : WHERE {tmp_con="";} cond_statement {conditionlst.add(tmp_con);} (boolean_expr {tmp_con="";} cond_statement {conditionlst.add(tmp_con);})*
                  | ;
cond_statement : sqlfield operator {tmp_con+="~"+$operator.text+"~";} sqlfield 
                | sqlfield operator {tmp_con+="~"+$operator.text+"~";}  quote_statement NAME quote_statement {tmp_con+="'"+$NAME.text+"'";}
                | sqlfield operator {tmp_con+="~"+$operator.text+"~";}  quote_statement (Digit)+ NAME  quote_statement {tmp_con+="'"+$Digit.text+$NAME.text+"'";}
                | sqlfield operator {tmp_con+="~"+$operator.text+"~";} (Digit)+ {tmp_con+=$Digit.text;}
                | sqlfield operator {tmp_con+="~"+$operator.text+"~";}  quote_statement  quote_statement {tmp_con+="'"+"'";};
                
                
boolean_expr : OR | AND;

quote_statement: '"' | '\'' ;

operator : '=' 
          | '>=' 
          | '<=' 
          | '>'
          | '<'
          | 'LIKE';

group_statement : GROUP BY {group=true;} grouppers {group=false;};

grouppers : {tmp_con="";} sqlfield {if(group){ groupperlst.add(tmp_con);}} (comma_statement {tmp_con="";} sqlfield {if(group)groupperlst.add(tmp_con);} );

OR : O R;

AND : A N D;

CREATE: C R E A T E;

CUBE :  C U B E ;

RELATED : R E L A T E D;

SQL_TABLE: S Q L UNDERSCORE T A B L E ;

REFERENCES: R E F E R E N C E S;

DIMENSION : D I M E N S I O N ;

DIMENSION_TABLE: D I M E N S I O N UNDERSCORE T A B L E;

LIST: L I S T;

OF: O F;

LEVEL:L E V E L ;

AS: A S;

AT :A T;

HIERARCHY: H I E R A R C H Y;

SELECT : S E L E C T;

AVG : A V G;

SUM : S U M;

MAX : M A X;

MIN : M I N;

COUNT : C O U N T;

WHERE : W H E R E;

FROM : F R O M;

GROUP : G R O U P;

BY: B Y;

MEASURES : M E A S U R E S;

NAME: Letter (Letter | Digit | '_'  | '-' | '~' )*;

LBRACE:'{';
RBRACE:'}';
DOT:'.';
COMMA:',';
CHILDOF:'>';
QUESTMARK:';';
UNDERSCORE:'_';

fragment Digit :  '0'..'9';
fragment Letter :  'a'..'z' |  'A'..'Z';
fragment A:'A'|'a';
fragment B:'B'|'b';
fragment C:'C'|'c';
fragment D:'D'|'d';
fragment E:'E'|'e';
fragment F:'F'|'f';
fragment G:'G'|'g';
fragment H:'H'|'h';
fragment I:'I'|'i';
fragment J:'J'|'j';
fragment K:'K'|'k';
fragment L:'L'|'l';
fragment M:'M'|'m';
fragment N:'N'|'n';
fragment O:'O'|'o';
fragment P:'P'|'p';
fragment Q:'Q'|'q';
fragment R:'R'|'r';
fragment S:'S'|'s';
fragment T:'T'|'t';
fragment U:'U'|'u';
fragment V:'V'|'v';
fragment W:'W'|'w';
fragment X:'X'|'x';
fragment Y:'Y'|'y';
fragment Z:'Z'|'z';

WS : ( 
    (' ' | '\t' | '\f')+
  |
    // handle newlines
    ( '\r\n'  // DOS/Windows
      | '\r'    // Macintosh
      | '\n'    // Unix
    )
    )
 { $channel = HIDDEN; };