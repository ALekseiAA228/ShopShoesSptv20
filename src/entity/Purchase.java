/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author user
 */
public class Purchase implements Serializable{
    private Shoes shoes;
    private Customer customer;
    private Date givenShoes;

    public Purchase() {
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getGivenShoes() {
        return givenShoes;
    }

    public void setGivenShoes(Date givenShoes) {
        this.givenShoes = givenShoes;
    }
    
            
}