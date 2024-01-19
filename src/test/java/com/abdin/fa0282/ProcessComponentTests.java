package com.abdin.fa0282;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.abdin.fa0282.models.Checkout;
import com.abdin.fa0282.models.RentalAgreement;

@SpringBootTest
public class ProcessComponentTests {

    ProcessComponent processComponent = new ProcessComponent();

    //Renting 0 days would not allow it to continue. 
    // Charge Holiday and Charge weekend

    // No Charge Holiday 
        //Labor Day 
        //Independence Day
        //Independence Day Holiday out of range 

    // No Charge Weekend 

    // No Charge Holiday and No Charge Weekend 

    //Invalid Number of days
    //Invalid Discount 
    // Discount 
    // No Discount 

    // Test Calculations
        //Discount
        //Final after discount 






    
    @Test
    void processNoChargeWeekendNoHolidays(){

        Checkout checkout = Checkout.getInstance("01/14/24", 10, "CHNS", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 7);

    }

    @Test
    void processWholeWeekNoHolidays(){
        Checkout checkout = Checkout.getInstance("09/01/23", 10, "LADW", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 10);
    }


    
    @Test
    @Disabled
    void processNoChargeIndependenceDayInWeekday(){
        Checkout checkout = Checkout.getInstance("07/01/24", 10, "LADW", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 9);
    }

    @Test
    @Disabled
    void processNoChargeIndependenceDayInWeekend(){
        Checkout checkout = Checkout.getInstance("07/01/20", 10, "LADW", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 9);
    }

    @Test
    @Disabled
    void processNoChargeIndependenceDayInWeekendOutOfRange(){
        Checkout checkout = Checkout.getInstance("07/04/20", 10, "LADW", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 10);
    }

    

}
