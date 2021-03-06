package com.company.entities.interfaces;

import com.company.entities.Cases;

public interface ICaseRep {

    boolean addCase(Cases cases);

    Cases getCaselId(int id);
}
