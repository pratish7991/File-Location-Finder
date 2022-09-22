package one;

import java.io.File;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Logfile {
    public int checkTable(String tableName){
        int f=0;
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        try{
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                f=1;
                return f;
            }
            else {
                return f;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void createTable(String tablename){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        try{
            Statement stmt = con.createStatement();
            String query;
            if (Objects.equals(tablename, "history4")){
                query="create table history4(ID varchar(255),Drive varchar(225),Directory varchar(225));";
            }if (Objects.equals(tablename, "history10")){
                query="create table history10(ID varchar(255),Drive varchar(225),Directory varchar(225));";
            }else if(Objects.equals(tablename,"history14")){
                query="create table history14(id varchar(225),drivename varchar(225),search boolean);";
            }else if(Objects.equals(tablename,"history11")){
                query="create table history11(id varchar(225),drivename varchar(225),search boolean);";
            }else if(Objects.equals(tablename,"history12")){
                query="create table history12(id varchar(225),drivename varchar(225),search boolean);";
            }else{
                query="create table history13(id varchar(225),drivename varchar(225),search boolean);";
            }
            stmt.execute(query);
            System.out.println("table created");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertTable(String name,String drive,String file){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
//        String hh = String.valueOf(file);
        String query ="insert into history4 values('"+name+"','" +drive+"',' "+file+"')";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String selectTable(String name,int N,String drivename){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        int i=0;
        String query="select Directory from history4 where ID='"+name+"' and Drive='"+drivename+"'";
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(i<N){
                rs.next();
                i++;
            }
            String f=rs.getString(1);
            return f;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "null";
    }
    public int selectCount(String name){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(Directory) as count from history4 where ID='"+name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
    public int selectCount(String name,String drive_name){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(Directory) as count from history4 where ID='"+name+"' and Drive='"+drive_name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }


    public void deletetable(String path,String D){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="delete from history4 where id='"+path+"' and drive='"+D+"'";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void insertTable_searchoperation(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="insert into history14 values('"+name+"','" +g+"','" +b+"')";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean selectTable_searchoperation(String name, String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query="select search from history14 where id='"+name+"'and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            rs.next();
            return rs.getBoolean("search");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void updateTable_searchoperation(String name,String drivename,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="update history14 set search='"+b+"' where ID ='"+name+"' and drivename='"+drivename+"' ";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int selectCountTable_searchoperation(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(id) as count from history14 where drivename='"+g+"' and id='"+name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public void insertTable_searchoperation_deletion(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="insert into history11 values('"+name+"','" +g+"','" +b+"')";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean selectTable_searchoperation_deletion(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query="select search from history11 where id='"+name+"'and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);

            rs.next();
            return rs.getBoolean("search");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void updateTable_searchoperation_deletion(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="update history11 set search='"+b+"' where ID ='"+name+"' and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int selectCountTable_searchoperation_deletion(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(id) as count from history11 where drivename='"+g+"' and id = '"+name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public void insertTable_searchoperation_addition(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="insert into history12 values('"+name+"','" +g+"','" +b+"')";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean selectTable_searchoperation_addition(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query="select search from history12 where id='"+name+"'and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);

            rs.next();
            return rs.getBoolean("search");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void updateTable_searchoperation_addition(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="update history12 set search='"+b+"' where ID ='"+name+"' and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int selectCountTable_searchoperation_addition(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(id) as count from history12 where drivename='"+g+"' and id = '"+name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count") ;
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
    public void insertTable_searchoperation_directorychange(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="insert into history13 values('"+name+"','" +g+"','" +b+"')" ;
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean selectTable_searchoperation_directorychange(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query="select search from history13 where id='"+name+"' and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);

            rs.next();
            return rs.getBoolean("search");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void updateTable_searchoperation_directorychange(String name,String g,Boolean b){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="update history13 set search='"+b+"' where ID ='"+name+"' and drivename='"+g+"'";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int selectCountTable_searchoperation_directorychange(String name,String g){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(id) as count from history13 where drivename='"+g+"'  and id = '"+name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public void insertTable3(String name,String drive,String file){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
//        String hh = String.valueOf(file);
        String query ="insert into history10 values('"+name+"','" +drive+"',' "+file+"')";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String selectTable3(String name,int N,String drivename){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        int i=0;
        String query="select Directory from history10 where ID='"+name+"' and Drive='"+drivename+"'";
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(i<N){
                rs.next();
                i++;
            }
            String f=rs.getString(1);
            return f;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "null";
    }
    public int selectCountTable3(String name){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(Directory) as count from history10 where ID='"+name+"' ";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
    public int selectCountTable3(String name,String drive_name){
        DBConnect db = new DBConnect();
        Connection con =db.connect();
        String query="select count(Directory) as count from history10 where ID='"+name+"' and Drive='"+drive_name+"'";
        int count=0;
        try{
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                count=rs.getInt("count");
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }


    public void deletetable3(String path,String D){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="delete from history10 where id='"+path+"' and Drive='"+D+"'";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deletetable3(String path){
        DBConnect db = new DBConnect();
        Connection con =db.connect();

        String query ="delete from history10 where id='"+path+"'";
        try{
            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
