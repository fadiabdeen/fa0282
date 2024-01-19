package com.abdin.fa0282.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.abdin.fa0282.models.Tool;

@Service
public class CalendarUtil {

    public Date getDateFromString(String stringDate){

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        Date date = formatter.parse(stringDate, new ParsePosition(0));

        return date;
    }

    public void printDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        System.out.println(formatter.format(date,new StringBuffer(""),new FieldPosition(0)));

    }

    public static String formatDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        return formatter.format(date,new StringBuffer(""),new FieldPosition(0)).toString();

    }

    /* Checkout Date + number of days */
    public Date calcDueDate(String checkoutDateString, Integer numberOfDays){

        Date checkoutDate = getDateFromString(checkoutDateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkoutDate);
        cal.add(Calendar.DATE, numberOfDays);
        return cal.getTime();
    }

    private boolean isWeekend(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return 
        cal.get(Calendar.DAY_OF_WEEK)==1 || 
        cal.get(Calendar.DAY_OF_WEEK)==7;

    }


    private boolean isLaborDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // September && First Week && Monday
        return( 
        (cal.get(Calendar.MONTH)==8) && 
        (cal.get(Calendar.DAY_OF_WEEK_IN_MONTH)==1) && 
        (cal.get(Calendar.DAY_OF_WEEK)==2));
       
    }



    /*
     * Independence Day, July 4th - If falls on weekend, it is observed on the closest weekday 
     * (if Sat,then Friday before, if Sunday, then Monday after)
     */
    private boolean isIndependenceDayHoliday(Date current, Date checkoutDate, Date dueDate){

        //Month July 
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);

        // July && 4th
        if( 
        (cal.get(Calendar.MONTH)==6) && 
        (cal.get(Calendar.DAY_OF_MONTH)==4)){

            //If it falls on weekend 
            if(cal.get(Calendar.DAY_OF_WEEK)==1){
                cal.add(Calendar.DATE, 1);
            }else 
            if(cal.get(Calendar.DAY_OF_WEEK)==7){
                cal.add(Calendar.DATE, -1);
            }

            //Check within range (checkout date - due date)
            if(cal.getTime().after(dueDate) || cal.getTime().before(checkoutDate)) return false;
            else return true;

        }

        return false;

    }

    public Integer calcChargeDays(Tool tool, Date checkoutDate, Integer numberOfDays, Date dueDate) {

        if(!tool.getType().getWeekend()){
            //Subtract Weekends
            numberOfDays = numberOfDays - countWeekendDays(checkoutDate, numberOfDays);
 
        }

        if(!tool.getType().getHoliday()){
            //Subtract 
            numberOfDays = numberOfDays - countHolidayDays(checkoutDate, numberOfDays, dueDate);
        }

        return numberOfDays;

    }

    private Integer countHolidayDays(Date checkoutDate, Integer numberOfDays, Date dueDate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(checkoutDate);

        for(int counter=0; counter<=numberOfDays; counter++)
        {
            //condition to checking checkoutday 
            if(counter>0) cal.add(Calendar.DATE, 1);

            if(isLaborDay(cal.getTime()) || isIndependenceDayHoliday(cal.getTime(), checkoutDate, dueDate)){
                return 1;
            }

        }
        
        return 0;
    }

    private Integer countWeekendDays(Date checkoutDate, Integer numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkoutDate);

        int weekendDays = 0;
        for(int counter=0; counter<=numberOfDays; counter++)
        {
            //condition to checking checkoutday 
            if(counter>0) cal.add(Calendar.DATE, 1);

            if(isWeekend(cal.getTime()))
                weekendDays ++;
        }
        return weekendDays;
    }

    public Double calcChargeAmount(Integer chargeDays, Double rentalCharge) {

        //Need to round up
        return round((chargeDays * rentalCharge),2);
    }

    public Double calcDiscountAmount(Integer discountPercentage, Double chargeAmount) {

        return round((discountPercentage *0.01) * chargeAmount,2);

    }

    public Double calcFinalChargeAmount(Double chargeAmount, Double discountAmount){
        return round(chargeAmount - discountAmount,2);
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
