package com.company.repositories;


import com.company.data.interfaces.IDB;
import com.company.entities.Employee;
import com.company.entities.interfaces.IEmpRep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeRep extends Employee implements IEmpRep {
    private final IDB db;
    public EmployeeRep(IDB db){this.db= db;}

    public String empInfo(){
        return "FBI employee "+getName()+"with ID "+getId()+" gets "+getSalary()+"$ per month";
    }
    @Override
    public boolean addEmployee(Employee employee) { //method to add a new employee
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "INSERT INTO employee(id,name,surname,gender,age,salary,position,dateAdoption,dismissalDate) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, getId());
            st.setString(2, getName());
            st.setString(3, getSurname());
            st.setString(4, getGender());
            st.setInt(5,getAge());
            st.setInt(6, getSalary());
            st.setString(7, getPosition());
            st.setString(8, getDateAdoption());
            st.setString(9, getDismissalDate());
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                con.close();}
            catch (SQLException throwables){
                throwables.printStackTrace();}
        }return false;}

    public Employee getEmployeeId(int id){ //display an employee by his id
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "SELECT id,name,surname,gender,age,salary,position,dateAdoption,dismissalDate FROM employee WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee employee= new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getInt("salary"),
                        rs.getString("position"),
                        rs.getString("dateAdoption"),
                        rs.getString("dismissalDate"));
                return employee;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();}
            catch (SQLException throwables) {
                throwables.printStackTrace();}
        }return null;}


    public Employee getEmployeePos(String position){ //display an employee by his position(profession)
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "SELECT id,name,surname,gender,age,salary,position,dateAdoption,dismissalDate FROM employee WHERE position=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, position);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee employee= new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getInt("salary"),
                        rs.getString("position"),
                        rs.getString("dateAdoption"),
                        rs.getString("dismissalDate"));
                return employee;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();}
            catch (SQLException throwables) {
                throwables.printStackTrace();}
        }return null;
    }


    public boolean disEmp(int id) { //dismissal an employee
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "DELETE FROM employee WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    public boolean changeEmp(int cash, int id){ //method for changing a salary of employee by his id
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "update employee set salary =? where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cash);
            st.setInt(2, id);
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();}
            catch (SQLException throwables) {
                throwables.printStackTrace();}
        }return false;
    }
    public boolean promEmp(int id, String position){ //employee promotion by his id
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "update employee set position =? where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, position);
            st.setInt(2, id);
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();}
            catch (SQLException throwables) {
                throwables.printStackTrace();}
        }return false;
    }
    @Override
    public List<Employee> getAllEmployee() { //display a list of all employees
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "select * from employee";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Integer> idList = new LinkedList<>();
            while (rs.next()){
                idList.add(rs.getInt("id"));
            }
            int counter = 0;
            List<Employee> list = new LinkedList<Employee>();
            while (counter < idList.size()){
                sql = "select * from employee where id = ?";
                st = con.prepareStatement(sql);
                st.setInt(1,idList.get(counter));
                rs = st.executeQuery();
                counter++;
                //save all data
                while(rs.next()){
                    Employee employee= new Employee(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getInt("salary"),
                            rs.getString("position"),
                            rs.getString("dateAdoption"),
                            rs.getString("dismissalDate"));
                    list.add(employee);
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public List<Employee> getAllEmployeeRel(){ //display a list of employees who are still working
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "select * from employee where dismissaldate='null'";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Integer> idList = new LinkedList<>();
            while (rs.next()){
                idList.add(rs.getInt("id"));
            }
            int counter = 0;
            List<Employee> list = new LinkedList<Employee>();
            while (counter < idList.size()){
                sql = "select * from employee where id = ?";
                st = con.prepareStatement(sql);
                st.setInt(1,idList.get(counter));
                rs = st.executeQuery();
                counter++;
                //save all data
                while(rs.next()){
                    Employee employee= new Employee(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getInt("salary"),
                            rs.getString("position"),
                            rs.getString("dateAdoption"),
                            rs.getString("dismissalDate"));
                    list.add(employee);
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public boolean kickEmp(String dismissaldate, int id) { //change a date when employee was dismissal
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "update employee set dismissaldate =? where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dismissaldate);
            st.setInt(2, id);
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
