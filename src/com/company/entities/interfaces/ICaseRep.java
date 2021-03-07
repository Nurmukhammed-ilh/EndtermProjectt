package com.company.entities.interfaces;

import com.company.entities.Cases;

import java.util.List;

public interface ICaseRep { //list of actions with class Case

    boolean addCase(Cases cases);

    Cases getCaselId(int id);

    List<Cases> getAllCases();

    boolean disCase(int id);

    boolean fbichange(int id, int fbiid);

    Cases getAllCasesFBIf(int id);
}
