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

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import setup.ModeOfWork;
import setup.ModeOfWork.WorkMode;

/**
 * This is the class that represents the production of a KMeans clustering of the results of a query, based on their measure values.
 * 
 * Based on the algorithm provided by apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
 * 
 * @author pvassil
 * @since v.0.2
 */
public class KmeansApache extends AbstractModel {

	/**
	 * @param aResult the Result to which the KMeans is applied
	 */
	public KmeansApache(Result aResult) {
		super(aResult);
	}//end param. constructor

	/* (non-Javadoc)
	 * @see result.AbstractModel#compute()
	 */
	@Override
	public int compute() {
		//int numOfMeasureValues = 0;
		List<CellWrapper> clusterInput = setupClusterInput(this.result);
		//numOfMeasureValues = clusterInput.size();
		List<CentroidCluster<CellWrapper>> clusterResults = computeClusters(clusterInput);
		clustersOfCells = markCellsWithClusters(clusterResults, clusterInput, this);

		int numOfMeasureValues = clusterInput.size();
		int numOfClusters = clusterResults.size();
		 membershipMatrix = createCellToClusterMembershipArray(clustersOfCells, numOfMeasureValues, numOfClusters);
		
		return 0;
	}//end compute

	/**
	 * Converts the Cell objects of the input Result to a List of CellWrapper objects, so that the K-Means clustering of Apache can work
	 * 
	 * The basic idea is that K-Means by Apache operates only on objects implementing the Clusterable interface, thus we need the class CellWrapper
	 * 
	 * @param res The Result object containing the Cell objects with the measures
	 * @return A List of CellWrapper objects, one per input Cell 
	 */
	private static List<CellWrapper> setupClusterInput(Result res) {
		List<Cell> cells = res.getCells();
		List<CellWrapper> clusterInput = new ArrayList<CellWrapper>(cells.size());
		for (Cell cell : cells)
		    clusterInput.add(new CellWrapper(cell));
		return clusterInput;
	}//end method setupClusterInput
	
	/**
	 * Clusters the input measure values in Clusters
	 * 
	 * @param clusterInput a List of CellWrapper objects, one per input Cell, produced by the steupClusterInput method
	 * @return a List o CentroidCluster of CellWrappers, i.e., the result of the K-Means
	 */
	private static List<CentroidCluster<CellWrapper>> computeClusters(List<CellWrapper> clusterInput) {
		KMeansPlusPlusClusterer<CellWrapper> clusterer = new KMeansPlusPlusClusterer<CellWrapper>(NUM_CLUSTERS, NUM_ITERATIONS );
		List<CentroidCluster<CellWrapper>> clusterResults = clusterer.cluster(clusterInput);
		
		return clusterResults;
	}//end method computeClusters
	
	/**
	 * Returns  String "KMeansApache"
	 * @see result.AbstractModel#getModelName()
	 */
	@Override
	public String getModelName() {
		return "KMeansApache";
	}

	private static int [] markCellsWithClusters(List<CentroidCluster<CellWrapper>> clusterResults, List<CellWrapper> clusterInput, KmeansApache callerModel) {
		HashMap<Double, Integer> cellClusters = new HashMap<Double, Integer>();
		
		//For each cluster, create a component and add its values to a hashmap
		for (int i=0; i<clusterResults.size(); i++) {
			//decide cluster name, new a component and add it to the components of the model 
			String currentClusterName = "Cluster " + i;
			if ((ModeOfWork.mode == WorkMode.DEBUG_GLOBAL)||(ModeOfWork.mode == WorkMode.DEBUG_MODEL)) {
				System.out.println(currentClusterName);
			}
		    KMeansApacheComponent currentComponent = new KMeansApacheComponent(currentClusterName, callerModel);
		    callerModel.components.put(currentClusterName, currentComponent);
		    
		    for (CellWrapper cellWrapper : clusterResults.get(i).getPoints()) {
		    	cellClusters.put(cellWrapper.getCell().toDouble(), i);
				if ((ModeOfWork.mode == WorkMode.DEBUG_GLOBAL)||(ModeOfWork.mode == WorkMode.DEBUG_MODEL)) {
					System.out.println(cellWrapper.getCell().toDouble());
				}
		    }
			if ((ModeOfWork.mode == WorkMode.DEBUG_GLOBAL)||(ModeOfWork.mode == WorkMode.DEBUG_MODEL)) {
				System.out.println();
			}
		}
		
		int size = clusterInput.size();
		int [] clustersOfCells = new int [size];
		for (int i = 0; i < size; i++) {
			Double lookupValue = clusterInput.get(i).getCell().toDouble();
			Integer clusterId = cellClusters.get(lookupValue);
			clustersOfCells[i] = clusterId.intValue();
			if ((ModeOfWork.mode == WorkMode.DEBUG_GLOBAL)||(ModeOfWork.mode == WorkMode.DEBUG_MODEL)) {
				System.out.println(lookupValue + " at " + clustersOfCells[i]);
			}
		}
		
		return clustersOfCells;
	}
	
	private int[][] createCellToClusterMembershipArray(int [] cellClusterIds, int numOfMeasureValues, int numOfClusters ) {
		if(cellClusterIds.length != numOfMeasureValues)
			return null;
		
		int [][] cellToClusterMembershipArray =  new int[numOfMeasureValues][numOfClusters];
		membershipMatrixLabels =  new String [numOfMeasureValues+1][numOfClusters];
		for (int j = 0; j < numOfClusters; j++)
			membershipMatrixLabels[0][j] = "Cluster " + j; //first line of labels are the headers
		for(int i = 0; i < numOfMeasureValues; i++) {
			for (int j = 0; j < numOfClusters; j++) {
				if (cellClusterIds[i] == j) { 
					cellToClusterMembershipArray[i][j] = 1;
					membershipMatrixLabels[i+1][j] = "1";				
				}
				else { 
					cellToClusterMembershipArray[i][j] = 0;
					membershipMatrixLabels[i+1][j] = "0";
				}//else
			}//inner for
		}//outer for
		return cellToClusterMembershipArray;
	}
	/* (non-Javadoc)
	 * @see result.AbstractModel#printAs2DStringArray()
	 */
	@Override
	public String[][] printAs2DStringArray() {
		return membershipMatrixLabels;
	}//end print as 2DString

	@Override
	public String getInfoContent() {
		String result = this.getModelName() + "\n-------------------------\n\n"
				+ "We run K-Means provided by Apache Commons Math for the results of the query.\n"  
				+ "We have created " + NUM_CLUSTERS + " clusters for " + clustersOfCells.length + " cells.\n"
				+ "Each column of the result pertains to another cluster of the result set, with each row referring to the respective cell of the query result";
		
		return result;
	}//end method getInfoContent()
	
	private String [] [] membershipMatrixLabels;
	private int [] []  membershipMatrix;
	private int [] clustersOfCells;
	private static int NUM_CLUSTERS = 3;
	private static int NUM_ITERATIONS = 10000;
	

}//end class
