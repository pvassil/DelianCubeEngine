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


package cubemanager.cubebase;

import java.util.ArrayList;

public class Level {
    /**
	 * @uml.property  name="hierarchy"
	 * @uml.associationEnd  inverse="lvls:CubeMgr.CubeBase.Hierarchy"
	 */
    private Hierarchy hierarchy;
    /**
	 * @uml.property  name="lvlAttributes"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="level:CubeMgr.CubeBase.LevelAttribute"
	 */
    private ArrayList<LevelAttribute> lvlAttributes;
    /**
	 * @uml.property  name="id"
	 */
    private Integer id;
    /**
	 * @uml.property  name="name"
	 */
    private String name;

    public String getAttributeName(int i){
    	return lvlAttributes.get(i).getAttribute().getName();
    }
    
    public String getName() {
    	return name;
    }
    
    public Level(Integer position,String nm,Hierarchy Hier){
    	id=position;
    	name=nm;
    	lvlAttributes=new ArrayList<LevelAttribute>();
    	setHierarchy(Hier);
    }

    public Level(Integer position,String nm){
    	id=position;
    	name=nm;
    	lvlAttributes=new ArrayList<LevelAttribute>();
    }
        
    public void setLevelAttribute(ArrayList<LevelAttribute> levelAttributes){
    	this.lvlAttributes=levelAttributes;
    }

	public Hierarchy getLinearHierarchy() {
		return hierarchy;
	}

	/**
	 * @param hier
	 * @uml.property  name="hierarchy"
	 */
	public void setHierarchy(Hierarchy hier) {
		hierarchy = hier;
	}

	public void addLevelAttribute(LevelAttribute lvlattribute) {
		lvlAttributes.add(lvlattribute);
	}
}
