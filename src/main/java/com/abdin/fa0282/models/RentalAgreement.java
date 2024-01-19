package com.abdin.fa0282.models;

import java.util.Date;

import com.abdin.fa0282.services.CalendarUtil;

public class RentalAgreement {

    private Tool tool;
    private Integer numberOfDays;
    private Date checkoutDate;
    private Date dueDate;
    private Double rentalCharge;
    private Integer chargeDays;
    private Double chargeAmount;
    private Integer discountPercentage;
    private Double discountAmount;
    private Double finalCharge;


    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setFinalCharge(Double finalCharge) {
        this.finalCharge = finalCharge;
    }

    public void setRentalCharge(Double rentalCharge) {
        this.rentalCharge = rentalCharge;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }    

    public Integer getChargeDays() {
        return chargeDays;
    }

    public Double getRentalCharge() {
        return rentalCharge;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public static RentalAgreement getInstance(Checkout checkout) {

        RentalAgreement agreement = new RentalAgreement();

        agreement.numberOfDays = checkout.getNumberOfDays();
        agreement.discountPercentage = checkout.getDiscount();
        agreement.numberOfDays = checkout.getNumberOfDays();
        
        return agreement;
    }

    @Override
    public String toString() {
        return 
          "----------------------------------------\n"
        + "  RENTAL AGREEMENT  \n" 
        + "----------------------------------------\n" 
        + " Tool code       \t: "+tool.getCode()            + "\n" 
        + " Tool type       \t: "+tool.getType().getType()  + "\n" 
        + " Tool brand      \t: "+tool.getBrand()           + "\n" 
        + " Rental Days     \t: "+numberOfDays              + " Days \n" 
        + " Check out date  \t: "+CalendarUtil.formatDate(checkoutDate)              + "\n" 
        + " Due date        \t: "+CalendarUtil.formatDate(dueDate)                   + "\n" 
        + " Daily rental    \t: $"+rentalCharge             + "\n" 
        + " Charge days     \t: "+chargeDays                + " Days\n" 
        + " Pre-discount    \t: $"+chargeAmount              + "\n"
        + " Discount percent\t: "+discountPercentage        + "%\n"
        + " Discount Amount \t: $"+discountAmount            + "\n"
        + " Final charge    \t: $"+finalCharge               + "\n"
        + "----------------------------------------\n" ;
    }

}
