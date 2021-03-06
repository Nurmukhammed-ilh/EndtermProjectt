package com.company.entities.interfaces;

import com.company.entities.Criminals;

import java.util.List;

public interface ICrimeRep {
    boolean addCriminal(Criminals criminals);
    Criminals getCriminalId(int id);
    List<Criminals> getAllCriCri(String article);
    List<Criminals> getAllCriminal();
    boolean crichange(int id, boolean wanted);

    List<Criminals> criage(int age);
}
