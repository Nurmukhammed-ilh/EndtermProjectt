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

public class EmployeeRep implements IEmpRep {
    private final IDB db;
    public EmployeeRep(IDB db){this.db= db;}

    @Override
    public boolean addEmployee(Employee employee) {
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "INSERT INTO employee(id,name,surname,gender,age,salary,position,dateAdoption,dismissalDate) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, employee.getId());
            st.setString(2, employee.getName());
            st.setString(3, employee.getSurname());
            st.setString(4, employee.getGender());
            st.setInt(5, employee.getAge());
            st.setInt(6, employee.getSalary());
            st.setString(7, employee.getPosition());
            st.setString(8, employee.getDateAdoption());
            st.setString(9, employee.getDismissalDate());
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

    public Employee getEmployeeId(int id){
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


    public Employee getEmployeePos(String position){
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


    public boolean disEmp(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
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
    public boolean changeEmp(int cash, int id){
        Connection con = null;
        try {
            con = db.getConnection();
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
    public boolean promEmp(int id, String position){
        Connection con = null;
        try {
            con = db.getConnection();
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
    public List<Employee> getAllEmployee() {
        Connection con = null;
        try {
            con = db.getConnection();
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
    public List<Employee> getAllEmployeeRel(){
        Connection con = null;
        try {
            con = db.getConnection();
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
    public boolean kickEmp(String dismissaldate, int id) {
        Connection con = null;
        try {
            con = db.getConnection();
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
