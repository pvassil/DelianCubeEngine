/**
 * 
 */
package cubemanager;

import cubemanager.cubebase.CubeQuery;

/**
 * Responsible for converting a <i>logical</i> {@link CubeQuery} to a physical materialization of a {@link IExtractionMethod}, e.g., a {@link SqlQuery}.
 * 
 * @author pvassil
 * @since v.0.1
 * 
 */
public interface ICubeQueryTranslator {
	public String produceExtractionMethod(CubeQuery cubeQuery);
}
