
package interfaces;


import entity.Customer;
import entity.Purchase;
import entity.Shoes;
import java.util.List;



public interface Keepeing {
    public void saveShoeses(List<Shoes> shoeses);
    public List<Shoes> loadShoeses();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void savePurchases(List<Purchase> purchases);
    public List<Purchase> loadPurchases();
}
