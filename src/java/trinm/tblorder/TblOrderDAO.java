/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblorder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author Tri Nguyen
 */
public class TblOrderDAO implements Serializable {

    public void createTblOrder(String username, float total) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblOrder (username,total) "
                        + "VALUES (?,?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setFloat(2, total);
                stm.executeUpdate();

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
    }
    
    public int getLastID()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int last = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT TOP 1 id "
                        + "FROM tblOrder "
                        + "ORDER BY ID DESC";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    int id = rs.getInt("id");
                    last = id;
                }// end while
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
        return last;
    }

}
