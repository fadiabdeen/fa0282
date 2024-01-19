package com.abdin.fa0282.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class CalendarUtilTest {

    // Test Calculations
        //Discount
        //Final after discount 


        CalendarUtil util = new CalendarUtil();

    @Test
    void testCalcChargeAmount() {

        Double result = util.calcChargeAmount(10, 10.00);
        assertEquals(result, 100.00);

    }

    @Test
    void testCalcDiscountAmount() {

        Double result = util.calcDiscountAmount(50, 100.00);
        assertEquals(result, 50.00);

    }

    @Test
    void testCalcDueDate() {

        Date dueDate = util.calcDueDate("01/01/2024", 10);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dueDate);
        assertEquals(11, cal.get(Calendar.DAY_OF_MONTH));

    }

    @Test
    void testCalcFinalChargeAmount() {

        Double finalCharge = util.calcFinalChargeAmount(100.00, 20.00);
        assertEquals(80, finalCharge);

    }

    @Test
    void testRound() {

        double rounded = CalendarUtil.round(10.888, 2);
        assertEquals(rounded, 10,89);

    }
}
