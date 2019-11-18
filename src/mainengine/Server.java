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

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import mainengine.SimpleQueryProcessorEngine;
import mainengine.IMainEngine;

public class Server {
	private static final int PORT = 2020;
	private static Registry registry;

	public static void startRegistry() throws RemoteException {
		// Create server registry
		registry = LocateRegistry.createRegistry(PORT);
	}

	public static void registerObject(String name, Remote remoteObj)
			throws RemoteException, AlreadyBoundException {
		// Bind the object in the registry.  It is bind with certain name.
		// Client will lookup on the registration of the name to get object.
		registry.bind(name, remoteObj);
		System.out.println("Registered: " + name + " -> "
				+ remoteObj.getClass().getName() + "[" + remoteObj + "]");
	}


	/**
	 * For the moment, just a message and system exit
	 */
	private static void stopServer() {
		System.out.println("Server stopped!");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Server starting...");
		startRegistry();
		registerObject(IMainEngine.class.getSimpleName(), new SimpleQueryProcessorEngine());
		
		//registerObject(Foo.class.getSimpleName(), new Foo());
		
		// Server has started, and is listening to the request from the clients
		System.out.println("Server started!");
		//Default stop after 2 hour, if not stopped explicitly
		TimeUnit.SECONDS.sleep(7200);
		stopServer();
	}
}
