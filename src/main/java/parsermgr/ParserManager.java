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


package parsermgr;


import java.util.ArrayList;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

/**
 * The main parsing class that returns the parts of the parsed string.
 * 
 * @author D. Gkesoulis
 * @version 0.0.1
 * @since v0.0.0 (the Cincecubes system) 
 *
 */
public class ParserManager {
	
	/**
	 * @uml.property  name="mode"
	 */
	public Integer mode;
	/**
	 * @uml.property  name="name_creation"
	 */
	public String name_creation;
	/**
	 * @uml.property  name="sqltable"
	 */
	public String sqltable;
	/**
	 * @uml.property  name="dimensionlst"
	 */
	public ArrayList<String> dimensionlst;
	/**
	 * @uml.property  name="hierachylst"
	 */
	public ArrayList<String> hierachylst;
	/**
	 * @uml.property  name="originallvllst"
	 */
	public ArrayList<String> originallvllst;
	/**
	 * @uml.property  name="customlvllst"
	 */
	public ArrayList<String> customlvllst;
	/**
	 * @uml.property  name="conditionlst"
	 */
	public ArrayList<String> conditionlst;
	/**
	 * @uml.property  name="tablelst"
	 */
	public ArrayList<String> tablelst;
	/**
	 * @uml.property  name="groupperlst"
	 */
	public ArrayList<String> groupperlst;
	/**
	 * @uml.property  name="measurelst"
	 */
	public ArrayList<String> measurelst;
	/**
	 * @uml.property  name="measurefields"
	 */
	public ArrayList<String> measurefields;
	/**
	 * @uml.property  name="aggregatefunc"
	 */
	public String aggregatefunc;
	
	/**
	 * Simply initializes the ArrayLists holding information for dimensions and queries.
	 * 
	 *  @author D. Gkesoulis
	 *  @since v0.0.0 (the Cincecubes system) 
	 */
	public ParserManager() {
		dimensionlst=new ArrayList<String>();
		hierachylst=new ArrayList<String>();
		originallvllst=new ArrayList<String>();
		customlvllst=new ArrayList<String>();
		conditionlst=new ArrayList<String>();
		tablelst=new ArrayList<String>();
		groupperlst=new ArrayList<String>();
		measurelst=new ArrayList<String>();
		measurefields=new ArrayList<String>();
		
	}
	
	/**
	 * Returns void, yet it initializes the appropriate fields of this class.
	 * 
	 *  @author D. Gkesoulis
	 *  @see CubeSqlLexer
	 *  @see CubeSqlParser
	 *  @since v0.0.0 (the Cincecubes system) 
	 */

	public void parse(String toParse){
		CharStream stream =	new ANTLRStringStream(toParse);
		CubeSqlLexer lexer = new CubeSqlLexer(stream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		CubeSqlParser parser = new CubeSqlParser(tokenStream);
		dimensionlst.clear();
		hierachylst.clear();
		originallvllst.clear();
		customlvllst.clear();
		try {
			parser.start();	
			
			/*For DIMENSION only*/
			hierachylst.addAll(parser.hierachylst);
			
			/*For CUBE only*/
			measurefields.addAll(parser.measurefields);
			measurelst.addAll(parser.measurelst);
			
			/*SHARED CUBE,DIMENSION */
			dimensionlst.addAll(parser.dimensionlst);
			originallvllst.addAll(parser.originallvllst);
			customlvllst.addAll(parser.customlvllst);
			mode=parser.mode;
			name_creation=parser.name_creation;
			sqltable=parser.sql_table;
			
			/* SQL QUERY STAFF */ 
			aggregatefunc=parser.aggregatefunc;
			conditionlst.addAll(parser.conditionlst);
			tablelst.addAll(parser.tablelst);
			groupperlst.addAll(parser.groupperlst);
		} catch (RecognitionException e) {
			e.printStackTrace();
		}

	}

}
