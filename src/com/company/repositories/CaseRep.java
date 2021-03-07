package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Cases;
import com.company.entities.Employee;
import com.company.entities.interfaces.ICaseRep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CaseRep extends Cases implements ICaseRep {
    private final IDB db;
    public CaseRep(IDB db){this.db= db;}

    public String caseInfo(){
        return "Case name: "+ getName()+", ID: "+getIdCase();
    }
    @Override
    public boolean addCase(Cases cases) {
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "INSERT INTO cases(idCase,name,investCase) VALUES(?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, getIdCase());
            st.setString(2, getName());
            st.setInt(3, getInvestCase());
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
    public Cases getCaselId(int id){
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "SELECT * FROM cases WHERE idcase=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cases cases = new Cases(rs.getInt("idcase"),
                        rs.getString("name"),
                        rs.getInt("investCase"));
                return cases;
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
    public List<Cases> getAllCases() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from cases";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Integer> idList = new LinkedList<>();
            while (rs.next()){
                idList.add(rs.getInt("idcase"));
            }
            int counter = 0;
            List<Cases> list = new LinkedList<>();
            while (counter < idList.size()){
                sql = "select * from cases where idcase = ?";
                st = con.prepareStatement(sql);
                st.setInt(1,idList.get(counter));
                rs = st.executeQuery();
                counter++;
                //save all data
                while(rs.next()){
                    Cases cases= new Cases(rs.getInt("idcase"),
                            rs.getString("name"),
                            rs.getInt("investcase"));
                    list.add(cases);
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
    public boolean  disCase(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM cases WHERE idcase = ?";
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
    public boolean fbichange(int id,int fbiid){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "update cases set investcase =? where idcase=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, fbiid);
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
    public Cases getAllCasesFBIf(int id){
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "SELECT * FROM cases WHERE investcase=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cases cases = new Cases(rs.getInt("idcase"),
                        rs.getString("name"),
                        rs.getInt("investCase"));
                return cases;
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
}
