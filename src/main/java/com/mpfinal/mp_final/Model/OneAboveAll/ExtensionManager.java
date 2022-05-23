package com.mpfinal.mp_final.Model.OneAboveAll;

import java.io.*;
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

    public static boolean TESTOWO(){
        if (1==1)
            return true;
        return false;
    }
    public static boolean isExtendEmpty(){
        return allExtents.isEmpty();
    }

    public static void writeExtents(String outDirectory) {

        try {
            FileOutputStream writeData = new FileOutputStream(outDirectory);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(allExtents);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads all extents from the given stream (a utility class method).
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readExtents(String inDirectory) {
        if (new File(inDirectory).exists()) {
            try {
                FileInputStream readData = new FileInputStream(inDirectory);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                allExtents = (Map<Class, List<ExtensionManager>>) readStream.readObject();
                readStream.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
