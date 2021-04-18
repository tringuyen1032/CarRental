/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tbldiscount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author trinm
 */
public class TblDiscountDAO implements Serializable{
    
    public int searchDiscount(String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rt = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT discount "
                        + "FROM tblDiscount "
                        + "WHERE code = ? AND CAST( GETDATE() AS Date ) <= expiryDate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    rt = rs.getInt("discount");
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
}
