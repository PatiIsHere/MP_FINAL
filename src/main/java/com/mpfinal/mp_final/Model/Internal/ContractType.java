package com.mpfinal.mp_final.Model.Internal;

public enum ContractType {
    FULL_TIME(1.0f),
    PART_TIME(0.5f);

    private final float timeType;

    ContractType(float timeType) {
        this.timeType = timeType;
    }
}
