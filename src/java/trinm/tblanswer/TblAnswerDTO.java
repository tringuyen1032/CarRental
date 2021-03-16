/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblanswer;

import java.io.Serializable;

/**
 *
 * @author trinm
 */
public class TblAnswerDTO implements Serializable{
    
    private int ID;
    private String answer;
    private boolean correct;

    public TblAnswerDTO(int answerID, String answer, boolean correct) {
        this.ID = answerID;
        this.answer = answer;
        this.correct = correct;
    }

    /**
     * @return the questionID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setID(int answerID) {
        this.ID = answerID;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the correct
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    
    
}
