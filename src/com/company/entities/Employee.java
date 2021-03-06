package com.company.entities;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private int salary;
    private String position;
    private String dateAdoption;
    private String dismissalDate;
    public Employee(){
    }
    public Employee(int id,String name, String surname,String gender,int age,int salary,String position,String dateAdoption,String dismissalDate) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setAge(age);
        setSalary(salary);
        setPosition(position);
        setDateAdoption(dateAdoption);
        setDismissalDate(dismissalDate);
    }
    public Employee(String name, String surname,String gender,int age,int salary,String position,String dateAdoption,String dismissalDate) {
        setName(name);
        setSurname(surname);
        setGender(gender);
        setAge(age);
        setSalary(salary);
        setPosition(position);
        setDateAdoption(dateAdoption);
        setDismissalDate(dismissalDate);
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position=position;
    }
    public String getDateAdoption(){
        return dateAdoption;
    }
    public void setDateAdoption(String dateAdoption){
        this.dateAdoption=dateAdoption;
    }
    public String getDismissalDate(){
        return dismissalDate;
    }
    public void setDismissalDate(String dismissalDate){
        this.dismissalDate=dismissalDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", dateAdoption='" + dateAdoption + '\'' +
                ", dismissalDate='" + dismissalDate + '\'' +
                '}';
    }
}
