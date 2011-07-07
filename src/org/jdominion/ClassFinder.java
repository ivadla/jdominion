package org.jdominion;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.jdominion.aiStrategies.IStrategy;
import org.jdominion.gui.HumanStrategy;
import org.jdominion.remote.RemoteStrategy;

public class ClassFinder {

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and subpackages.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private static List<Class> getClasses(String packageName) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			String fileName = resource.getFile();
			String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
			dirs.add(new File(fileNameDecoded));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private static List<Class> findClasses(File directory, String packageName) {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				assert !fileName.contains(".");
				classes.addAll(findClasses(file, packageName + "." + fileName));
			} else if (fileName.endsWith(".class") && !fileName.contains("$")) {
				try {
					Class _class;
					try {
						_class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
					} catch (ExceptionInInitializerError e) {
						// happen, for example, in classes, which depend on
						// Spring to inject some beans, and which fail,
						// if dependency is not fulfilled
						_class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6), false,
								Thread.currentThread().getContextClassLoader());
					}
					classes.add(_class);
				} catch (ClassNotFoundException e) {
					// TODO: implement real error output
					System.err.println("Error loading class from file " + fileName);
				}
			}
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	public static List<Class<? extends Card>> findAllKingdomCards() {
		List<Class<? extends Card>> kingdomCards = new ArrayList<Class<? extends Card>>();
		List<Class> classesInCardsPackage;
		try {
			classesInCardsPackage = getClasses("org.jdominion.cards");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (Class classInCardsPackage : classesInCardsPackage) {
			if (classInCardsPackage.getSuperclass() == Card.class && !Modifier.isAbstract(classInCardsPackage.getModifiers())) {
				try {
					if (CardClassInfo.getInstance().isKingdomCard(classInCardsPackage)) {
						kingdomCards.add((Class<? extends Card>) classInCardsPackage);
					}
				} catch (Exception e) {
					// TODO: implement real error output
					System.err.println("Could not load card-class " + classInCardsPackage.getName());
					e.printStackTrace();
				}

			}
		}
		return kingdomCards;
	}

	@SuppressWarnings("unchecked")
	public static List<Class<? extends IStrategy>> findStrategies() {
		List<Class<? extends IStrategy>> strategies = new ArrayList<Class<? extends IStrategy>>();

		List<Class> classesInStrategyPackage;
		try {
			classesInStrategyPackage = getClasses("org.jdominion.aiStrategies");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (Class classInStrategyPackage : classesInStrategyPackage) {
			if (IStrategy.class.isAssignableFrom(classInStrategyPackage) && !classInStrategyPackage.isInterface()
					&& !Modifier.isAbstract(classInStrategyPackage.getModifiers())) {
				strategies.add(classInStrategyPackage);
			}
		}

		// TODO: implement a way to find human players during runtime
		strategies.add(HumanStrategy.class);
		strategies.add(RemoteStrategy.class);

		return strategies;
	}

}
