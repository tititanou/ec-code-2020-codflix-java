package com.codflix.backend.features.other;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.features.message.SendingContactMail;
import com.codflix.backend.utils.URLUtils;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class ContactController {
    public String contact(Request request, Response response) {

        // If there is an active session, redirect to the logged root route
        Session session = request.session(false);
        if (session != null) {
            response.redirect(Conf.ROUTE_LOGGED_ROOT);
            return "";
        }

        Map<String, Object> model = new HashMap<>();
        return Template.render("contact.html", model);
    }

    public String sendMail(Request request, Response response){
        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String sender = query.get("email");
        String subject = query.get("subject");
        String message = query.get("message");
        SendingContactMail contact= new SendingContactMail(sender, subject, message);
        contact.sendMail();
        response.removeCookie("session");
        response.redirect("/");
        return "OK";
    }

}
