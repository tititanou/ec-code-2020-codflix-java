package com.codflix.backend.features.user;

import com.codflix.backend.App;
import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.features.message.SendingVerificationMail;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.URLUtils;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Session;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final JOptionPane d = new JOptionPane();
    private final UserDao userDao = new UserDao();

    public String login(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_login.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password = query.get("password");
        String pwd = hash(password);

        // Authenticate user
        User user = userDao.getUserByCredentials(email, pwd);
        if (user == null) {
            logger.info("User not found. Redirect to login");
            d.showMessageDialog(null,"Identifiant et/ou Mot de passe invalide.", "Information", JOptionPane.INFORMATION_MESSAGE);
            response.removeCookie("session");
            response.redirect("/login");
            return "KO";
        }
        if(user.getStatus()==0){
            logger.info("Account not verified. Send a new verification mail");
            d.showMessageDialog(null,"Votre compte n'a pas été vérifié", "Information", JOptionPane.INFORMATION_MESSAGE);
            // Generate Hash Code which helps in creating Activation Link
            Random theRandom = new Random();
            theRandom.nextInt(999999);
            String myHash = hash("" +	theRandom);
            SendingVerificationMail juju= new SendingVerificationMail(user, myHash);
            juju.sendMail();
            response.removeCookie("session");
            response.redirect("/");
            return "KO";
        }

        // Create session
        Session session = request.session(true);
        session.attribute("user_id", user.getId());
        response.cookie("/", "user_id", "" + user.getId(), 3600, true);

        // Redirect to medias page
        response.redirect(Conf.ROUTE_LOGGED_ROOT);
        return "OK";
    }

    public String signUp(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        if (request.requestMethod().equals("GET")) {
            return Template.render("auth_signup.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password = query.get("password");
        String passwordConfirm = query.get("password_confirm");
        System.out.println(email);
        System.out.println(password);
        System.out.println(passwordConfirm);

        // Check if passwords match
        if (!password.equals(passwordConfirm) ) {
            logger.info("Password don't match");
            d.showMessageDialog(null,"Les mots de passe ne correspondent pas.", "Information", JOptionPane.INFORMATION_MESSAGE);
            response.removeCookie("session");
            response.redirect("/signup");
            return "KO";
        }

        User user = new User(email, password, 0);
        String pwd = hash(password);
        if(userDao.insertNewUser(email,pwd)) {
            Random theRandom = new Random();
            theRandom.nextInt(999999);
            String myHash = hash("" + theRandom);
            SendingVerificationMail verif = new SendingVerificationMail(user, myHash);
            verif.sendMail();
            d.showMessageDialog(null,"Vous allez recevoir un mail pour valider votre compte.", "Information", JOptionPane.INFORMATION_MESSAGE);
            response.removeCookie("session");
            response.redirect("/");
            return "=ok";
        }else {
            logger.info("User not created. Redirect to signup");
            d.showMessageDialog(null,"L'inscription a échoué. Veuillez recommencer", "Information", JOptionPane.INFORMATION_MESSAGE);
            response.removeCookie("session");
            response.redirect("/signup");
            return "KO";
        }

        /*
        RegisterDAO regDAO = new RegisterDAO();
			String s1 = regDAO.RegisterUser(rb);

			if(s1.equals("Success")) {
				response.sendRedirect("verify.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
        */
    }

    public String logout(Request request, Response response) {
        Session session = request.session(false);
        if (session != null) {
            session.invalidate();
        }
        response.removeCookie("session");
        response.removeCookie("JSESSIONID");
        response.redirect("/");

        return "";
    }

    public String hash(String password) {
        String passwordHashed = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        System.out.println(passwordHashed);
        return passwordHashed;
    }

}
