/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Customer;
import entity.Purchase;
import entity.Shoes;
import interfaces.Keepeing;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
    public class SaverToBase implements Keepeing{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sptv20ShoesShopPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveShoeses(List<Shoes> shoeses) {
        tx.begin();
            for (int i = 0; i < shoeses.size(); i++) {
                if(shoeses.get(i).getId() == null){
                    em.persist(shoeses.get(i));
                }else{
                    em.merge(shoeses.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Shoes> loadShoeses() {
        List<Shoes> shoeses=null;
        try {
            shoeses = em.createQuery("SELECT shoes FROM Shoes shoes")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return shoeses;
    }
    
    @Override
    public void saveCustomers(List<Customer> customers) {
        tx.begin();
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i).getId() == null){
                    em.persist(customers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Customer> loadCustomers() {
         List<Customer> customers=null;
        try {
            customers = em.createQuery("SELECT customer FROM Customer customer")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return customers;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
        tx.begin();
            for (int i = 0; i < purchases.size(); i++) {
                if(purchases.get(i).getId() == null){
                    em.persist(purchases.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Purchase> loadPurchases() {
         List<Purchase> purchases=null;
        try {
            purchases = em.createQuery("SELECT purchase FROM Purchase purchase")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return purchases;
    }



    
}