/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblsubject;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author tring
 */
public class TblSubjectDTO implements Serializable{
    String subjectID;
    String subjectName;
    Time timeDoQuiz;
    
    public TblSubjectDTO(String subjectID, String subjectName, Time timeDoQuiz) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.timeDoQuiz = timeDoQuiz;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Time getTimeDoQuiz() {
        return timeDoQuiz;
    }

    public void setTimeDoQuiz(Time timeDoQuiz) {
        this.timeDoQuiz = timeDoQuiz;
    }
    
    
}
