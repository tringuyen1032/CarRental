/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.utilities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tring
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws NamingException, SQLException {
        //1. load Driver
        Context context = new InitialContext();
        //2. Crate connection String
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        //3. open connection
        DataSource ds = (DataSource) tomcatContext.lookup("SE1422SE");
        //4. create connection from datasource
        Connection con = ds.getConnection();
        return con;
    }
}
