package com.abdin.fa0282.models;

public class ToolType {

    String type;
    Double daily;
    Boolean weekday;
    Boolean weekend;
    Boolean holiday;

    public String getType() {
        return type;
    }

    public Double getDaily() {
        return daily;
    }

    public Boolean getWeekday() {
        return weekday;
    }

    public Boolean getHoliday() {
        return holiday;
    }

    public Boolean getWeekend() {
        return weekend;
    }


    public static ToolType getInstance(String type, Double daily, boolean weekday, boolean weekend, boolean holiday){
        ToolType toolType = new ToolType();
        toolType.type = type;
        toolType.daily = daily;
        toolType.holiday = holiday;
        toolType.weekday = weekday;
        toolType.weekend = weekend;
        return toolType;
    }
    
}
