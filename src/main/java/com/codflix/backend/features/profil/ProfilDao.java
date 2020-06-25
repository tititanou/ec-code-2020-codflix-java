package com.codflix.backend.features.profil;

import com.codflix.backend.core.Database;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfilDao {
    UserDao userDao = new UserDao();
    public User getUserById(int id){
        User user = userDao.getUserById(id);
        return user;
    }

    public void changeEmail(String email,int id) {
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE user SET email =? WHERE id=?");
            st.setString(1, email);
            st.setInt(2, id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(String password,int id){
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE user SET password =? WHERE id=?");
            st.setString(1, password);
            st.setInt(2, id);
            st.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}