package com.company.controllers;

import com.company.entities.Employee;
import com.company.entities.interfaces.IEmpRep;
import com.company.repositories.EmployeeRep;

import java.util.List;

public class EmployeeController {
    private final IEmpRep repo;

    public EmployeeController(EmployeeRep repo) {
        this.repo = repo;
    }

    public String addEmp(int id,String name, String surname, String gender, int age, int salary, String position, String dateAdoption, String dismissalDate) { //method to add a new employee, if we have
        Employee employee = new Employee(id,name,surname,gender,age,salary,position,dateAdoption,dismissalDate);
        boolean add = repo.addEmployee(employee);
        if (add == true) {
            return "Employee was successfully added!";
        } else
            return "Employee was not added! Something wend wrong";
    }
    public String getEmpById(int id) { //method to display employee by his id
        Employee employee=repo.getEmployeeId(id);
        if (employee != null) {
            return employee.toString();
        } else
            return "Employee was not found! Seems like he does not work here";
    }
    public String getEmpByPos(String position) { //display employee by his position(profession)
        Employee employee=repo.getEmployeePos(position);
        if (employee != null) {
            return employee.toString();
        } else
            return "Employee was not found! Seems like he does not work here";
    }
    public String disEmp(int id){ //delete employee by his id
        boolean delete=repo.disEmp(id);
        if (delete==false) {
            return "Employee was not found! Seems like he does not work here";
        } else
            return "Employee was deleted";
    }
    public String changeEmp(int cash,int id) { //method for changing an employee's salary by entering his id 
        boolean change=repo.changeEmp(cash,id);
        if (change != false) {
            return "New salary was added";
        } else
            return "Employee was not found! Seems like he does not work here";
    }

    public String promEmp(int id, String position) { //method to employee promotion
        boolean change=repo.promEmp(id,position);
        if (change != false) {
            return "Employee was promoted";
        } else
            return "Employee was not found! Seems like he does not work here";
    }
    public String getAllEmployee(){ //display a list of all employees
        List<Employee> list = repo.getAllEmployee();
        return (list.size() == 0 ? "Employers were not found!" : list.toString());
    }
    public String getAllEmployeeRel(){ //display those employees who are still working 
        List<Employee> list = repo.getAllEmployeeRel();
        return (list.size() == 0 ? "Employers were not found!" : list.toString());
    }
    public String kickEmp(String dismissaldate,int id){ //dismissal of employee by id
        boolean delete=repo.kickEmp(dismissaldate,id);
        if (delete==false) {
            return "Employee was not found! Seems like he does not work here";
        } else
            return "Employee was dismissed";
    }
}
