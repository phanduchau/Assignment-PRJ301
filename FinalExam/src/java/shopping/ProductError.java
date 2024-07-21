/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

/**
 *
 * @author Hậu Phan
 */
public class ProductError {
    private String mobileIdError;  
    private String mobileNameError;  
    private String errorError;

    public ProductError() {
        this.mobileIdError="";       
        this.mobileNameError="";  
        this.errorError="";
    }

    public ProductError(String mobileIdError, String mobileNameError, String errorError) {
        this.mobileIdError = mobileIdError;
        this.mobileNameError = mobileNameError;
        this.errorError = errorError;
    }

    public String getMobileIdError() {
        return mobileIdError;
    }

    public void setMobileIdError(String mobileIdError) {
        this.mobileIdError = mobileIdError;
    }

    public String getMobileNameError() {
        return mobileNameError;
    }

    public void setMobileNameError(String mobileNameError) {
        this.mobileNameError = mobileNameError;
    }

    public String getErrorError() {
        return errorError;
    }

    public void setErrorError(String errorError) {
        this.errorError = errorError;
    }

    

    
}
