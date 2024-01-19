package com.abdin.fa0282;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.abdin.fa0282.models.Checkout;
import com.abdin.fa0282.models.RentalAgreement;

@SpringBootTest
public class ProcessComponentTests {


    ProcessComponent processComponent = new ProcessComponent();


    // Charge Holiday and Charge weekend

    @Test
    void processWholeWeekNoHolidays() throws Exception{
        Checkout checkout = Checkout.getInstance("08/01/23", 10, "LADW", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 10);
    }

    // No Charge Holiday 
        //Labor Day 


        @Test
        void processNoChargeLaborDay() throws Exception{
            Checkout checkout = Checkout.getInstance("09/01/24", 10, "LADW", 40);
            RentalAgreement agreement= processComponent.process(checkout);
            assertEquals(agreement.getChargeDays(), 9);
        }

        //Independence Day
        //Independence Day Holiday out of range 

        @Test
        void processNoChargeIndependenceDayInWeekday() throws Exception{
            Checkout checkout = Checkout.getInstance("07/01/24", 10, "LADW", 40);
            RentalAgreement agreement= processComponent.process(checkout);
            assertEquals(agreement.getChargeDays(), 9);
        }
    
        @Test
        void processNoChargeIndependenceDayInWeekend() throws Exception{
            Checkout checkout = Checkout.getInstance("07/01/20", 10, "LADW", 40);
            RentalAgreement agreement= processComponent.process(checkout);
            assertEquals(agreement.getChargeDays(), 9);
        }
    
        @Test
        void processNoChargeIndependenceDayInWeekendOutOfRange() throws Exception{
            Checkout checkout = Checkout.getInstance("07/04/20", 10, "LADW", 40);
            RentalAgreement agreement= processComponent.process(checkout);
            assertEquals(agreement.getChargeDays(), 10);
        }
    

    // No Charge Weekend     
    @Test
    void processNoChargeWeekendNoHolidays() throws Exception{

        Checkout checkout = Checkout.getInstance("01/14/24", 10, "CHNS", 40);
        RentalAgreement agreement= processComponent.process(checkout);
        assertEquals(agreement.getChargeDays(), 7);

    }

    //Invalid Number of days

    @Test
    void processInvalidNumberOfDays() throws Exception {
        Checkout checkout = Checkout.getInstance("08/01/23", -20, "LADW", 40);

        try{
            processComponent.process(checkout);
        }
        catch(Exception e){

            assertEquals(e.getMessage(), "Rental Day Count must be greater than 1");

        }
    }


    //Renting 0 days would not allow it to continue. 

    @Test
    void processZeroDaysRental() throws Exception {
        Checkout checkout = Checkout.getInstance("08/01/23", 0, "LADW", 40);

        try{
            processComponent.process(checkout);
        }
        catch(Exception e){

            assertEquals(e.getMessage(), "Rental Day Count must be greater than 1");

        }
        
    }

    //Invalid Discount 
    @Test
    void processInvalidDiscount() throws Exception {
        Checkout checkout = Checkout.getInstance("08/01/23", 0, "LADW", 3000);

        try{
            processComponent.process(checkout);
        }
        catch(Exception e){

            assertEquals(e.getMessage(), "Discount must be between 0-100");

        }
    }        

    // Discount 
    // No Discount 



}
