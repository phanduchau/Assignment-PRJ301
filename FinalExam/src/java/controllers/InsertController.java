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
import shopping.Product;
import shopping.ProductDAO;
import shopping.ProductError;

/**
 *
 * @author Hậu Phan
 */
public class InsertController extends HttpServlet {

    private static final String ERROR="insert.jsp";
    private static final String SUCCESS="staff.jsp";

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        boolean checkValidation = true;
        ProductError productError = new ProductError();
        ProductDAO dao = new ProductDAO();
        try {
            String mobileId= request.getParameter("mobileId");
            String description= request.getParameter("description");
            float price= Float.parseFloat(request.getParameter("price"));  
            String mobileName=request.getParameter("mobileName");
            int yearOfProduction=Integer.parseInt(request.getParameter("yearOfProduction"));
            String image =request.getParameter("image");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int notSale = Integer.parseInt(request.getParameter("notSale"));
            boolean checkDuplicateId = dao.checkDuplicateId(mobileId);
            boolean checkDuplicateName= dao.checkDuplicateName(mobileName);
            if (checkDuplicateId) {
                productError.setMobileIdError("Duplicate mobile Id!!!");
            }
            else if (checkDuplicateName) {
                productError.setMobileNameError("Duplicate mobile Name!!!");
            }
            if (checkValidation) {
                Product product = new Product(mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale);
                boolean check = dao.insertV1(product);
                if (check) {
                    url = SUCCESS;
                }
                else{
                    productError.setErrorError("Unknow error!");
                    request.setAttribute("PRODUCT_ERROR", productError);
                }
            }else{
                request.setAttribute("USER_ERROR", productError);
            }
            
        } catch (Exception e) {
    
        } finally{
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
