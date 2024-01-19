package com.abdin.fa0282.models;

public class Tool {

    private String code;
    private ToolType type;
    private String brand;

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }
    public String getCode() {
        return code;
    }

    public ToolType getType() {
        return type;
    }

    public static Tool getInstance(String code, ToolType toolType, String brand){
        Tool tool = new Tool();
        tool.code = code;
        tool.type = toolType;
        tool.brand = brand;

        return tool;
    }
    
}
