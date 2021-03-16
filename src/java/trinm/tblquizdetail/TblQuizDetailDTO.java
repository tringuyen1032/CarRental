/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblquizdetail;

import java.io.Serializable;

/**
 *
 * @author tring
 */
public class TblQuizDetailDTO implements Serializable{
    private int quizID;
    private String userAnswer;
    private int resultID;

    public TblQuizDetailDTO(int quizID, String userAnswer, int resultID) {
        this.quizID = quizID;
        this.userAnswer = userAnswer;
        this.resultID = resultID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }
    
    
}
