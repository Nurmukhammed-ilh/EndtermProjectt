package com.company.controllers;

import com.company.entities.Cases;
import com.company.entities.Criminals;
import com.company.entities.interfaces.ICaseRep;
import com.company.repositories.CaseRep;

public class CaseController {
    private final ICaseRep repo;

    public CaseController(CaseRep repo) {
        this.repo = repo;
    }

    public String addCase(int idCase, String name, int investCase){
        Cases cases=new Cases(idCase,name,investCase);
        boolean add = repo.addCase(cases);
        if (add == true) {
            return "Case was successfully added!";
        } else
            return "Case was not added! Something went wrong";
    }
    public String getCaseById(int id) {
        Cases cases=repo.getCaselId(id);
        if (cases != null) {
            return cases.toString();
        } else
            return "Case was not found!";
    }

}
