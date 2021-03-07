package com.company.entities.interfaces;

import com.company.entities.Employee;

import java.util.List;

public interface IEmpRep { //list of actions with class Employee
    boolean addEmployee(Employee employee);
    Employee getEmployeeId(int id);
    Employee getEmployeePos(String position);
    boolean disEmp(int id);
    boolean changeEmp(int cash, int id);
    boolean promEmp(int id, String position);
    List<Employee> getAllEmployee();
    List<Employee> getAllEmployeeRel();

    boolean kickEmp(String dismissaldate, int id);
}
