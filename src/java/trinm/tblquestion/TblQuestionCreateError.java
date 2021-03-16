/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblquestion;

import java.io.Serializable;

/**
 *
 * @author tring
 */
public class TblQuestionCreateError implements Serializable {

    private String questionNullErr;
    private String answerNullErr;
    private String correctNullErr;
    private String subjectNullErr;
    private String questionDuplicateErr;

    public TblQuestionCreateError() {
    }

    public TblQuestionCreateError(String questionNullErr, String answerNullErr,String correctNullErr, String subjectNullErr, String questionDuplicateErr) {
        this.questionNullErr = questionNullErr;
        this.answerNullErr = answerNullErr;
        this.correctNullErr = correctNullErr;
        this.subjectNullErr = subjectNullErr;
        this.questionDuplicateErr = questionDuplicateErr;
    }

    /**
     * @return the questionNullErr
     */
    public String getQuestionNullErr() {
        return questionNullErr;
    }

    /**
     * @param questionNullErr the questionNullErr to set
     */
    public void setQuestionNullErr(String questionNullErr) {
        this.questionNullErr = questionNullErr;
    }

    /**
     * @return the answer1NullErr
     */
    public String getAnswerNullErr() {
        return answerNullErr;
    }

    /**
     * @param answer1NullErr the answer1NullErr to set
     */
    public void setAnswerNullErr(String answerNullErr) {
        this.answerNullErr = answerNullErr;
    }

    public String getCorrectNullErr() {
        return correctNullErr;
    }

    /**
     * @param correctNullErr the correctNullErr to set
     */
    public void setCorrectNullErr(String correctNullErr) {
        this.correctNullErr = correctNullErr;
    }

    /**
     * @return the subjectNullErr
     */
    public String getSubjectNullErr() {
        return subjectNullErr;
    }

    /**
     * @param subjectNullErr the subjectNullErr to set
     */
    public void setSubjectNullErr(String subjectNullErr) {
        this.subjectNullErr = subjectNullErr;
    }

    public String getQuestionDuplicateErr() {
        return questionDuplicateErr;
    }

    public void setQuestionDuplicateErr(String questionDuplicateErr) {
        this.questionDuplicateErr = questionDuplicateErr;
    }

}
