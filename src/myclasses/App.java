/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Customer;
import entity.Income;
import entity.Purchase;
import entity.Shoes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import tools.SaverToFiles;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Shoes> shoeses = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();
    private SaverToFiles saverToFiles = new SaverToFiles();
    Income income = new Income();
    
    public App(){
        shoeses = saverToFiles.loadShoeses();
        customers = saverToFiles.loadCustomers();
        purchases = saverToFiles.loadPurchases();
        incomes = saverToFiles.loadIncomes();

    }
    
    public void run(){
       String repeat = "yes";
       do{
           System.out.println("Выберите номер задачи: ");
           System.out.println("0: Закрыть программу");
           System.out.println("1: Добавить Обувь");
           System.out.println("2: Список Обуви");
           System.out.println("3: Добавить покупателя");
           System.out.println("4: Список Покупателей");
           System.out.println("5: купить обувь");
           System.out.println("6: Доход магазина за все время работы");
           System.out.println("7: Добавить денег покупателю");
           int task = scanner.nextInt(); scanner.nextLine();
           switch (task) {
               case 0:
                   repeat = "no";
                   break;
               case 1:
                   addShoes();
                   break;
               case 2:
                   printListShoeses();
                   break;
               case 3:
                   addCustomer();
                   break;
               case 4:
                   printListCustomers();
                   break;
               case 5:
                   givenShoes();
                   break;
               case 6:
                   incomes();
                   break;
               case 7:
                   addMoney();
                   break;
               default:
                   System.out.println("Выберите номер из списка!");
           }
           
       }while("yes".equals(repeat));
       System.out.println("Пока! :)");
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
        saverToFiles.saveCustomers(customers);
    }
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
       saverToFiles.saveShoeses(shoeses);
    }

    private void printListShoeses() {
        System.out.println("Список обуви: ");
        for (int i = 0; i < shoeses.size(); i++) {
            if(shoeses.get(i) != null 
                    && shoeses.get(i).getCount() > 0
                    && shoeses.get(i).getCount() < shoeses.get(i).getQuantity() + 1){
                System.out.printf("%d %s цена: %s колличество: %d %n"
                       ,i+1
                       ,shoeses.get(i).getShoesName()
                       ,shoeses.get(i).getPrice()
                       ,shoeses.get(i).getCount()
               );
           }

       }
    }

    private void printListCustomers(){
    System.out.println("*СПИСОК ПОКУПАТЕЛЕЙ*");
    int n=0;
    for (int i = 0; i < customers.size(); i++) {
        if(customers.get(i)!=null){
            System.out.printf("%d. %s %s, номер телефона: %s, доступные деньги: %s евро%n",
            i+1,
            customers.get(i).getFirstname(),
            customers.get(i).getLastname(),
            customers.get(i).getPhone(),
            customers.get(i).getMoney()
            );
            n++;
        }
    } 
    if(n<1){
        System.out.println("Покупателей нет!");
    }
}
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
        income.setGeneralMoney(income.getGeneralMoney()+purchase.getShoes().getPrice());
        purchase.getShoes().setCount(purchase.getShoes().getCount() - 1);
        purchases.add(purchase);
        incomes.add(income);
        saverToFiles.saveShoeses(shoeses);
        saverToFiles.saveCustomers(customers);
        saverToFiles.saveIncomes(incomes);
        saverToFiles.savePurchases(purchases);
    }else{
        System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег!");
    }
}

    private void addMoney() {
    System.out.println("*ДОБАВИТЬ ДЕНЬГИ ПОКУПАТЕЛЮ*");
    printListCustomers();
    System.out.print("Выберите нужного покупателя: ");
    int choice= scanner.nextInt(); scanner.nextLine();
    System.out.print("Введите сколько денег вы хотите добавить этому покупателю: ");
    int add= scanner.nextInt(); scanner.nextLine();
    customers.get(choice-1).setMoney(customers.get(choice-1).getMoney()+add);
    saverToFiles.saveCustomers(customers);
    }
    private void incomes(){
    System.out.println("*ДОХОД МАГАЗИНА*");
    int sum=0;
        for (int i = 0; i < incomes.size(); i++) {
            sum +=incomes.get(i).getGeneralMoney();
            
        }
    System.out.printf("Выручка магазина составляет: %s Евро ",sum);
}

}

