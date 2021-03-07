package com.company.controllers;

import com.company.entities.Cases;
import com.company.entities.Employee;
import com.company.entities.interfaces.ICaseRep;
import com.company.repositories.CaseRep;

import java.util.List;

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
    public String getAllCases(){
        List<Cases> list = repo.getAllCases();
        return (list.size() == 0 ? "Cases were not found!" : list.toString());
    }
    public String disCase(int id){
        boolean delete=repo.disCase(id);
        if (delete==false) {
            return "Case was not found! Seems like it does not exist";
        } else
            return "The case was successfully deleted.";
    }
    public String fbichang(int id,int fbiid) {
        boolean change=repo.fbichange(id,fbiid);
        if (change != false) {
            return "A new FBI official has been appointed to investigate the case!";
        } else
            return "The case was not found!";
    }
    public String getAllCa(int id){
        Cases cases=repo.getAllCasesFBIf(id);
        if (cases != null) {
            return cases.toString();
        } else
            return "Case was not found!";
    }
}


