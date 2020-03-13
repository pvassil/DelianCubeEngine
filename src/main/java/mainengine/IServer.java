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
package mainengine;

import java.rmi.Remote;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.rmi.RemoteException;


public interface IServer extends Remote {
    	public abstract OutputStream getOutputStream(File f) throws IOException;
    	public abstract InputStream getInputStream(File f) throws IOException;
        //public String sayHello() throws RemoteException;
}
