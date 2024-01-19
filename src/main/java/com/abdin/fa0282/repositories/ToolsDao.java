package com.abdin.fa0282.repositories;

import org.springframework.stereotype.Repository;

import com.abdin.fa0282.models.Tool;
import com.abdin.fa0282.models.ToolType;

@Repository
public class ToolsDao {

    public Tool getTool(String toolCode) throws Exception{

        Tool tool = findTool(toolCode);

        if(tool == null) throw new Exception("Tool not found");

        return tool;
    }


    private Tool findTool(String toolCode) throws Exception{

        //Tool Code, Tool Type, Brand 
        Object[][] data = {
            {"CHNS", "Chainsaw", "Stihl"},
            {"LADW", "Ladder", "Werner"},
            {"JAKD", "Jackhammer", "DeWalt"},
            {"JAKR", "Jackhammer", "Ridgid"}            
        };

        for(Object[] row: data){
            if(toolCode.equals((String)row[0]))
                return Tool.getInstance((String)row[0], findToolType((String)row[1]), ((String)row[2]));
        }

        return null;
    }



    private ToolType findToolType(String toolType){

        //Tool Type, Daily charge, Weekday charge, Weekend charge, Holiday charge
        Object[][] data = {
            {"Ladder", 1.99, true, true, false},
            {"Chainsaw", 1.49, true, false, true}, 
            {"Jackhammer", 2.99, true, false, false}
        };

        for(Object row[]: data){
            if(toolType.equals(((String)row[0])))
                return ToolType.getInstance((String)row[0],(Double)row[1],(Boolean)row[2],(Boolean)row[3],(Boolean)row[4]);
        }
        return null;

    }

}

