/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hậu Phan
 */
public class WishList {
    private Map<String, Product> wishList;
    
    public WishList(){ 
    }

    public WishList(Map<String, Product> wishList) {
        this.wishList = wishList;
    }

    public Map<String, Product> getWishList() {
        return wishList;
    }

    public void setWishList(Map<String, Product> wishList) {
        this.wishList = wishList;
    }
    
    public boolean add(Product mobile) {
        boolean check = false;
        try {
            if (this.wishList == null) {
                this.wishList = new HashMap<>();
            }
            wishList.put(mobile.getMobileId(), mobile);
            
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
    
    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.wishList != null) {
                if (this.wishList.containsKey(id)) {
                    Product mobile = this.wishList.get(id);
                    this.wishList.remove(id, mobile);
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
}
