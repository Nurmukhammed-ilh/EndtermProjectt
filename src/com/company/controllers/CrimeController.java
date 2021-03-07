package com.company.controllers;


import com.company.entities.Criminals;
import com.company.entities.Employee;
import com.company.entities.interfaces.ICrimeRep;
import com.company.repositories.CrimeRep;

import java.util.List;

public class CrimeController<wanted> {
    private final ICrimeRep repo;

    public CrimeController(CrimeRep repo){
        this.repo=repo;
    }

    public String addCrime(int CriId, String CriName, String article, int CriAge, boolean wanted){ //method to add a new criminal
        Criminals criminals = new Criminals(CriId,CriName,article,CriAge,wanted);
        boolean add = repo.addCriminal(criminals);
        if (add == true) {
            return "Criminal was successfully added!";
        } else
            return "Criminal was not added! Something wend wrong";
    }

    public String getCrimeById(int id) { //display a criminal by his id
        Criminals criminals=repo.getCriminalId(id);
        if (criminals != null) {
            return criminals.toString();
        } else
            return "Criminals was not found!";
    }

    public String getCrimeByCri(String article){ //display a criminals by their articles(cases)
        List<Criminals> list = repo.getAllCriCri(article);
        return (list.size() == 0 ? "Criminals were not found!" : list.toString());
    }
    public String getAllCriminal(){ //display a list of all criminals
        List<Criminals> list = repo.getAllCriminal();
        return (list.size() == 0 ? "Criminals were not found!" : list.toString());
    }
    public String crichange(int id, boolean wanted) { //method to change criminals status(wanted or not)
        boolean change=repo.crichange(id,wanted);
        if (change != false) {
            return "New wanted status was added";
        } else
            return "Criminal was not found!";
    }

    public String criage(int age){ //method for finding criminals, whose age higher than entered
        List<Criminals> list = repo.criage(age);
        return (list.size() == 0 ? "There are no criminals in the database older than that age!" : list.toString());
    }
}
