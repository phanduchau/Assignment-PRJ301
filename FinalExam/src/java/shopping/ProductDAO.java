/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import user.UserDTO;
import utils.DBUtils;

/**
 *
 * @author Hậu Phan
 */
public class ProductDAO {
    
    private static final String GETLISTPRODUCT = "SELECT mobileId,description,price,mobileName,yearOfProduction,image,quantity,notSale FROM tbl_Mobile WHERE mobileName like ?";
    
    private static final String SEARCH = "SELECT mobileId,description,price,mobileName,yearOfProduction,image,quantity,notSale FROM tbl_Mobile WHERE mobileId like ? OR mobileName like ?";
   
    private static final String UPDATE = "UPDATE tbl_Mobile SET description=?, price=?, quantity=?, notSale=? WHERE mobileId=?";
    
    private static final String DELETE = "DELETE tbl_Mobile WHERE mobileId=?";
    
    private static final String CHECK_DUPLICATE = "SELECT mobileName FROM tbl_Mobile WHERE mobileId=?";
    
    private static final String INSERT = "INSERT INTO tbl_Mobile(mobileId, description, price, mobileName, yearOfProduction,image, quantity, notSale) VALUES(?,?,?,?,?,?,?,?)";
    
    private static final String PRICE_RANGE_SEARCH = "SELECT mobileId, description, price, mobileName, yearOfProduction,image, quantity, notSale FROM tbl_Mobile WHERE price BETWEEN ? AND ?";
    
    private static final String LIST_MOBILE = "SELECT mobileId, description, price, mobileName, yearOfProduction,image, quantity, notSale FROM tbl_Mobile";
    
    private static final String ADD_WISHLIST = "INSERT INTO tbl_Wishlist(userId, mobileId) VALUES (?,?)";
    
    private static final String SELECT_WISHLIST_BY_USERID = "SELECT wl.mobileId, m.description, m.price, m.mobileName, m.yearOfProduction,m.image, m.quantity, m.notSale "
            + "FROM tbl_Wishlist wl "
            + "JOIN tbl_Mobile m ON wl.mobileId = m.mobileId "
            + "WHERE wl.userId = ?";
    
    private static final String CHECK_DUPLICATE_WISHLIST = "SELECT * FROM tbl_Wishlist WHERE userId = ? AND mobileId = ?";
    
    private static final String REMOVE_WISHLIST = "DELETE tbl_Wishlist WHERE userId = ? AND mobileId = ?";
    
    private static final String CHECK = "SELECT mobileId FROM tbl_Mobile WHERE mobileId = ? AND quantity >= ?";
    
    private static final String GET_QUANTITY = "SELECT quantity FROM tbl_Mobile WHERE mobileId = ?";
    
    private static final String CREATE_ORDER = "INSERT INTO tbl_Order(userId, total, dateBuy) VALUES (?,?,GETDATE())";
    
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO tbl_OrderDetail(orderDetailID,orderID,mobileId,mobileName,quantity,price) VALUES (?,?,?,?,?,?)"; 
    
    private static final String SELECT_LAST_INSERTED_ORDER_ID = "SELECT IDENT_CURRENT('tbl_Order')";
    
    private static final String SELECT_LAST_INSERTED_ORDER_DETAIL_ID = "SELECT * FROM tbl_OrderDetail ORDER BY orderDetailID DESC";
    
    private static final String SET_PRODUCT_QUANTITY = "UPDATE tbl_Mobile SET quantity = ? WHERE mobileId = ?";

    
    
    public List<Product> ListProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLISTPRODUCT);
                ptm.setString(1, "%" + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    int notSale = rs.getInt("notSale");                  
                    list.add(new Product(mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }
    public List<Product> getListProduct(String search) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    int notSale = rs.getInt("notSale");                  
                    list.add(new Product(mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;

    }

    public boolean update(Product product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);            
                ptm.setString(1, product.getDescription());
                ptm.setFloat(2, product.getPrice());
                ptm.setInt(3, product.getQuantity());
                ptm.setInt(4, product.getNotSale());
                ptm.setString(5, product.getMobileId());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public boolean delete(String mobileId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, mobileId);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean checkDuplicateId(String mobileId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs= null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, mobileId);
                rs= ptm.executeQuery();
                if(rs.next()){
                    check=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();

        }
        return check;
    }
    public boolean checkDuplicateName(String mobileName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs= null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, mobileName);
                rs= ptm.executeQuery();
                if(rs.next()){
                    check=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();

        }
        return check;
    }

    public boolean insertV1(Product product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, product.getMobileId());
                ptm.setString(2, product.getDescription());
                ptm.setFloat(3, product.getPrice());
                ptm.setString(4, product.getMobileName());
                ptm.setInt(5, product.getYearOfProduction());
                ptm.setString(6, product.getImage());
                ptm.setInt(7, product.getQuantity());
                ptm.setInt(8, product.getNotSale());
                check = ptm.executeUpdate() > 0 ? true : false;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
    public List<Product> getListProductByPriceRange (String minPrice, String maxPrice) throws SQLException{
        List<Product> list= new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRICE_RANGE_SEARCH);
                ptm.setString(1, minPrice);
                ptm.setString(2, maxPrice);
                rs=ptm.executeQuery();
                
                while (rs.next()) {                    
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    int notSale = rs.getInt("notSale");
                    list.add(new Product(mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale));
                }
            }
        } catch (Exception e) {
        }
        finally{
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }        
    }
        return list;
      
    }  
    
    public boolean checkDuplicateWishlist(String userId, String mobileId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean isDuplicate = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_WISHLIST);
                ptm.setString(1, userId);
                ptm.setString(2, mobileId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    isDuplicate = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return isDuplicate;
    }

    public boolean addWishlist(String userId, Product mobile) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_WISHLIST);
                ptm.setString(1, userId);
                ptm.setString(2, mobile.getMobileId());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<Product> getWishlistByUserID(String userId) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Product> wishlist = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SELECT_WISHLIST_BY_USERID);
            ptm.setString(1, userId);
            rs = ptm.executeQuery();

            while (rs.next()) {
                String mobileId = rs.getString("mobileId");
                String description = rs.getString("description");
                Float price = rs.getFloat("price");
                String mobileName = rs.getString("mobileName");
                int yearOfProduction = rs.getInt("yearOfProduction");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                int notSale = rs.getInt("notSale");
                if (notSale == 0) {
                    Product product = new Product(mobileId, description, 0, mobileName, yearOfProduction, image, quantity, notSale);
                    wishlist.add(product);
                }
                 }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return wishlist;
    }

    public boolean removeWishlist(String userId, String mobileId) throws SQLException {
        boolean checkRemove = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(REMOVE_WISHLIST);
                ptm.setString(1, userId);
                ptm.setString(2, mobileId);
                checkRemove = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkRemove;
    }
     public List<Product> getAllList() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_MOBILE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    int notSale = rs.getInt("notSale");
                    list.add(new Product(mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
     
     public boolean checkQuantity(String mobileId, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK);
                ptm.setString(1, mobileId);
                ptm.setInt(2, quantity);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int getQuantity(String mobileId) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY);
                ptm.setString(1, mobileId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }

    public int createOrder(String userId, int total) throws SQLException { //return OrderID
        int generatedID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_ORDER);
                ptm.setString(1, userId);
                ptm.setDouble(2, total);
                ptm.executeUpdate();

                ptm = conn.prepareStatement(SELECT_LAST_INSERTED_ORDER_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    generatedID = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return generatedID;
    }

    public boolean insertCartIntoOrderDetail(Cart cart, int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int orderDetailID = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_LAST_INSERTED_ORDER_DETAIL_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    orderDetailID = rs.getInt(1);
                }
                orderDetailID++;
                ptm = conn.prepareStatement(CREATE_ORDER_DETAIL);
                for (Product product : cart.getCart().values()) {
                    ptm.setInt(1, orderDetailID);
                    ptm.setInt(2, orderID);
                    ptm.setString(3, product.getMobileId());
                    ptm.setString(4, product.getMobileName());
                    ptm.setInt(5, product.getQuantity());
                    ptm.setFloat(6, product.getPrice());
                    check = ptm.executeUpdate() > 0 ? true : false;
                }

                if (check) {
                    for (Product product : cart.getCart().values()) {
                        int currentQuantity = getQuantity(product.getMobileId());
                        ptm = conn.prepareStatement(SET_PRODUCT_QUANTITY);
                        ptm.setInt(1, currentQuantity - product.getQuantity());
                        ptm.setString(2, product.getMobileId());
                        check = ptm.executeUpdate() > 0 ? true : false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
