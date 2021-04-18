/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblcart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trinm.tblcar.TblCarDAO;
import trinm.utilities.DBHelper;

/**
 *
 * @author Tri Nguyen
 */
public class TblCartDAO implements Serializable{

    public void createTblCart(int id, String carID, int amount, float price, String datePick, String dateBack) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblCart (orderID, carID, amount, price, datePick, dateBack, status) "
                        + "VALUES (?,?,?,?,?,?,?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, carID);
                stm.setInt(3, amount);
                stm.setFloat(4, price);
                stm.setString(5, datePick);
                stm.setString(6, dateBack);
                stm.setString(7, "active");
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
    
    private List<TblCartDTO> historyLoad;

    public List<TblCartDTO> getHistoryLoad() {
        return historyLoad;
    }

    public void searchHistory(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT Cart.orderID, Cart.carID, Cart.amount, Cart.price, CONVERT(DATETIME,Cart.datePick) as datePick, CONVERT(DATETIME,Cart.dateBack) as dateBack, Cart.dateRent, Cart.status " 
                        + "FROM tblCart as Cart, tblOrder as [Order] "
                        + "WHERE Cart.orderID = [Order].id AND [Order].username = ? "
                        + "ORDER BY orderID";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String carID = rs.getString("carID");
                    String carName = new TblCarDAO().getName(carID);
                    int amount = rs.getInt("amount");
                    float price = rs.getFloat("price");
                    String datePick = rs.getDate("datePick").toString();
                    String dateBack = rs.getDate("dateBack").toString();
                    String dateRent = rs.getDate("dateRent").toString();
                    String status = rs.getString("status");

                    TblCartDTO dto = new TblCartDTO(orderID, carName, amount, price, datePick, dateBack, dateRent, status);
                    
                    if (this.historyLoad == null) {
                        this.historyLoad = new ArrayList<>();
                    }
                    this.historyLoad.add(dto);
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
    
    public void searchHistory(String id, String userID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT Cart.orderID, Cart.carID, Cart.amount, Cart.price, CONVERT(DATETIME,Cart.datePick) as datePick, CONVERT(DATETIME,Cart.dateBack) as dateBack, Cart.dateRent, Cart.status " 
                        + "FROM tblCart as Cart, tblOrder as [Order] "
                        + "WHERE Cart.carID = ? AND Cart.orderID = [Order].id AND [Order].username = ? "
                        + "ORDER BY orderID";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, userID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String carID = rs.getString("carID");
                    String carName = new TblCarDAO().getName(carID);
                    int amount = rs.getInt("amount");
                    float price = rs.getFloat("price");
                    String datePick = rs.getDate("datePick").toString();
                    String dateBack = rs.getDate("dateBack").toString();
                    String dateRent = rs.getDate("dateRent").toString();
                    String status = rs.getString("status");

                    TblCartDTO dto = new TblCartDTO(orderID, carName, amount, price, datePick, dateBack, dateRent, status);
                    
                    if (this.historyLoad == null) {
                        this.historyLoad = new ArrayList<>();
                    }
                    this.historyLoad.add(dto);
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
    
    public void searchHistoryByDate(String date, String userID)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT Cart.orderID, Cart.carID, Cart.amount, Cart.price, CONVERT(DATETIME,Cart.datePick) as datePick, CONVERT(DATETIME,Cart.dateBack) as dateBack, Cart.dateRent, Cart.status " 
                        + "FROM tblCart as Cart, tblOrder as [Order] "
                        + "WHERE CAST(Cart.dateRent AS DATE) = ? AND Cart.orderID = [Order].id AND [Order].username = ? "
                        + "ORDER BY orderID";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(date).getTime()));
                stm.setString(2, userID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String carID = rs.getString("carID");
                    String carName = new TblCarDAO().getName(carID);
                    int amount = rs.getInt("amount");
                    float price = rs.getFloat("price");
                    String datePick = rs.getDate("datePick").toString();
                    String dateBack = rs.getDate("dateBack").toString();
                    String dateRent = rs.getDate("dateRent").toString();
                    String status = rs.getString("status");

                    TblCartDTO dto = new TblCartDTO(orderID, carName, amount, price, datePick, dateBack, dateRent, status);
                    
                    if (this.historyLoad == null) {
                        this.historyLoad = new ArrayList<>();
                    }
                    this.historyLoad.add(dto);
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
    
    public void searchHistoryByDate(String id, String date, String userID)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT Cart.orderID, Cart.carID, Cart.amount, Cart.price, CONVERT(DATETIME,Cart.datePick) as datePick, CONVERT(DATETIME,Cart.dateBack) as dateBack, Cart.dateRent, Cart.status " 
                        + "FROM tblCart as Cart, tblOrder as [Order] "
                        + "WHERE CAST(Cart.dateRent AS DATE) = ? AND Cart.carID = ? AND Cart.orderID = [Order].id AND [Order].username = ?  "
                        + "ORDER BY orderID";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(date).getTime()));
                stm.setString(2, id);
                stm.setString(3, userID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String carID = rs.getString("carID");
                    String carName = new TblCarDAO().getName(carID);
                    int amount = rs.getInt("amount");
                    float price = rs.getFloat("price");
                    String datePick = rs.getDate("datePick").toString();
                    String dateBack = rs.getDate("dateBack").toString();
                    String dateRent = rs.getDate("dateRent").toString();
                    String status = rs.getString("status");

                    TblCartDTO dto = new TblCartDTO(orderID, carName, amount, price, datePick, dateBack, dateRent, status);
                    
                    if (this.historyLoad == null) {
                        this.historyLoad = new ArrayList<>();
                    }
                    this.historyLoad.add(dto);
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

    public boolean deleteCart(int orderID, String carID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "UPDATE tblCart "
                        + "SET status = ? "
                        + "WHERE orderID = ? AND carID = ? ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "inactive");
                stm.setInt(2, orderID);
                TblCarDAO dao = new TblCarDAO();
                stm.setString(3, dao.getID(carID));
                //4.    Execute Query
                int row = stm.executeUpdate();
                //5.    Process result
                if (row > 0) {
                    rt = true;
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
        return rt;
    }
}
