package com.mpfinal.mp_final.Model.System;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    public static int generateUniqueID(){
        return idGenerator.incrementAndGet();
    }
}
