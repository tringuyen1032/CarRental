/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblquestion;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author tring
 */
public class TblQuestionDTO implements Serializable {

    private String question;
    private String subject;
    String status;
    int rowNum;
    int ID;
    private Time timeDoQuiz;

    public TblQuestionDTO(String question, String subject, String status, int rowNum, int ID) {
        this.question = question;
        this.subject = subject;
        this.status = status;
        this.rowNum = rowNum;
        this.ID = ID;
    }
    
    public TblQuestionDTO(int ID, String question, Time timeDoQuiz, String subject) {
        this.question = question;
        this.timeDoQuiz = timeDoQuiz;
        this.ID = ID;
        this.subject = subject;
    }
    
     public TblQuestionDTO(int ID, String question) {
        this.question = question;
        this.ID = ID;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * @param correct the correct to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Time getTimeDoQuiz() {
        return timeDoQuiz;
    }

    public void setTimeDoQuiz(Time timeDoQuiz) {
        this.timeDoQuiz = timeDoQuiz;
    }

}
