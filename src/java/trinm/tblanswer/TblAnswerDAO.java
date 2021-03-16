/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblanswer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import trinm.utilities.DBHelper;

/**
 *
 * @author trinm
 */
public class TblAnswerDAO implements Serializable{

    private List<TblAnswerDTO> answerList;

    public List<TblAnswerDTO> getAnswerList() {
        return answerList;
    }
    
    private Map<Integer, Integer> countList;

    public Map<Integer, Integer> getCountList() {
        return countList;
    }

    public void searchAnswer(int ID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {

                String sql = "SELECT questionID, answer, status "
                        + "FROM tblAnswer "
                        + "WHERE questionID = ?";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int questionID = rs.getInt("questionID");
                    String answer = rs.getString("answer");
                    boolean correct = rs.getBoolean("status");
                    
                    TblAnswerDTO dto = new TblAnswerDTO(questionID, answer, correct);

                    if (this.answerList == null) {
                        this.answerList = new ArrayList<>();
                    }

                    this.answerList.add(dto);
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
        
        public void getCount(int ID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {

                String sql = "SELECT COUNT(questionID) as count "
                        + "FROM tblAnswer "
                        + "WHERE questionID = ? "
                        + "group by questionID";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    int count = rs.getInt("count");
                    

                    if (this.countList == null) {
                        this.countList = new HashMap<>();
                    }

                    this.countList.put(ID, count);
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
        
        public boolean updateAnswer(int correct, int ID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;
        
        TblAnswerDAO dao = new TblAnswerDAO();
        
        dao.searchAnswer(ID);
        List<TblAnswerDTO> answerList = dao.getAnswerList();

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "UPDATE tblAnswer "
                        + "SET status = 1 "
                        + "WHERE questionID = ? AND answer LIKE ? "
                        + "UPDATE tblAnswer "
                        + "SET status = 0 "
                        + "WHERE questionID = ? AND answer NOT LIKE ? ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                stm.setString(2, answerList.get(correct - 1).getAnswer());
                stm.setInt(3, ID);
                stm.setString(4, answerList.get(correct - 1).getAnswer());
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

        public boolean checkCorrect(int ID, String answer) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT questionID "
                        + "FROM tblAnswer "
                        + "WHERE questionID = ? AND answer LIKE ? AND status = 1 ";
                        
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                stm.setString(2, answer);

                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    rt = true;
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
