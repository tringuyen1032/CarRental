/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblcar;

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
public class TblCarDAO implements Serializable {

    private List<TblCarDTO> carLoad;

    public List<TblCarDTO> getCarLoad() {
        return carLoad;
    }
    
    private int num = 0;
    
    public int getNum() {
        return num;
    }

    public void loadCar(int count)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        count = (count - 1) * 20 + 1;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "WITH RESULT AS "
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, count);
                stm.setInt(2, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }
                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category);
                    this.carLoad.add(dto);
                    this.num = rowNum;
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
    
    public void search(int count, String valueSearch)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        count = (count - 1) * 20 + 1;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "WITH RESULT AS "
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
                stm.setInt(2, count);
                stm.setInt(3, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }
                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category);
                    this.carLoad.add(dto);
                    this.num = rowNum;
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
