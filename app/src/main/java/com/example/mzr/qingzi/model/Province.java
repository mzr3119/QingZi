package com.example.mzr.qingzi.model;

/**
 * Created by Administrator on 2016/2/29.
 */
public class Province {
    private int id;
    private String provinveName;
    private String provinceCode;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getProvinveName(){
        return provinveName;
    }
    public  void  setProvinveName(String provinveName){
        this.provinveName=provinveName;
    }
    public String getProvinceCode(){
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode){
        this.provinceCode=provinceCode;
    }
}
