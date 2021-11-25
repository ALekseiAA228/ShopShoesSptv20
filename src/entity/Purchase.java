/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
public class Purchase implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Customer customer;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Shoes shoes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date givenShoes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date givenDate;


    public Purchase() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Date getGivenShoes() {
        return givenShoes;
    }

    public void setGivenShoes(Date givenDate) {
        this.givenDate = givenDate;
    }
    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    @Override
    public String toString() {
        return "Purchase{" 
                + "reader=" + customer 
                + ", book=" + shoes 
                + ", givenDate=" + givenDate
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}