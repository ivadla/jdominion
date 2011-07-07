package org.jdominion.remote;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIHandler {

	private static boolean alreadyInitialized = false;

	public static void initializeRMI() {

		if (alreadyInitialized) {
			return;
		}

		// Create and install a security manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security manager installed.");
		} else
			System.out.println("Security manager already exists.");

		try // special exception handler for registry creation
		{
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			// do nothing, error means registry already exists
			System.out.println("java RMI registry already exists.");
		}

		alreadyInitialized = true;

	}
}
