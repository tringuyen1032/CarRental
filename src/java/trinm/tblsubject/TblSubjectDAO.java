/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblsubject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author tring
 */
public class TblSubjectDAO implements Serializable {
    
    
    private List<String> subjectLoad;

    public List<String> getSubjectLoad() {
        return subjectLoad;
    }

    public void loadSubject()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT subjectID "
                        + "FROM tblSubject ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String subject = rs.getString("subjectID");

                    if (this.subjectLoad == null) {
                        this.subjectLoad = new ArrayList<>();
                    }

                    this.subjectLoad.add(subject);
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

    private List<TblSubjectDTO> subjectList;

    public List<TblSubjectDTO> getSubjectList() {
        return subjectList;
    }

    public void getSubject() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT subjectID, subjectName, timeDoQuiz "
                        + "FROM tblSubject ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    String subjectName = rs.getString("subjectName");
                    Time timeDoQuiz = rs.getTime("timeDoQuiz");
                    TblSubjectDTO dto = new TblSubjectDTO(subjectID, subjectName, timeDoQuiz);

                    if (this.subjectList == null) {
                        this.subjectList = new ArrayList<>();
                    }

                    this.subjectList.add(dto);
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
    }

    private List<String> listSubjectID;

    public List<String> getListSubjectID() {
        return listSubjectID;
    }

    public void getSubjectID(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT subjectID "
                        + "FROM tblSubject "
                        + "WHERE subjectName LIKE ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    if (this.listSubjectID == null) {
                        this.listSubjectID = new ArrayList<>();
                    }

                    this.listSubjectID.add(subjectID);
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
    }

}
