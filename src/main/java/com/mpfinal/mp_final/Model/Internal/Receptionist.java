package com.mpfinal.mp_final.Model.Internal;

import com.mpfinal.mp_final.Model.Base.Address;

import java.io.Serializable;
import java.time.LocalDate;

public class Receptionist extends Employee implements Serializable {
    private ContractType contractType;
    //todo po wyborze czym ma sie wyrozniac - odpowiednie atrybuty

    public Receptionist(String name, String surname, LocalDate beginningOfEmployment, LocalDate endOfEmployment, boolean higherEducation, ContractType contractType) {
        super(name, surname, beginningOfEmployment, endOfEmployment, higherEducation);
        this.contractType = contractType;
    }
//region Getters and Setters
    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
//endregion Getters and Setters

    //todo wyroznienie klienta - obecnie w uml jest metoda do wysylki sms a ograc to to masakra
}
