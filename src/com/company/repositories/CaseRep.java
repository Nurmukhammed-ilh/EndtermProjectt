package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Cases;
import com.company.entities.Criminals;
import com.company.entities.Employee;
import com.company.entities.interfaces.ICaseRep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CaseRep implements ICaseRep {
    private final IDB db;
    public CaseRep(IDB db){this.db= db;}

    @Override
    public boolean addCase(Cases cases) {
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "INSERT INTO cases(idCase,name,investCase) VALUES(?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cases.getIdCase());
            st.setString(2, cases.getName());
            st.setInt(3, cases.getInvestCase());
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

}
