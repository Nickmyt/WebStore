package com.example.webstore;

import android.icu.text.CaseMap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.rpc.Help;

import java.io.Serializable;
import java.util.ArrayList;

public class CartModel implements Serializable{
    private String Title , Desc, link;
    private Long price;

    public CartModel(String Title, String Desc, String link,Long price){
        this.Title = Title;
        this.Desc = Desc;
        this.link = link;
        this.price = price;



    }


    public  CartModel(){

    }

    public void setTitle(String Title){
        this.Title = Title;
    }
    public void setDesc(String Desc){
        this.Desc = Desc;
    }
    public void setLink(String link){
        this.link = link;
    }
    public void setPrice(Long price){
        this.price = price;
    }

    public String getTitle(){
        return Title;
    }

    public String getDesc(){
        return Desc;
    }

    public String getLink(){
        return link;
    }

    public Long getPrice(){
        return  price;
    }



}
