/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.Product;
import shopping.ProductDAO;
import user.UserDTO;

/**
 *
 * @author Hậu Phan
 */
public class RemoveWishListController extends HttpServlet {
    private static final String ERROR = "user.jsp";
    private static final String SUCCESS = "user.jsp";


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String mobileId = request.getParameter("mobileId");
            Float price = Float.parseFloat(request.getParameter("price"));
            String mobileName = request.getParameter("mobileName");

            HttpSession session = request.getSession();
            if (session != null) {

                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                if (user != null) {
                    String userId = user.getUserId();                
                    Product mobile = new Product(mobileId, "", price, mobileName, 0, url, 0, 0);
                    ProductDAO dao = new ProductDAO();
                    boolean checkWishlist = dao.checkDuplicateWishlist(userId, mobileId);
                    if (!checkWishlist) {
                        boolean addedToWishlist = dao.addWishlist(userId, mobile);
                        if (addedToWishlist) {
                            request.setAttribute("MESSAGE", "The mobile has been successfully added to wishlist");
                            url = SUCCESS;
                        } else {
                            request.setAttribute("ERROR", "Failed to add mobile to wishlist");
                        }
                    } else {
                        request.setAttribute("ERROR", "The mobile is already in Wishlist");
                    }
                }
            } else {
                request.setAttribute("ERROR", "User had not login");
                url = "login.jsp";
            }
        } catch (Exception e) {
            log("Error at AddWishlistController: " + e.toString());
        } finally {
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
