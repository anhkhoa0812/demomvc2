/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoatha.utils.DBHelper;

/**
 *
 * @author tahoa
 */
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;

        try {

            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute querry
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    //result = true;
                    //mapping
                    //5.1 get data from result set
                    String fullName = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO
                    result = new RegistrationDTO(username, password, fullName, role);
                } // end username and password are existed
            } //end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {

            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Excute querry
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    //5.1 Get data from rs
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data into DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    //5.3 add DTO into List
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    } //end acconuts had NOT existed
                    this.accounts.add(dto);
                } // end traverse rs to get data

            } //end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteAccount(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {

            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ?";
                //3.Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute querry
                int effectRows = stm.executeUpdate();
                //5. Process
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. Execute querry
                int effectRows = stm.executeUpdate();
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            }   //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

//    public boolean createAccount(String username, String password, String fullName, boolean role) throws SQLException, NamingException {
//        boolean result = false;
//        Connection con = null;
//        PreparedStatement stm = null;
//        try {
//            //Make connection
//            con = DBHelper.createConnection();
//            if(con != null) {
//                //Create SQL String
//                String sql = "INSERT INTO Registration( "
//                        + "username, password, lastname, isAdmin) "
//                        + "Values(?, ?, ?, ?)";
//                //Create Statement
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                stm.setString(2, password);
//                stm.setString(3, fullName);
//                stm.setBoolean(4, role);
//                //Execute querry
//                int  effectRow = stm.executeUpdate();
//                //Process result
//                if(effectRow > 0) {
//                    result = true;
//                }
//            }
//        } finally {
//            if(stm != null) {
//                stm.close();
//            }
//            if(con != null) {
//                con.close();
//            }
//        }
//        return result;
//    }
    public boolean createAccount(RegistrationDTO account) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {

            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Insert Into Registration ("
                        + "username, password, lastname, isAdmin"
                        + ") Values ("
                        + "?, ?, ?, ?"
                        + ")";
                //3.Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4.Execute querry
                int effectRows = stm.executeUpdate();
                //5. Process
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
