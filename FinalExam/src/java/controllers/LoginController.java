/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Hậu Phan
 */

public class LoginController extends HttpServlet {
    private static final String ERROR="login.jsp";
    private static final String US="US";
    private static final String USER_PAGE="user.jsp";
    private static final String MN="MN";
    private static final String MANAGER_PAGE="manager.jsp";
    private static final String ST="ST";
    private static final String STAFF_PAGE="staff.jsp";

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String url= ERROR;
       try{
           String userId=request.getParameter("userId");
           String password = request.getParameter("password");
           UserDAO dao = new UserDAO();
           UserDTO loginUser=dao.checkLogin(userId,password);
           if (loginUser !=null) {
               HttpSession session = request.getSession();
               session.setAttribute("LOGIN_USER", loginUser);
               String role = loginUser.getRole();
               if (US.equals(role) ) {
                   url=USER_PAGE;
               }else if (MN.equals(role)) {
                   url=MANAGER_PAGE;
               }else if (ST.equals(role)) {
                   url=STAFF_PAGE;
               }
               else {
                   request.setAttribute("ERROR", "Your role is not support yet!");
               }
           }else{
               request.setAttribute("ERROR","incorrect userID or password" );
           }
       }catch(Exception e){
           log("error at LoginController: " + e.toString() );
       }finally{
           request.getRequestDispatcher(url).forward(request, response);
       }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
