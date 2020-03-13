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

package result;

public class ModelFactory {

	public ModelFactory() {
		;
	}

	public AbstractModel generateModel(String modelType, Result aResult) {
		switch(modelType) {
			case "Rank":	return new RankModel(aResult); //break;
			case "Outlier":	return new OutlierModel(aResult); //break;
			case "KMeansApache":	return new KmeansApache(aResult); //break;
			case "KPIMedianBased":	return new KPIMedianBasedModel(aResult); //break;
			case "loan_KPIdemo_SouthBoh_YR_Status":	return new KPIModel_SouthBoh_YR_Status(aResult); //break;
			default:	System.err.println("MODEL FACTORY: Missed the generation of: " + modelType);	return null; //break;
		}
	}
}//end class
