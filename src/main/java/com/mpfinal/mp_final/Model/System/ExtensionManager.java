package com.mpfinal.mp_final.Model.System;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ExtensionManager implements Serializable {
    private static Map<Class, List<ExtensionManager>> allExtents = new Hashtable<>();

    private int id;
    public ExtensionManager() {
        id = IDGenerator.generateUniqueID();

        List extent = null;
        Class theClass = this.getClass();

        if(allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        }
        else {
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }

        extent.add(this);
    }

    public int getId(){
        return this.id;
    }

    public static boolean isExtendEmpty(){
        return allExtents.isEmpty();
    }

    /**
     * Saves entire extend map to provided directory.
     * @param outDirectory String
     */
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
     * Recreates extension map from provided directory.
     * @param inDirectory String
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
     * Returns all object of provided class type.
     * @param type Object.class
     * @param <T> T
     * @return T
     * @throws ClassNotFoundException when class does not extists
     */
    public static <T> ArrayList<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if(allExtents.containsKey(type)) {
            return (ArrayList<T>) allExtents.get(type);
        }

        throw new ClassNotFoundException(String.format("%s. Stored extents: %s", type.toString(), allExtents.keySet()));
    }
}
