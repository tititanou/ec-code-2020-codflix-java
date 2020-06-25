package com.codflix.backend.features.profil;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.AuthController;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.URLUtils;
import spark.Request;
import spark.Response;
import spark.Session;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ProfilController {
    private final UserDao userDao = new UserDao();
    private final ProfilDao profilDao = new ProfilDao();
    private static final JOptionPane d = new JOptionPane();
    private final AuthController authController = new AuthController();
    User user;
    public String profil(Request request, Response response) {

        // If there is an active session, redirect to the logged root route
        Session session = request.session(true);
        int userId = session.attribute("user_id");
        user = userDao.getUserById(userId);
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return Template.render("profil.html", model);
    }
    public String modifyEmail(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        if (request.requestMethod().equals("GET")) {
            return Template.render("profil.html", model);
        }
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        if (!email.isEmpty()){
            profilDao.changeEmail(email,user.getId());
            d.showMessageDialog(null, "Votre adresse mail a bien été modifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return Template.render("profil.html", model);
        } else {
            d.showMessageDialog(null, "Votre adresse mail n'a été modifiée. Veuillez réessayer.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return Template.render("profil.html", model);
        }
    }

    public String modifyPassword(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        if (request.requestMethod().equals("GET")) {
            return Template.render("profil.html", model);
        }
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String password = query.get("password");
        String confirmPassword = query.get("confirmPassword");
        if (!password.isEmpty() && authController.hash(password).equals(user.getPassword())) {
            if (!confirmPassword.isEmpty()) {
                profilDao.changePassword(authController.hash(confirmPassword), user.getId());
                d.showMessageDialog(null, "Votre mot de passe a bien été modifié", "Information", JOptionPane.INFORMATION_MESSAGE);
                return Template.render("profil.html", model);
            } else {
                d.showMessageDialog(null, "Votre mot de passe  n'a pas été modifié. Veuillez réessayer.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return Template.render("profil.html", model);
            }
        }else {
            d.showMessageDialog(null, "Votre mot de passe  n'a pas été modifié. Veuillez réessayer.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return Template.render("profil.html", model);
        }
    }
}
