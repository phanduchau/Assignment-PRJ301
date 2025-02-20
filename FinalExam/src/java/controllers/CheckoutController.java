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
import shopping.Cart;
import shopping.Product;
import shopping.ProductDAO;
import user.UserDTO;


/**
 *
 * @author Hậu Phan
 */
public class CheckoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "user.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
               String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null && !cart.getCart().isEmpty()) {
                    ProductDAO dao = new ProductDAO();
                    boolean checkQuantity = true;
                    int total = 0;
                    for (Product product : cart.getCart().values()) {
                        if (!dao.checkQuantity(product.getMobileId(), product.getQuantity())) {
                            checkQuantity = false;
                            request.setAttribute("ERROR", "Not enough quantity for " + product.getMobileId());
                            break;
                        }
                        total += product.getQuantity() * product.getPrice();
                    }
                    if (checkQuantity) {
                        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                        if (user != null) {
                            String userId = user.getUserId();

                            int orderID = dao.createOrder(userId, total);
                            if (orderID != 0) {
                                boolean checkInsertOrder = dao.insertCartIntoOrderDetail(cart, orderID);
                                if (checkInsertOrder) {
                                    session.setAttribute("CART", null);
                                    request.setAttribute("THANKYOU", "Thank you for shopping!");
                                    url = SUCCESS;
                                } else {
                                    request.setAttribute("ERROR", "Cannot insert to Cart");
                                }
                            } else {
                                request.setAttribute("ERROR", "Cannot create Cart.");
                            }
                        } else {
                            request.setAttribute("ERROR", "User had not login");
                            url = "login.jsp";
                        }
                    }
                } else {
                    request.setAttribute("ERROR", "Empty Cart.");
                }
            } else {
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
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
