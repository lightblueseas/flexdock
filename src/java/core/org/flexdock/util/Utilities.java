/*
 * Created on Aug 29, 2004
 */
package org.flexdock.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



/**
 * @author marius
 */
public class Utilities {
	private static final String OS_FAMILIES_URI = "org/flexdock/util/os-families.xml";
	private static final String UNKNOWN_FAMILY = "unknown";
	private static final String FAMILY_KEY = "family";
	private static final String OS_KEY = "os";
	private static final String NAME_KEY = "name";
	public static final String OS_FAMILY = loadOSFamily();
	
	public static void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch(InterruptedException ignored) {
		}
	}
	
	public static int getInt(String data) {
		return getInt(data, 0);
	}
	
	public static int getInt(String data, int defaultValue) {
		try {
			return Integer.parseInt(data);
		} catch(Exception e) {
			return defaultValue;
		}
	}
	
	public static boolean isEmpty(String data) {
		return data==null? true: data.trim().length()==0;
	}
	
	private static String loadOSFamily() {
		Document document = ResourceManager.getDocument(OS_FAMILIES_URI);
		if(document==null)
			return UNKNOWN_FAMILY;
		
		String osName = System.getProperty("os.name");
		
		NodeList systems = document.getElementsByTagName(OS_KEY);
		for(int i=0; i<systems.getLength(); i++) {
			Element osElem = (Element)systems.item(i);
			String testName = osElem.getAttribute(NAME_KEY);
			if(osName.equals(testName)) {
				Element familyElem = (Element)osElem.getParentNode();
				return familyElem.getAttribute(NAME_KEY);
			}
		}
		return UNKNOWN_FAMILY;
	}
	
	public static Object createInstance(String className) {
		return createInstance(className, null);
	}
	
	public static Object createInstance(String className, Class superType) {
		try {
			Class c = Class.forName(className);
			if(superType!=null && !superType.isAssignableFrom(c))
				throw new ClassCastException("'" + c.getName() + "' is not a type of " + superType + ".");
			return c.newInstance();
		} catch(Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}