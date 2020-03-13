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


package exctractionmethod;

import result.Result;

//import java.sql.ResultSet;
//import java.util.TreeSet;

/**
 * The reference abstract class to represent the queries that are issued to the underlying DBMS.
 * <p>
 * Started with only one extension, {@link SQLQuery}, which is why it appears too tightly related to SQL.
 * Also carries some information from (@link Result} (which is a higher level concept, storing the data for {@link CubeQuery}).
 * 
 * @author  dgkesouli
 * @see Result
 */
public abstract class ExtractionMethod {
    
    private Result result;
    
    /**
     * MOST IMPORTANT: ALL ExtractionMethod constructors, MUST <code>new Result();</code>
     * The link between ExtractionMethod and Result is obligatory +
     * it MUST be created at the birth of the ExtractionMethod.
     * All concrete extensions of ExtractionMethod MUST call <code>super()</code>.
     * Observe also that there is NO such thing as a setResult method! 
     * 
     * @see SqlQuery
     */
    public ExtractionMethod(){
    	result = new Result();
    }

 
   public Result getResult() {
	   return result;
   }
    abstract public String toString();
    abstract public boolean compareExtractionMethod(ExtractionMethod toCompare);

    abstract public void addReturnedFields(String aggregationFuction, String attribute);
    abstract public void addFilter(String[] index);
    abstract public void addGroupers(String[] index);
    abstract public void addSourceCube(String[] index);
    

}
