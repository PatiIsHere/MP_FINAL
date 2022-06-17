package com.mpfinal.mp_final.Model.System;

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
}
