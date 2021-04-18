/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tbluser;

import java.io.Serializable;

/**
 *
 * @author tring
 */
public class TblUserCreateError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullnameLengthErr;
    private String confirmationNotMatched;
    private String usernameIsExisted;
    private String phoneLengthErr;
    private String phoneTypeErr;
    private String addressLengthErr;

    public TblUserCreateError() {
    }
    
    public TblUserCreateError(String usernameLengthErr, String passwordLengthErr, String fullnameLengthErr, String confirmationNotMatched, String usernameIsExisted, String phoneLengthErr, String phoneTypeErr, String addressLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.fullnameLengthErr = fullnameLengthErr;
        this.confirmationNotMatched = confirmationNotMatched;
        this.usernameIsExisted = usernameIsExisted;
        this.phoneLengthErr = phoneLengthErr;
        this.phoneTypeErr = phoneTypeErr;
        this.addressLengthErr = addressLengthErr;
    }


    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the fullnameLengthErr
     */
    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    /**
     * @param fullnameLengthErr the fullnameLengthErr to set
     */
    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmationNotMatched() {
        return confirmationNotMatched;
    }

    /**
     * @param confirmationNotMatched the confirmNotMatched to set
     */
    public void setConfirmationNotMatched(String confirmationNotMatched) {
        this.confirmationNotMatched = confirmationNotMatched;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    /**
     * @return the phoneLengthErr
     */
    public String getPhoneLengthErr() {
        return phoneLengthErr;
    }

    /**
     * @param phoneLengthErr the phoneLengthErr to set
     */
    public void setPhoneLengthErr(String phoneLengthErr) {
        this.phoneLengthErr = phoneLengthErr;
    }

    /**
     * @return the phoneTypeErr
     */
    public String getPhoneTypeErr() {
        return phoneTypeErr;
    }

    /**
     * @param phoneTypeErr the phoneTypeErr to set
     */
    public void setPhoneTypeErr(String phoneTypeErr) {
        this.phoneTypeErr = phoneTypeErr;
    }

    /**
     * @return the addressLengthErr
     */
    public String getAddressLengthErr() {
        return addressLengthErr;
    }

    /**
     * @param addressLengthErr the addressLengthErr to set
     */
    public void setAddressLengthErr(String addressLengthErr) {
        this.addressLengthErr = addressLengthErr;
    }
    
    
}
