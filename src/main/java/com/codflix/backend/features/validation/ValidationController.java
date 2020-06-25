package com.codflix.backend.features.validation;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Database;
import com.codflix.backend.core.Template;
import com.codflix.backend.utils.URLUtils;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActivateAccount
 */
@WebServlet("/ValidationController")
public class ValidationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ValidationDao validation = new ValidationDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public String validation(Request request, Response response) {

        //If there is an active session, redirect to the logged root route
        Session session = request.session(false);
        if (session != null) {
            response.redirect(Conf.ROUTE_LOGGED_ROOT);
            return "";
        }
        String email = request.queryParams("key1");
        System.out.println(email);
        if(validation.verifyAccount(email)){
            Map<String, Object> model = new HashMap<>();
            return Template.render("validation.html", model);
        }
        else{
            Map<String, Object> model = new HashMap<>();
            return Template.render("home.html", model);
        }
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}