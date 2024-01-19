package com.abdin.fa0282.models;

public class Checkout {
    private String checkoutDate;
    private Integer numberOfDays;
    private String toolCode;
    private Integer discount;


    public String getCheckoutDate() {
        return checkoutDate;
    }

    public Integer getDiscount() {
        return discount;
    }
    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public String getToolCode() {
        return toolCode;
    }

    public static Checkout getInstance(String checkoutDate, Integer numberOfDays, String toolCode, Integer discount){
        
        Checkout checkout = new Checkout();
        checkout.checkoutDate = checkoutDate;
        checkout.numberOfDays = numberOfDays;
        checkout.toolCode = toolCode;
        checkout.discount = discount;

        return checkout;
    }

    public void validate() throws Exception    
    {
        if(this.discount>100 || this.discount<0) 
            throw new Exception("Discount must be between 0-100"); 

        if(this.numberOfDays<1)
            throw new Exception("Rental Day Count must be greater than 1");
    }
}
