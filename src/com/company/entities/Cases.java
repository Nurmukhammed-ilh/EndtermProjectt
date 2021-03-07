package com.company.entities;

public class Cases { //class creation
    private int investCase;
    private String name;
    private int idCase;

    //instructors
    public Cases() {
    }
    public Cases(int idCase, String name, int investCase) {
         setIdCase(idCase);
         setName(name);
         setInvestCase(investCase);
    }
    public Cases(String name, int investCase) {
        setName(name);
        setInvestCase(investCase);
    }
//get-set - to access this class and assign values
    public int getIdCase() {
        return idCase;
    }

    public void setIdCase(int idCase) {
        this.idCase = idCase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInvestCase() {
        return investCase;
    }

    public void setInvestCase(int investCase) {
        this.investCase = investCase;
    }

    public String caseInfo(){
        return "FBI employee with id"+ getInvestCase()+"is investigating the case" +getName()+" with ID "+getIdCase();
    }

    @Override
    public String toString() {
        return "Cases{" +
                "investCase=" + investCase +
                ", name='" + name + '\'' +
                ", idCase=" + idCase +
                '}';
    }
}
