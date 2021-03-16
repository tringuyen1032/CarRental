/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblresult;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author tring
 */
public class TblResultDAO implements Serializable {

    private List<TblResultDTO> resultList;

    public List<TblResultDTO> getResultList() {
        return resultList;
    }

    public void loadResult(String user, int count)
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
                        + "(SELECT userID, subjectID, point, submitDate, resultID, ROW_NUMBER() OVER(ORDER BY userID) AS rowNum   "
                        + "FROM tblResult "
                        + "WHERE userID = ?) "
                        + "SELECT userID, subjectID, point, submitDate, resultID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, user);
                stm.setInt(2, count);
                stm.setInt(3, count + 19);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String subjectID = rs.getString("subjectID");
                    float point = rs.getFloat("point");
                    Timestamp submitDate = rs.getTimestamp("submitDate");
                    int resultID = rs.getInt("resultID");
                    int rowNum = rs.getInt("Count");

                    TblResultDTO dto = new TblResultDTO(userID, subjectID, point, submitDate, resultID, rowNum);

                    if (this.resultList == null) {
                        this.resultList = new ArrayList<>();
                    }

                    this.resultList.add(dto);
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

    public void loadResult(String user, int count, String subject)
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
                        + "(SELECT userID, subjectID, point, submitDate, resultID, ROW_NUMBER() OVER(ORDER BY userID) AS rowNum   "
                        + "FROM tblResult "
                        + "WHERE userID = ? "
                        + "AND subjectID = ?) "
                        + "SELECT userID, subjectID, point, submitDate, resultID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, user);
                stm.setString(2, subject);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String subjectID = rs.getString("subjectID");
                    float point = rs.getFloat("point");
                    Timestamp dateSubmit = rs.getTimestamp("submitDate");
                    int resultID = rs.getInt("resultID");
                    int rowNum = rs.getInt("Count");

                    TblResultDTO dto = new TblResultDTO(userID, subjectID, point, dateSubmit, resultID, rowNum);

                    if (this.resultList == null) {
                        this.resultList = new ArrayList<>();
                    }

                    this.resultList.add(dto);
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

    public void loadResult(String user, int count, List<String> subjects)
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
                        + "(SELECT userID, subjectID, point, submitDate, resultID, ROW_NUMBER() OVER(ORDER BY userID) AS rowNum   "
                        + "FROM tblResult "
                        + "WHERE userID = ? "
                        + "AND subjectID = ? ";
                for (int i = 1; i < subjects.size(); i++) {
                    sql = sql + "OR subjectID = ? ";
                }
                sql = sql + ")SELECT userID, subjectID, point, submitDate, resultID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, user);
                stm.setString(2, subjects.get(0));
                for (int i = 1; i < subjects.size(); i++) {
                    stm.setString(i + 2, subjects.get(i));
                }
                stm.setInt(subjects.size() + 2, count);
                stm.setInt(subjects.size() + 3, count + 19);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String subjectID = rs.getString("subjectID");
                    float point = rs.getFloat("point");
                    Timestamp dateSubmit = rs.getTimestamp("submitDate");
                    int resultID = rs.getInt("resultID");
                    int rowNum = rs.getInt("Count");

                    TblResultDTO dto = new TblResultDTO(userID, subjectID, point, dateSubmit, resultID, rowNum);

                    if (this.resultList == null) {
                        this.resultList = new ArrayList<>();
                    }

                    this.resultList.add(dto);
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

    public boolean addResult(String userID, String subjectID, double point) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblResult(userID, subjectID, point) "
                        + "Values(?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, subjectID);
                stm.setDouble(3, point);
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

    public int getLastID()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int id = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT TOP 1 resultID "
                        + "FROM tblResult "
                        + "ORDER BY resultID DESC";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    id = rs.getInt("resultID");
                }// end if
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

}
