package com.codflix.backend.features.validation;

import com.codflix.backend.core.Database;
import com.codflix.backend.core.Template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class ValidationDao {
    public Boolean verifyAccount(String email){
        Connection conn = Database.getConnection();

        try {
            PreparedStatement pst1 = conn.prepareStatement("update user set active='1' where Email=? AND active='0'");
            //PreparedStatement pst1 = conn.prepareStatement("update user_details set Active='1' where Email=? and Hash=?");
            pst1.setString(1, email);
            //pst1.setString(2, hash);

            int i = pst1.executeUpdate();

            if(i == 1) {
                Map<String, Object> model = new HashMap<>();
                return true;
            }else {
                Map<String, Object> model = new HashMap<>();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error at ActivateAccount.java: " + e);
            return false;
        }
    }
}
