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

package client.gui.utils;

import javafx.stage.Stage;
/**
 * For the moment, simply closes the caller stage
 * <p>
 * Must check to see whether there are more stuff to add
 * 
 * @author pvassil
 *
 */
public class ExitController {
	private Stage stage;
	
	public ExitController(Stage callerStage) {
		stage = callerStage;
	}
	
	public void exit() {
		stage.close();
	}
}
