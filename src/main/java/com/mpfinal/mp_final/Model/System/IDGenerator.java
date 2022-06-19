package com.mpfinal.mp_final.Model.System;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    /**
     * Static method used to create unique ID at the level of entire program.
     * @return int
     */
    public static int generateUniqueID(){
        return idGenerator.incrementAndGet();
    }

    public static void setIdGeneratorAfterRecreatingExtension(Map<Class, List<ExtensionManager>> allExtension){
        int maxId = 0;
        for (List<ExtensionManager> allExtensionValues: allExtension.values()) {

                 for (ExtensionManager allExtensionValue : allExtensionValues) {
                     if (maxId < allExtensionValue.getId()){
                         maxId = allExtensionValue.getId();
                 }
            }
        }
        idGenerator.addAndGet(maxId);
    }
}
