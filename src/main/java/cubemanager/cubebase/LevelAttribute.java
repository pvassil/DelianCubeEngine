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

import cubemanager.starschema.Attribute;

public class LevelAttribute{
    
	/**
	 * @uml.property  name="nameLvlAttr"
	 */
	private String nameLvlAttr;
	/**
	 * @uml.property  name="moreinfos"
	 */
	private String Moreinfos;
    /**
	 * @uml.property  name="level"
	 * @uml.associationEnd  inverse="lvlAttributes:CubeMgr.CubeBase.Level"
	 */
    private Level level;
    /**
	 * @uml.property  name="attribute"
	 * @uml.associationEnd  
	 */
    private Attribute attribute;
  
    public LevelAttribute(String name,String infos){
    	setNameLvlAttr(name);
    	setMoreinfos(infos);
    }
    
	/**
	 * @return
	 * @uml.property  name="nameLvlAttr"
	 */
	public String getNameLvlAttr() {
		return nameLvlAttr;
	}

	/**
	 * @param nameLvlAttr
	 * @uml.property  name="nameLvlAttr"
	 */
	public void setNameLvlAttr(String nameLvlAttr) {
		this.nameLvlAttr = nameLvlAttr;
	}

	/**
	 * @return
	 * @uml.property  name="moreinfos"
	 */
	public String getMoreinfos() {
		return Moreinfos;
	}

	/**
	 * @param moreinfos
	 * @uml.property  name="moreinfos"
	 */
	public void setMoreinfos(String moreinfos) {
		Moreinfos = moreinfos;
	}


	/**
	 * @return
	 * @uml.property  name="level"
	 */
	public Level getLevel() {
		return level;
	}


	/**
	 * @param level
	 * @uml.property  name="level"
	 */
	public void setLevel(Level level) {
		this.level = level;
	}


	/**
	 * @param attribute
	 * @uml.property  name="attribute"
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return
	 * @uml.property  name="attribute"
	 */
	public Attribute getAttribute() {
		return attribute;
	}
    
}
