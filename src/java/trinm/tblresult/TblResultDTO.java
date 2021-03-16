/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblresult;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author tring
 */
public class TblResultDTO implements Serializable{
    private String userID;
    private String subjectID;
    private Float point;
    private Timestamp dateSubmit;
    private int resultID;
    private int rowNum;

    public TblResultDTO(String userID, String subjectID, Float point, Timestamp dateSubmit, int resultID, int rowNum) {
        this.userID = userID;
        this.subjectID = subjectID;
        this.point = point;
        this.dateSubmit = dateSubmit;
        this.resultID = resultID;
        this.rowNum = rowNum;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the point
     */
    public Float getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(Float point) {
        this.point = point;
    }

    /**
     * @return the submitDate
     */
    public Timestamp getDateSubmit() {
        return dateSubmit;
    }

    /**
     * @param dateSubmit the submitDate to set
     */
    public void setDateSubmit(Timestamp dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    /**
     * @return the resultID
     */
    public int getResultID() {
        return resultID;
    }

    /**
     * @param resultID the resultID to set
     */
    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
    
}
