package com.example.loginregistrationwsql;

import java.io.Serializable;

public class Data implements Serializable {

    private Integer image_id;
    private Integer expense;
    private String info;

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public String getInfo(){
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}