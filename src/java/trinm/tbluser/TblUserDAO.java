/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tbluser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author tring
 */
public class TblUserDAO implements Serializable {

    public int checkLogin(String ID, String pass) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rt = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT ID, status "
                        + "FROM tblUser "
                        + "WHERE ID = ? AND pass = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                stm.setString(2, pass);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    String status = rs.getString("status");
                    if(status.equals("Active")) {
                        rt = 1;
                    } else {
                        rt = 2;
                    }
                }// end if rs
            }//end if connection is opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rt;
    }
    
    public int checkLogin(String ID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rt = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT ID, status "
                        + "FROM tblUser "
                        + "WHERE ID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    String status = rs.getString("status");
                    if(status.equals("Active")) {
                        rt = 1;
                    } else {
                        rt = 2;
                    }
                }// end if rs
            }//end if connection is opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rt;
    }

    public String getFullname(String ID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT name "
                        + "FROM tblUser "
                        + "WHERE ID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return name;
    }

    public boolean getRole(String ID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT role "
                        + "FROM tblUser "
                        + "WHERE ID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    rt = rs.getBoolean("role");
                }
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return rt;
    }

    public boolean createAccount(String ID, String password, String fullname, boolean role, String status, long phone, String address) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblUser(ID, name, pass, role, status, phone, address) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                stm.setString(2, fullname);
                stm.setString(3, password);
                stm.setBoolean(4, role);
                stm.setString(5, status);
                stm.setLong(6, phone);
                stm.setString(7, address);
                //4.    Execute Query
                int row = stm.executeUpdate();
                //5.    Process result
                if (row > 0) {
                    rt = true;
                }// end if rs
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rt;
    }
    
    public boolean createAccount(String ID, String password, String fullname, boolean role, String status, String address) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblUser(ID, name, pass, role, status, address) "
                        + "VALUES(?, ?, ?, ?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                stm.setString(2, fullname);
                stm.setString(3, password);
                stm.setBoolean(4, role);
                stm.setString(5, status);
                stm.setString(6, address);
                //4.    Execute Query
                int row = stm.executeUpdate();
                //5.    Process result
                if (row > 0) {
                    rt = true;
                }// end if rs
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rt;
    }
    
    public boolean updateUser(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "Update tblUser "
                        + "Set status = ? "
                        + "Where ID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "Active");
                stm.setString(2, username);
                //4.    Execute Query
                int row = stm.executeUpdate();
                //5.    Process result
                if (row > 0) {
                    return true;
                }
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
