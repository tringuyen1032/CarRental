/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tbluser;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author tring
 */
public class TblUserHash implements Serializable{

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    private String password;

    public TblUserHash(String password) {
        this.password = password;
    }

    public String toHexString() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                int value = 0xFF & b;   //unsigned integer
                String toAppend = Integer.toHexString(value);
                sb.append(toAppend);
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            BasicConfigurator.configure();
            LOGGER.error("CreateNewAccountServlet_NoSuchAlgorithm: " + ex.getMessage());
        }
        return null;
    }

}
