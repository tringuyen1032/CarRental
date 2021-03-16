/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblquizdetail;

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
 * @author tring
 */
public class TblQuizDetailDAO implements Serializable {

    private List<TblQuizDetailDTO> detailList;

    public List<TblQuizDetailDTO> getDetailList() {
        return detailList;
    }

    public void loadDetail(int resutlID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "SELECT quizID, userAnswer, resultID "
                        + "FROM tblQuizDetail "
                        + "WHERE resultID = ?";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setInt(1, resutlID);
                //4.    Execute Query
                rs = stm.executeQuery();
                //5.    Process result
                while (rs.next()) {
                    int quizID = rs.getInt("quizID");
                    String userAnswer = rs.getString("userAnswer");
                    int resultID = rs.getInt("resultID");

                    TblQuizDetailDTO dto = new TblQuizDetailDTO(quizID, userAnswer, resultID);

                    if (this.detailList == null) {
                        this.detailList = new ArrayList<>();
                    }

                    this.detailList.add(dto);
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

    public boolean addDetail(String userID, int quizID, String userAnswer, int resultID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean rt = false;

        try {
            //1.    connect DB
            con = DBHelper.makeConnection();
            //2.    Create SQL String
            if (con != null) {
                String sql = "INSERT INTO tblQuizDetail(userID, quizID, userAnswer, resultID) "
                        + "Values(?, ?, ?, ?)";
                //3.    Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setInt(2, quizID);
                stm.setString(3, userAnswer);
                stm.setInt(4, resultID);
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
