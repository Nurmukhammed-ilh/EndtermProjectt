package com.company;

import com.company.controllers.CaseController;
import com.company.controllers.CrimeController;
import com.company.controllers.EmployeeController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.entities.Employee;
import com.company.repositories.CaseRep;
import com.company.repositories.CrimeRep;
import com.company.repositories.EmployeeRep;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private Scanner scanner = new Scanner(System.in);
    private IDB db = new PostgresDB();
    private EmployeeRep employeeRep = new EmployeeRep(db);
    private CaseRep caseRep = new CaseRep(db);
    private CrimeRep crimeRep = new CrimeRep(db);
    CrimeController crimeController = new CrimeController(crimeRep);
    EmployeeController employeeController = new EmployeeController(employeeRep);
    CaseController caseController = new CaseController(caseRep);


    public void start() {
        while (true) {//while that is not false
            System.out.println("   What do you want to do?");
            System.out.println("1. Operation with FBI employee");
            System.out.println("2. Operation with cases");
            System.out.println("3. Operation with criminals");
            System.out.println("4. Exit");
            try {//users can choose what they want to do with it
                int option = scanner.nextInt();
                if (option == 1) {
                    consoleFBI();
                }//Search for any medicine by name
                if (option == 2) {
                    consoleCases();
                }
                if (option == 3) {
                    consoleCriminal();
                }
                if (option == 4) {
                    System.exit(0);
                }
                else {
                    break;//program is gonna finish if user push "wrong" button
                }
            } catch (InputMismatchException e) {
                System.out.println("Error!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void consoleFBI() {
        while (true) {
            System.out.println("   What do you want to do?");
            System.out.println("1. Add new employee to Database");
            System.out.println("2. Get information about an FBI employee by ID");
            System.out.println("3. Get FBI employee's information by position");
            System.out.println("4. Delete all info about an FBI employee in database");
            System.out.println("5. Change salary of FBI employee by ID");
            System.out.println("6. Promote FBI employee by ID");
            System.out.println("7. Get list of all of employees who have ever worked in FBI");
            System.out.println("8. Get list of all of employees who still work in FBI");
            System.out.println("9. Dismiss an FBI employee");
            System.out.println("10. Back to menu");
            try {
                int option1 = scanner.nextInt();
                if (option1 == 1) {
                    consoleFBIadd();
                }
                if (option1 == 2) {
                    consoleFBIid();
                }
                if (option1 == 3) {
                    consoleFBIpos();
                }
                if (option1 == 4) {
                    consoleFBIdis();
                }
                if (option1 == 5) {
                    consoleFBIchange();
                }
                if (option1 == 6) {
                    promoteEmployee();
                }
                if (option1 == 7){
                    getAllEmp();
                }
                if (option1 == 8){
                    getAllEmpRel();
                }
                if(option1==9){
                    kickEmp();
                }
                if(option1==10){
                    start();
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void consoleFBIadd() {
        System.out.println("Adding new FBI employee to database: ");
        System.out.println("Enter id of employee");
        int id = scanner.nextInt();
        System.out.println("Enter name of employee");
        String name = scanner.next();
        System.out.println("Enter surname of employee");
        String surname = scanner.next();
        System.out.println("Enter gender of employee");
        String gender = scanner.next();
        System.out.println("Enter age of employee");
        int age=scanner.nextInt();
        System.out.println("Enter salary of employee");
        int salary= scanner.nextInt();
        System.out.println("Enter position of employee");
        String position=scanner.next();
        System.out.println("Enter Adoption date of employee");
        String dateAdoption=scanner.next();
        System.out.println("Enter dismissal Date of employee,if he does not work in FBI anymore");
        String dismissalDate=scanner.next();
        String response = employeeController.addEmp(id,name,surname,gender,age,salary,position,dateAdoption,dismissalDate);
        System.out.println(response);
    }
 public void consoleFBIid(){
     System.out.println("Getting FBI employee by ID:");
     System.out.println("Enter id of employee: ");
     int id= scanner.nextInt();
     String response = employeeController.getEmpById(id);
     System.out.println(response);
 }
    public void consoleFBIpos() {
        System.out.println("Getting FBI employee by position:");
        System.out.println("Enter position of employee: ");
        String position = scanner.next();
        String response = employeeController.getEmpByPos(position);
        System.out.println(response);
    }
    public void consoleFBIdis(){
        System.out.println("Deleting FBI employee's information by id:");
        System.out.println("Enter id of employee: ");
        int id = scanner.nextInt();
        String response = employeeController.disEmp(id);
        System.out.println(response);
    }
    public void consoleFBIchange(){
        System.out.println("Changing salary of FBI employee by id:");
        System.out.println("Enter new salary of employee: ");
        int cash=scanner.nextInt();
        System.out.println("Enter id of employee");
        int id = scanner.nextInt();
        String response = employeeController.changeEmp(cash,id);
        System.out.println(response);
    }
    public void promoteEmployee(){
        System.out.println("Promoting FBI employee by id: ");
        System.out.println("Enter id of employee: ");
        int id = scanner.nextInt();
        System.out.println("Enter new position of employee");
        String position=scanner.next();
        String response = employeeController.promEmp(id,position);
        System.out.println(response);
    }
    public void getAllEmp(){
        System.out.println("Getting list of all employee that ever worked here...");
        String response = employeeController.getAllEmployee();
        System.out.println(response);
    }
    public void getAllEmpRel(){
        System.out.println("Getting list of all employee that still work in FBI...");
        String response = employeeController.getAllEmployeeRel();
        System.out.println(response);
    }
    public void kickEmp(){
        System.out.println("Enter current date (dd.mm.yyyy)");
        String dismissaldate=scanner.next();
        System.out.println("Enter ID of employee to dismiss");
        int id=scanner.nextInt();
        String response= employeeController.kickEmp(dismissaldate,id);
        System.out.println(response);
    }
    public  void consoleCriminal(){
        while (true) {
            System.out.println("   What do you want to do?");
            System.out.println("1. Add new criminal to Database");
            System.out.println("2. Get criminal's information by id");
            System.out.println("3. Get list of Criminals by crime");
            System.out.println("4. Get list of all Criminals in database");
            System.out.println("5. Change the status of a wanted criminal");
            System.out.println("6. Get criminals over a certain age");
            System.out.println("7. Back to menu");
            try {
                int option2 = scanner.nextInt();
                if (option2 == 1) {
                    consoleCrimadd();
                }
                if (option2 == 2){
                    consoleCrimeid();
                }
                if (option2 == 3){
                    consoleCrimeCri();
                }
                if (option2 == 4){
                    getAllCri();
                }
                if (option2 == 5){
                    crichange();
                }
                if (option2 == 6){
                    criage();
                }
                if (option2 == 7){
                    start();
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void consoleCrimadd(){
        System.out.println("Adding a new criminal to database");
        System.out.println("Enter id of criminal: ");
        int CriId = scanner.nextInt();
        System.out.println("Enter name of criminal: ");
        String CriName= scanner.next();
        System.out.println("Enter article of criminal: ");
        String article= scanner.next();
        System.out.println("Enter age of criminal: ");
        int CriAge = scanner.nextInt();
        System.out.println("Is he wanted? Enter true/false");
        boolean wanted=scanner.nextBoolean();
        String response = crimeController.addCrime(CriId,CriName,article,CriAge,wanted);
        System.out.println(response);
    }
    public void consoleCrimeid(){
        System.out.println("Getting criminal's information by id...");
        System.out.println("Enter id of Criminal: ");
        int id= scanner.nextInt();
        String response = crimeController.getCrimeById(id);
        System.out.println(response);
    }
    public void consoleCrimeCri(){
        System.out.println("Getting criminal's information by crime...");
        System.out.println("Enter crime of Criminal: ");
        String article= scanner.next();
        String response = crimeController.getCrimeByCri(article);
        System.out.println(response);
    }
    public void getAllCri(){
        System.out.println("Getting list of all criminal in database...");
        String response = crimeController.getAllCriminal();
        System.out.println(response);
    }
    public void crichange(){
        System.out.println("Enter the criminal's ID to change the status: ");
        int id=scanner.nextInt();
        System.out.println("Enter the criminal's wanted status: (true/false)");
        boolean wanted=scanner.nextBoolean();
        String response = crimeController.crichange(id,wanted);
        System.out.println(response);
    }
   public void criage(){
       System.out.println("Enter a number to display criminals over that age");
       int age=scanner.nextInt();
       String response = crimeController.criage(age);
       System.out.println(response);
   }
    public void consoleCases(){
        while (true) {
            System.out.println("   What do you want to do?");
            System.out.println("1. Add new case to Database");
            System.out.println("2. Get case's information by ID");
            System.out.println("3. Display all cases");
            System.out.println("4. Delete a case from the database");
            System.out.println("5. Change the FBI employee who is investigating the case");
            System.out.println("6. Get the case case by the ID of an employee who is investigating the case");
            System.out.println("7. Back to menu");
            try {
                int option3 = scanner.nextInt();
                if (option3 == 1) {
                    consoleCaseadd();
                }
                if(option3 == 2){
                    consoleCaseId();
                }
                if(option3 == 3){
                    consoleallcase();
                }
                if(option3 == 4){
                    casedelete();
                }
                if(option3 == 5){
                    fbichange();
                }
                if(option3 == 6){
                    caseinvest();
                }
                if(option3 == 7){
                    start();
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void consoleCaseadd(){
        System.out.println("Adding a new case to database...");
        System.out.println("Enter id of case");
        int idCase = scanner.nextInt();
        System.out.println("Enter name of case");
        String name=scanner.next();
        System.out.println("Enter id of FBI employee that will investigate this case");
        int investCase = scanner.nextInt();
        String response = caseController.addCase(idCase,name,investCase);
        System.out.println(response);
    }
    public void consoleCaseId(){
        System.out.println("Getting case's information by id...");
        System.out.println("Enter id of Case: ");
        int id= scanner.nextInt();
        String response = caseController.getCaseById(id);
        System.out.println(response);
    }
    public void consoleallcase(){
        System.out.println("Displaying all cases...");
        String response = caseController.getAllCases();
        System.out.println(response);
    }
    public void casedelete(){
        System.out.println("Deleting a case from the database...");
        System.out.println("Enter the case ID: ");
        int id = scanner.nextInt();
        String response = caseController.disCase(id);
        System.out.println(response);
    }
    public void fbichange(){
        System.out.println("Enter the case ID: ");
        int id=scanner.nextInt();
        System.out.println("Enter the ID of the new FBI employee who will handle this case");
        int fbiid=scanner.nextInt();
        String response = caseController.fbichang(id,fbiid);
        System.out.println(response);
    }
    public void caseinvest(){
            System.out.println("Getting all cases investigated by a specific FBI employee...");
            System.out.println("Enter ID of FBI employee");
            int id= scanner.nextInt();
            String response = caseController.getAllCa(id);
            System.out.println(response);
        }
    }
