/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.registration;

import java.io.Serializable;

/**
 *
 * @author tahoa
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMached;
    private String usernameisExisted;

    public RegistrationCreateError() {
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
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the confirmNotMached
     */
    public String getConfirmNotMached() {
        return confirmNotMached;
    }

    /**
     * @param confirmNotMached the confirmNotMached to set
     */
    public void setConfirmNotMached(String confirmNotMached) {
        this.confirmNotMached = confirmNotMached;
    }

    /**
     * @return the usernameisExisted
     */
    public String getUsernameisExisted() {
        return usernameisExisted;
    }

    /**
     * @param usernameisExisted the usernameisExisted to set
     */
    public void setUsernameisExisted(String usernameisExisted) {
        this.usernameisExisted = usernameisExisted;
    }
    
    
}
