/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Shoes implements Serializable{
    private String shoesName;
    private int count;
    private int price;
    private int quantity;
    

    public String getShoesName() {
        return shoesName;
    }


    public void setShoesName(String shoesName) {
        this.shoesName = shoesName;
    }

    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }    
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Book{" 
                + "shoesName=" + shoesName 
                + ", price=" + price
                + ", count=" + count
                + '}';
    }

    
}