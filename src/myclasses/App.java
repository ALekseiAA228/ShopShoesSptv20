/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Customer;
import entity.Purchase;
import entity.Shoes;
import interfaces.Keepeing;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Shoes> shoeses = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
    private Keepeing keeper = new SaverToBase();
    private SaverToBase saverToBase = new SaverToBase();
    
    public App(){
        shoeses = keeper.loadShoeses();
        customers = keeper.loadCustomers();
        purchases = keeper.loadPurchases();

    }
    
    public void run(){
       String repeat = "yes";
       do{
           System.out.println("Выберите номер задачи: ");
           System.out.println("1: Закрыть программу");
           System.out.println("2: Добавить Обувь");
           System.out.println("3: Список Обуви");
           System.out.println("4: Добавить покупателя");
           System.out.println("5: Список Покупателей");
           System.out.println("6: купить обувь");
           System.out.println("7: Доход магазина за все время работы");
           System.out.println("8: изменить обувь");
           System.out.println("9: изменить покупателя");
           int task = getNumber();
            switch (task) {
                case 1:
                    repeat="q";
                    System.out.println("Пока! :)");
                    break;
                case 2:
                    addShoes();
                    break;
                case 3:
                    printListShoeses();
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    printListCustomers();
                    break;
                case 6:
                    givenShoes();
                    break;
                case 7:
                    printListPurchase();
                    break;
                case 8:
                    updateShoes();
                    break;
                case 9:
                    updateCustomer();
                    break;
                default:
                    System.out.println("Введите номер из списка!");;
            }
            
        }while("r".equals(repeat));
    }
    private void addCustomer() {
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите телефон покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.println("Введите деньги покупателя");
        customer.setMoney(scanner.nextInt());scanner.nextLine();
        System.out.println("покупатель инициирован: "+customer.toString());
        customers.add(customer);
        keeper.saveCustomers(customers);
    }
    /*
    ------------------------------------------------------------------------------------------------
*/
    private void addShoes() {
       Shoes shoes = new Shoes();
       System.out.print("Введите название обуви: ");
       shoes.setShoesName(scanner.nextLine());
       System.out.print("Введите количество экземпляров обуви: ");
       shoes.setQuantity(scanner.nextInt());scanner.nextLine();
       shoes.setCount(shoes.getQuantity());
       System.out.println("Введите цену обуви");
       shoes.setPrice(scanner.nextInt());scanner.nextLine();
       System.out.println("обувь инициирована: "+shoes.toString());    
       shoeses.add(shoes);
       keeper.saveShoeses(shoeses);
    }
   /*
    ------------------------------------------------------------------------------------------------
*/
    private Set<Integer> printListShoeses() {
        Set<Integer> setNumbersShoeses = new HashSet<>();
        System.out.println("Список обуви: ");
        for (int i = 0; i < shoeses.size(); i++) {
            if(shoeses.get(i) != null 
                    && shoeses.get(i).getCount() > 0
                    && shoeses.get(i).getCount() < shoeses.get(i).getQuantity() + 1){
                System.out.printf("%s. %s. %s.  В наличии: %s%n"
                       ,i+1
                       ,shoeses.get(i).getShoesName()
                       ,shoeses.get(i).getPrice()
                       ,shoeses.get(i).getCount()
                );
                setNumbersShoeses.add(i+1);
           }

       }
        return setNumbersShoeses;
    }
   /*
    ------------------------------------------------------------------------------------------------
*/
    private Set<Integer> printListCustomers(){
        System.out.println("----- Список покупателей -----");
        Set<Integer> setNumbersCustomers = new HashSet<>();
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s деньги: %s %n"
                       ,i+1
                       ,customers.get(i).getFirstname()
                       ,customers.get(i).getLastname()
                       ,customers.get(i).getPhone()
                       ,customers.get(i).getMoney()
                );
                setNumbersCustomers.add(i+1);
            }
        }
        if(setNumbersCustomers.isEmpty()){
            System.out.println("Список покупателей пуст.");
        }
        return setNumbersCustomers;
    }

       /*
    ------------------------------------------------------------------------------------------------
*/
private void givenShoes() {
    System.out.println("*ПОКУПКА ОБУВИ*"); 
    System.out.println("-----------------------------");
    printListShoeses();
    System.out.print("Выберите нужную модель обуви:");
    int shoesNum= scanner.nextInt(); scanner.nextLine();
    System.out.println("-----------------------------");
    printListCustomers();
    System.out.print("Выберите нужного покупателя: ");
    int customerNum= scanner.nextInt(); scanner.nextLine();
    Purchase purchase= new Purchase();
    purchase.setShoes(shoeses.get(shoesNum-1));
    purchase.setCustomer(customers.get(customerNum-1));
    Calendar c = new GregorianCalendar();
    purchase.setGivenShoes(c.getTime());
    if(purchase.getCustomer().getMoney()>=purchase.getShoes().getPrice()){
        System.out.println("-----------------------------");
        System.out.printf("Кроссовки %s купил %s %s за %s евро %s%n",
        purchase.getShoes().getShoesName(),
        purchase.getCustomer().getFirstname(),
        purchase.getCustomer().getLastname(),
        purchase.getShoes().getPrice(),
        purchase.getGivenShoes()
        );
        purchase.getCustomer().setMoney(purchase.getCustomer().getMoney()-purchase.getShoes().getPrice());
        purchase.getShoes().setCount(purchase.getShoes().getCount() - 1);
        purchases.add(purchase);
        keeper.saveShoeses(shoeses);
        keeper.saveCustomers(customers);
        keeper.savePurchases(purchases);
    }else{
        System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег!");
    }
}
   /*
    ------------------------------------------------------------------------------------------------
*/


    private void updateShoes() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersShoeses = printListShoeses();
        if(setNumbersShoeses.isEmpty()){
            System.out.println("В базе нет обуви");
            return;
        }
        System.out.println("Выберите номер обуви: ");
        int numberShoes = insertNumber(setNumbersShoeses);
        System.out.println("Название обуви: "+shoeses.get(numberShoes - 1).getShoesName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое название обуви: ");
            shoeses.get(numberShoes - 1).setShoesName(scanner.nextLine());
        }
        System.out.println("цена обуви: "+shoeses.get(numberShoes - 1).getPrice());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новую цену обуви: ");
            shoeses.get(numberShoes - 1).setPrice(getNumber());
        }
        System.out.println("Количество экземпляров обуви: "+shoeses.get(numberShoes - 1).getQuantity());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое количество экземпляров обуви: ");
            int newQuantity = getNumber();
            int oldQuantity = shoeses.get(numberShoes - 1).getQuantity();
            int oldCount = shoeses.get(numberShoes - 1).getCount();
            int newCount = oldCount + (newQuantity - oldQuantity);
            shoeses.get(numberShoes - 1).setQuantity(newQuantity);
            shoeses.get(numberShoes - 1).setCount(newCount);
        }

        keeper.saveShoeses(shoeses);
    }

    private int getNumber() {
                do{
            try {
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }
    
   /*
    ------------------------------------------------------------------------------------------------
*/
    private void updateCustomer() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()){
            return;
        }
        System.out.println("Выберите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        System.out.println("Имя покупателя: "+customers.get(numberCustomer - 1).getFirstname());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое имя покупателя: ");
            customers.get(numberCustomer - 1).setFirstname(scanner.nextLine());
        }
        System.out.println("Фамилия покупателя: "+customers.get(numberCustomer - 1).getLastname());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новую фамилию покупателя: ");
            customers.get(numberCustomer - 1).setLastname(scanner.nextLine());
        }
        System.out.println("Телефон покупателя: "+customers.get(numberCustomer - 1).getPhone());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новый телефон читателя: ");
            customers.get(numberCustomer - 1).setPhone(scanner.nextLine());
        }
        System.out.println("Деньги покупателя: "+customers.get(numberCustomer - 1).getMoney());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите деньги покупателя: ");
            customers.get(numberCustomer - 1).setMoney(scanner.nextInt());
    }
    }
    private int insertNumber(Set<Integer> setNumbers) {
        int number=0;
        do{
           number = getNumber();
           if(setNumbers.contains(number)){
               break;
           }
           System.out.println("Попробуй еще раз: ");
       }while(true);
       return number; 
    }

        private Set<Integer> printListPurchase(){
        System.out.println("Введите цифру месяца");
        int num = scanner.nextInt();
        int num2= num-1;
        int sum2=0;
        System.out.println("Введите год ");
        int year= scanner.nextInt();
        int year2 = purchases.get(1).getGivenDate().getYear()-100+2000;
        System.out.println("----- Список покупок -----");
        Set<Integer> setNumbersPurchases = new HashSet<>();
        for (int i = 0; i < purchases.size(); i++) {
            if (year2==year){
            if(purchases.get(i).getGivenDate().getMonth()==num2 ){
                System.out.printf("%s.номер месяца:%s,%s год. %s euro%n"
                       ,i+1
                       ,purchases.get(i).getGivenDate().getMonth()+1
                       ,purchases.get(i).getGivenDate().getYear()-100+2000
                       ,purchases.get(i).getShoes().getPrice()
                       ,sum2+=purchases.get(i).getShoes().getPrice()
                );
                setNumbersPurchases.add(i+1);
            }
        }
        }
        if(setNumbersPurchases.isEmpty()){
            System.out.println("Список покупок"
                    + " пуст.");
        }
        System.out.print("доход за месяц:"+sum2);
        return setNumbersPurchases;
    }
}
        
  
   /*
    ------------------------------------------------------------------------------------------------
*/

