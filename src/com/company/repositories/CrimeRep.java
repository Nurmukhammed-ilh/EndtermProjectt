package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Cases;
import com.company.entities.Criminals;
import com.company.entities.interfaces.ICrimeRep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CrimeRep extends Criminals implements ICrimeRep {
    private final IDB db;
    public CrimeRep(IDB db){this.db= db;}

    public String crimeInfo(){
        return "The criminal with ID "+getCriAge()+", is wanted: "+isWanted();
    }
    @Override
    public boolean addCriminal(Criminals criminals) {
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "INSERT INTO criminal(CriId,CriName,article,CriAge,wanted) VALUES(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, getCriId());
            st.setString(2, getCriName());
            st.setString(3, getArticle());
            st.setInt(4, getCriAge());
            st.setBoolean(5, isWanted());
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

    public Criminals getCriminalId(int id){
        Connection con = null;
        try {
            con = db.getConnection(); //the SQL codes will be "copied" to pgadmin4
            String sql = "SELECT * FROM criminal WHERE criid=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Criminals criminals= new Criminals(rs.getInt("criid"),
                        rs.getString("Criname"),
                        rs.getString("article"),
                        rs.getInt("CriAge"),
                        rs.getBoolean("wanted"));
                return criminals;
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


    public List<Criminals> getAllCriCri(String article){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from criminal where article=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, article);
            ResultSet rs = st.executeQuery();
            List<Integer> idList = new LinkedList<>();
            while (rs.next()){
                idList.add(rs.getInt("criid"));
            }
            int counter = 0;
            List<Criminals> list = new LinkedList<Criminals>();
            while (counter < idList.size()){
                sql = "select * from criminal where criid = ?";
                st = con.prepareStatement(sql);
                st.setInt(1,idList.get(counter));
                rs = st.executeQuery();
                counter++;
                while(rs.next()){
                    Criminals criminals= new Criminals(rs.getInt("criid"),
                            rs.getString("Criname"),
                            rs.getString("article"),
                            rs.getInt("CriAge"),
                            rs.getBoolean("wanted"));
                    list.add(criminals);
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
    @Override
    public List<Criminals> getAllCriminal(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from criminal";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Integer> idList = new LinkedList<>();
            while (rs.next()){
                idList.add(rs.getInt("criid"));
            }
            int counter = 0;
            List<Criminals> list = new LinkedList<Criminals>();
            while (counter < idList.size()){
                sql = "select * from criminal where criid = ?";
                st = con.prepareStatement(sql);
                st.setInt(1,idList.get(counter));
                rs = st.executeQuery();
                counter++;
                while(rs.next()){
                    Criminals criminals= new Criminals(rs.getInt("criid"),
                            rs.getString("Criname"),
                            rs.getString("article"),
                            rs.getInt("CriAge"),
                            rs.getBoolean("wanted"));
                    list.add(criminals);
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

    public boolean crichange(int id, boolean wanted){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "update criminal set wanted =? where criid=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, wanted);
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

        public List<Criminals> criage(int age){
            Connection con = null;
            try {
                con = db.getConnection();
                String sql = "select * from criminal where criage>?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1,age);
                ResultSet rs = st.executeQuery();
                List<Integer> idList = new LinkedList<>();
                while (rs.next()){
                    idList.add(rs.getInt("criid"));
                }
                int counter = 0;
                List<Criminals> list = new LinkedList<Criminals>();
                while (counter < idList.size()){
                    sql = "select * from criminal where criid = ?";
                    st = con.prepareStatement(sql);
                    st.setInt(1,idList.get(counter));
                    rs = st.executeQuery();
                    counter++;
                    while(rs.next()){
                        Criminals criminals= new Criminals(rs.getInt("criid"),
                                rs.getString("Criname"),
                                rs.getString("article"),
                                rs.getInt("CriAge"),
                                rs.getBoolean("wanted"));
                        list.add(criminals);
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
}
