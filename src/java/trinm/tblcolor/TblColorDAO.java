/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblcolor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author trinm
 */
public class TblColorDAO implements Serializable{
    
    private List<String> colorLoad;

    public List<String> getColorLoad() {
        return colorLoad;
    }

    public void getColor(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT color "
                        + "FROM tblColor "
                        + "WHERE id = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String color = rs.getString("color");

                    if (this.colorLoad == null) {
                        this.colorLoad = new ArrayList<>();
                    }
                    

                    this.colorLoad.add(color);
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
    }
}
