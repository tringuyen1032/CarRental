/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblquestion;

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
 * @author trinm
 */
public class TblQuestionDAO implements Serializable{
    
    private List<TblQuestionDTO> questionList;

    public List<TblQuestionDTO> getQuestionList() {
        return questionList;
    }
    
    public void searchQuestion(int count)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum  "
                        + "FROM tblQuestion) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY question";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, count);
                stm.setInt(2, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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
    
    public void searchQuestion(int count, String searchBySubject)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum "
                        + "FROM tblQuestion "
                        + "WHERE subjectID = ?) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE rowNum BETWEEN ? AND ? ";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, searchBySubject);
                stm.setInt(2, count);
                stm.setInt(3, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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
    
    public void searchQuestionByStatus(String searchValue, int count, String stt)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum  "
                        + "FROM tblQuestion "
                        + "WHERE question LIKE ? AND status = ?) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY question";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, stt);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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

    public void searchQuestion(String searchValue, int count)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum  "
                        + "FROM tblQuestion "
                        + "WHERE question LIKE ?) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY question";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, count);
                stm.setInt(3, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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
    
    public void searchQuestion(String searchValue, String searchBySubject, int count)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum "
                        + "FROM tblQuestion "
                        + "WHERE question LIKE ? AND subjectID = ?) "
                        + "SELECT questionID, question, correct, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE rowNum BETWEEN ? AND ? ";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, searchBySubject);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);
                    
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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

    public void searchQuestionByStatus(int count, String stt)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum  "
                        + "FROM tblQuestion "
                        + "WHERE status = ?) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE (rowNum BETWEEN ? AND ?) "
                        + "ORDER BY question";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, stt);
                stm.setInt(2, count);
                stm.setInt(3, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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

    public void searchQuestionByStatus(int count, String searchBySubject, String stt)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum "
                        + "FROM tblQuestion "
                        + "WHERE subjectID = ? AND status = ?) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE rowNum BETWEEN ? AND ? ";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, searchBySubject);
                stm.setString(2, stt);
                stm.setInt(3, count);
                stm.setInt(4, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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
    
    public void searchQuestionByStatus(String searchValue, String searchBySubject, int count, String stt)
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
                        + "(SELECT questionID, question, status, subjectID, ROW_NUMBER() OVER(ORDER BY question) AS rowNum "
                        + "FROM tblQuestion "
                        + "WHERE question LIKE ? AND subjectID = ? AND status = ?) "
                        + "SELECT questionID, question, status, subjectID, (SELECT MAX(rowNum) "
                        + "FROM RESULT) AS Count "
                        + "FROM RESULT "
                        + "WHERE rowNum BETWEEN ? AND ? ";

                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, searchBySubject);
                stm.setString(3, stt);
                stm.setInt(4, count);
                stm.setInt(5, count + 19);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    String question = rs.getString("question");
                    String subject = rs.getString("subjectID");
                    String status = rs.getString("status");
                    int rowNum = rs.getInt("Count");
                    int ID = rs.getInt("questionID");

                    TblQuestionDTO dto = new TblQuestionDTO(question, subject, status, rowNum, ID);

                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }

                    this.questionList.add(dto);
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

    public boolean deleteQuestion(int ID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "UPDATE tblQuestion "
                        + "SET status = ? "
                        + "WHERE questionID = ? ";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "deActive");
                stm.setInt(2, ID);
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
    
    public boolean updateQuestion(int ID, String question, String subject, String status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "UPDATE tblQuestion "
                        + "SET question = ?, subjectID = ?, status = ? "
                        + "WHERE questionID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, question);
                stm.setString(2, subject);
                stm.setInt(4, ID);
                stm.setString(3, status);

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
    
    private List<TblQuestionDTO> quizList;

    public List<TblQuestionDTO> getQuizList() {
        return quizList;
    }

    private Time time;

    public Time getDoQuizTime() {
        return time;
    }

    public void loadQuiz(String subjectID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT TOP 20 Q.questionID, Q.question, S.timeDoQuiz "
                        + "FROM tblQuestion Q, tblSubject S "
                        + "WHERE Q.status = ? "
                        + "AND Q.subjectID = ? "
                        + "AND Q.subjectID = S.subjectID "
                        + "ORDER BY NEWID()";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "Active");
                stm.setString(2, subjectID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int ID = rs.getInt("questionID");
                    String question = rs.getString("question");
                    Time timeDoQuiz = rs.getTime("timeDoQuiz");

                    TblQuestionDTO dto = new TblQuestionDTO(ID, question, timeDoQuiz, subjectID);

                    if (this.quizList == null) {
                        this.quizList = new ArrayList<>();
                    }

                    this.quizList.add(dto);
                    this.time = timeDoQuiz;
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

    public void loadQuizByID(int quizID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT questionID, question "
                        + "FROM tblQuestion "
                        + "WHERE questionID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    int ID = rs.getInt("questionID");
                    String question = rs.getString("question");

                    TblQuestionDTO dto = new TblQuestionDTO(ID, question);

                    if (this.quizList == null) {
                        this.quizList = new ArrayList<>();
                    }

                    this.quizList.add(dto);
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

    public int getLastQuestionID()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rt = 0;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT TOP 1 questionID "
                        + "FROM tblQuestion "
                        + "ORDER BY questionID DESC";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                if (rs.next()) {
                    rt = rs.getInt("questionID");
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
        return  rt;
    }
    
    public boolean addQuiz(String question, String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblQuestion(question, status, subjectID) "
                        + "Values(?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, question);
                stm.setString(2, "Active");
                stm.setString(3, subject);
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

    
    public boolean addAnswer(int questionID, String answer, boolean correct) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblAnswer(questionID, answer, status) "
                        + "Values(?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, questionID);
                stm.setString(2, answer);
                stm.setBoolean(3, correct);
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
