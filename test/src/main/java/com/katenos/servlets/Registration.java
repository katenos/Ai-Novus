/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.katenos.servlets;

import com.katenos.db.DAO;
import com.katenos.db.DAOException;
import com.katenos.db.Database;
import com.katenos.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class Registration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String REG_LOGIN = "^[a-z0-9]{4,}$";
    private final String REG_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        HashMap data;
        String username = request.getParameter("username").toLowerCase().trim();
        String password = request.getParameter("password");
        String password_doubling = request.getParameter("password_doubling");
        try (PrintWriter out = response.getWriter()) {
            data = checkingData(username, password, password_doubling);
            if (!data.containsKey("message")) {
                DAO dao = new Database();
                User user = new User(0, username, password);
                dao.addUser(user);
                out.println(new JSONObject().put("message", "Created").toString());
            } else {
                out.println(new JSONObject().put("message", data.get("message")).toString());
            }
        }
    }

    private HashMap checkingData(String username, String password, String password_doubling) throws DAOException {
        HashMap data = new HashMap();
        DAO dao = new Database();
        if (!password.equals(password_doubling)) {
            data.put("message", "Пароль и повтор пароля не совпадают");            
        } else {
            if (!reg(REG_PASSWORD, password)) {
                data.put("message", "Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов.");
            }
        }
        if (!reg(REG_LOGIN, username)) {
            data.put("message", "Имя пользователя должно быть длиннее 4 символов и состоять из цифр и букв английского алфавита.");
        }
        if (dao.userExist(username)) {
            data.put("message", "Пользователь с таким именем уже зарегестрирован.");
        }
        return data;
    }

    private boolean reg(String reg, String inputStr) {
        Pattern p = Pattern.compile(reg, Pattern.UNICODE_CASE);
        Matcher mat = p.matcher(inputStr);
        return mat.matches();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
