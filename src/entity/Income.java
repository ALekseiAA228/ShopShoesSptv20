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
public class Income implements Serializable {
       private int generalMoney;

    public int getGeneralMoney() {
        return generalMoney;
    }

    public void setGeneralMoney(int generalMoney) {
        this.generalMoney = generalMoney;
    }
    
}
