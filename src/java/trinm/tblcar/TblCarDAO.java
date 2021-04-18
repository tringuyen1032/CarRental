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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private int pos = 0;

    public int getNum() {
        return this.num;
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }
                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public void loadCar(int count, String ctg)
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE category = ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ctg);
                stm.setInt(2, count);
                stm.setInt(3, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                this.carLoad = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");
                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public void loadCar(int count, String ctg, int amount)
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE category = ? AND quantity >= ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, ctg);
                stm.setInt(2, amount);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                this.carLoad = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");
                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public void loadCar(int count, int amount)
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE quantity >= ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, amount);
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }
                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    private List<String> categoryLoad;

    public List<String> getCategory() {
        return categoryLoad;
    }

    public void loadCategory()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT DISTINCT category "
                        + "FROM tblCar";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    if (this.categoryLoad == null) {
                        this.categoryLoad = new ArrayList<>();
                    }
                    categoryLoad.add(rs.getString("category"));
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }

                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public void search(int count, String valueSearch, int amount)
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ? AND quantity >= ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
                stm.setInt(2, amount);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }

                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public void search(int count, String valueSearch, String ctg)
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ? AND category = ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
                stm.setString(2, ctg);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }

                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public void search(int count, String valueSearch, String ctg, int amount)
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
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ? AND category = ? AND quantity >= ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
                stm.setString(2, ctg);
                stm.setInt(3, amount);
                stm.setInt(4, count);
                stm.setInt(5, count + 19);
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }

                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = rowNum + this.pos;
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

    public String getName(String id)
            throws SQLException, NamingException {
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
                        + "FROM tblCar "
                        + "WHERE id = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }

    public String getID(String name)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String id = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT id "
                        + "FROM tblCar "
                        + "WHERE name = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    id = rs.getString("id");
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
        return id;
    }

    public float searchPrice(String name)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float price = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT price "
                        + "FROM tblCar "
                        + "WHERE name = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    price = rs.getFloat("price");
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
        return price;
    }

    private List<String> searchValue;

    public List<String> getSearchValue() {
        return searchValue;
    }

    public void checkStock(String datePick, String dateBack)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT MIN(Car.name) as name, SUM(Cart.amount) as count, MIN(Car.quantity) as quantity, MIN(Car.dateCreate) as dateCreate "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID "
                        + "UNION ALL "
                        + "SELECT name, '0' as count, quantity, dateCreate "
                        + "FROM tblCar "
                        + "WHERE name NOT IN (SELECT MIN(Car.name) as name "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(2, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(3, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(4, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(5, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(6, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(7, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(8, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(9, "active");
                stm.setDate(10, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(11, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(12, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(13, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(14, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(15, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(16, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(17, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(18, "active");
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                this.searchValue = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    int count = rs.getInt("count");
                    int quantity = rs.getInt("quantity");
                    if ((quantity - count) > 0) {
                        this.searchValue.add(name);
                        this.pos = pos + 1;
                    }
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

    public void checkStock(String datePick, String dateBack, int amount)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT MIN(Car.name) as name, SUM(Cart.amount) as count, MIN(Car.quantity) as quantity, MIN(Car.dateCreate) as dateCreate "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID "
                        + "UNION ALL "
                        + "SELECT name, '0' as count, quantity, dateCreate "
                        + "FROM tblCar "
                        + "WHERE name NOT IN (SELECT MIN(Car.name) as name "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID) "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(2, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(3, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(4, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(5, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(6, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(7, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(8, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(9, "active");
                stm.setDate(10, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(11, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(12, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(13, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(14, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(15, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(16, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(17, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(18, "active");
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                this.searchValue = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    int count = rs.getInt("count");
                    int quantity = rs.getInt("quantity");
                    if ((quantity - count) >= amount) {
                        this.pos = pos + 1;
                        this.searchValue.add(name);
                    }
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

    public void checkStock(String datePick, String dateBack, String value)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "WITH RESULT AS (SELECT MIN(Car.name) as name, SUM(Cart.amount) as count, MIN(Car.quantity) as quantity, MIN(Car.dateCreate) as dateCreate "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID "
                        + "UNION ALL "
                        + "SELECT name, '0' as count, quantity, dateCreate "
                        + "FROM tblCar "
                        + "WHERE name NOT IN (SELECT MIN(Car.name) as name "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID)) "
                        + "SELECT name, count, quantity, dateCreate "
                        + "FROM RESULT "
                        + "WHERE name LIKE ? "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(2, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(3, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(4, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(5, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(6, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(7, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(8, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(9, "active");
                stm.setDate(10, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(11, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(12, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(13, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(14, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(15, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(16, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(17, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(18, "active");
                stm.setString(19, "%" + value + "%");
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                this.searchValue = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    int count = rs.getInt("count");
                    int quantity = rs.getInt("quantity");
                    if ((quantity - count) > 0) {
                        this.searchValue.add(name);
                        this.pos = pos + 1;
                    }
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

    public boolean checkStock(String datePick, String dateBack, String value, int amount)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "WITH RESULT AS (SELECT MIN(Car.name) as name, SUM(Cart.amount) as count, MIN(Car.quantity) as quantity, MIN(Car.dateCreate) as dateCreate "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID "
                        + "UNION ALL "
                        + "SELECT name, '0' as count, quantity, dateCreate "
                        + "FROM tblCar "
                        + "WHERE name NOT IN (SELECT MIN(Car.name) as name "
                        + "FROM tblCart as Cart INNER JOIN tblCar as Car  ON Car.id = Cart.carID "
                        + "WHERE ((Cart.datePick >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick >= ? AND Cart.datePick <= ?) "
                        + "OR (Cart.dateBack >= ? AND Cart.dateBack <= ?) "
                        + "OR (Cart.datePick <= ? AND Cart.dateBack >= ?) "
                        + ") AND Cart.status = ? "
                        + "GROUP BY Cart.carID)) "
                        + "SELECT name, count, quantity, dateCreate "
                        + "FROM RESULT "
                        + "WHERE name LIKE ? "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setDate(1, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(2, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(3, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(4, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(5, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(6, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(7, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(8, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(9, "active");
                stm.setDate(10, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(11, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(12, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(13, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(14, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(15, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setDate(16, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(datePick).getTime()));
                stm.setDate(17, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateBack).getTime()));
                stm.setString(18, "active");
                stm.setString(19, "%" + value + "%");
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                this.searchValue = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    int count = rs.getInt("count");
                    int quantity = rs.getInt("quantity");
                    if ((quantity - count) >= amount) {
                        rt = true;
                        this.pos = pos + 1;
                        this.searchValue.add(name);
                    }
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
        return rt;
    }

    public void searchDate(String valueSearch)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "WITH RESULT AS "
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }

                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = (rowNum - 1) + this.pos;
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

    public void searchDate(String valueSearch, String ctg)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "WITH RESULT AS "
                        + "(SELECT id, name, image, price, quantity, category, dateCreate, color, ROW_NUMBER() OVER(ORDER BY dateCreate) AS rowNum "
                        + "FROM tblCar "
                        + "WHERE name LIKE ? AND category = ?) "
                        + "SELECT id, name, image, price, quantity, category, dateCreate, color, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "ORDER BY dateCreate";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
                stm.setString(2, ctg);
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
                    String color = rs.getString("color");
                    int rowNum = rs.getInt("Count");

                    if (this.carLoad == null) {
                        this.carLoad = new ArrayList<>();
                    }

                    TblCarDTO dto = new TblCarDTO(id, name, image, price, quantity, category, color);
                    this.carLoad.add(dto);
                    this.num = (rowNum - 1) + this.pos;
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

    private List<String> carID;

    public List<String> getCarID() {
        return carID;
    }

    public void search(String valueSearch)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT id "
                        + "FROM tblCar "
                        + "WHERE name LIKE ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + valueSearch + "%");
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String id = rs.getString("id");

                    if (this.carID == null) {
                        this.carID = new ArrayList<>();
                    }

                    this.carID.add(id);
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
