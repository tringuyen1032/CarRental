/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinmtblfeedback;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import trinm.tblcar.TblCarDAO;
import trinm.utilities.DBHelper;

/**
 *
 * @author Tri Nguyen
 */
public class TblFeedbackDAO implements Serializable{
    
    public boolean createFeedback(String text, int rate, int orderID, String carID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblFeedback(feedback, rate, orderID, carID) "
                        + "VALUES(?, ?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, text);
                stm.setInt(2, rate);
                stm.setInt(3, orderID);
                TblCarDAO dao = new TblCarDAO();
                stm.setString(4, dao.getID(carID));
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
}
