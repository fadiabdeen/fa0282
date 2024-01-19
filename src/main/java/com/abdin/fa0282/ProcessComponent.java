package com.abdin.fa0282;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abdin.fa0282.models.Checkout;
import com.abdin.fa0282.models.RentalAgreement;
import com.abdin.fa0282.models.Tool;

import com.abdin.fa0282.repositories.ToolsDao;
import com.abdin.fa0282.services.CalendarUtil;

@Component
public class ProcessComponent {

    @Autowired
    CalendarUtil holidayUtil = new CalendarUtil();

    ToolsDao dao = new ToolsDao();

    public RentalAgreement process(Checkout checkout) throws Exception{

        try{

            //Validate Checkout
            checkout.validate();

            //Initiate Rental Agreement
            RentalAgreement agreement = RentalAgreement.getInstance(checkout);
            agreement.setCheckoutDate(holidayUtil.getDateFromString(checkout.getCheckoutDate()));

            //Load Tool and Rates
            Tool tool = dao.getTool(checkout.getToolCode());
            agreement.setTool(tool);

            //Daily Rental Charge 
            agreement.setRentalCharge(tool.getType().getDaily());
            
            //Calculate Due Date
            agreement.setDueDate(holidayUtil.calcDueDate(checkout.getCheckoutDate(), checkout.getNumberOfDays()));

            //Calculate Charge Days
            agreement.setChargeDays(holidayUtil.calcChargeDays(tool, agreement.getCheckoutDate(), checkout.getNumberOfDays(), agreement.getDueDate()));
            
            //Calculate Charge Amount
            agreement.setChargeAmount(holidayUtil.calcChargeAmount(agreement.getChargeDays(), agreement.getRentalCharge()));

            //Discount Amount 
            agreement.setDiscountAmount(holidayUtil.calcDiscountAmount(agreement.getDiscountPercentage(), agreement.getChargeAmount()));

            //Final Charge 
            agreement.setFinalCharge(holidayUtil.calcFinalChargeAmount(agreement.getChargeAmount(), agreement.getDiscountAmount()));

            //Formated print in console
            System.out.println(agreement);

            return agreement;

        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

}
