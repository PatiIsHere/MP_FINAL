package com.mpfinal.mp_final.Model.OneAboveAll;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ExtensionManager implements Serializable {
    private static Map<Class, List<ExtensionManager>> allExtents = new Hashtable<>();

    public ExtensionManager() {
        List extent = null;
        Class theClass = this.getClass();

        if(allExtents.containsKey(theClass)) {
            // An extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else {
            // An extent does not exist - create a new one
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }

        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    /**
     * Reads all extents from the given stream (a utility class method).
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allExtents = (Hashtable) stream.readObject();
    }

    /**
     * Shows an extent of the given class (a utility class method)
     * @throws Exception
     */
    public static void showExtent(Class theClass) throws Exception {
        List extent = null;

        if(allExtents.containsKey(theClass)) {
            // Extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else {
            throw new Exception("Unknown class " + theClass);
        }

        System.out.println("Extent of the class: " + theClass.getSimpleName());

        for(Object obj : extent) {
            System.out.println(obj);
        }
    }

    /**
     * Gets the extent of the given class.
     * @param type
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if(allExtents.containsKey(type)) {
            return (Iterable<T>) allExtents.get(type);
        }

        throw new ClassNotFoundException(String.format("%s. Stored extents: %s", type.toString(), allExtents.keySet()));
    }
}
