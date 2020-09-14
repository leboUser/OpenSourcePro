
import Model.BankOP;
import Model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import java.io.*;
public class Task2  {

    @Test
    void Test1() throws Exception {
        BankOP bankOP = new BankOP("chrome",true,"http://www.way2automation.com/angularjs-protractor/banking/#/login",Task2.class.getName(),Task2.class.getMethods()[0].getName());
        //Test Cases steps
        bankOP.customerLogin(1);
        bankOP.depsitAmount("1500");
        //close Logoff
        bankOP.OffLogin();
        //flush report
        bankOP.shutdown();
        bankOP.flush();
    }

    @Test
    void Test2() throws Exception {
        BankOP bankOP = new BankOP("chrome",true,"http://www.way2automation.com/angularjs-protractor/banking/#/login",Task2.class.getName(),Task2.class.getMethods()[0].getName());

       for(int x=1;x<5;x++){
           bankOP.customerLogin(x);
           bankOP.depsitAmount("1500");
           bankOP.OffLogin();
       }
       bankOP.shutdown();
        bankOP.flush();
    }

        @Test
        void Test3()throws Exception  {
            BankOP bankOP = new BankOP("chrome",true,"http://www.way2automation.com/angularjs-protractor/banking/#/login",Task2.class.getName(),Task2.class.getMethods()[0].getName());

            bankOP.customerLogin(1);
            bankOP.depsitAmount("1500");
            bankOP.transactions("1500");
            bankOP.withDraw("1500");
            bankOP.OffLogin();
            bankOP.shutdown();
            bankOP.flush();
        }

    @Test
    void Test4() throws Exception {
        BankOP bankOP = new BankOP("chrome",true,"http://www.way2automation.com/angularjs-protractor/banking/#/login",Task2.class.getName(),Task2.class.getMethods()[0].getName());
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader location = new BufferedReader(
                new FileReader("src/main/resources/customer.json"));
        Customer customerObj = mapper.readValue(location, Customer.class);
        bankOP.customerLogin(Integer.parseInt(customerObj.getID()));
        bankOP.depsitAmount(customerObj.getDeposited());
        bankOP.transactions(customerObj.getDeposited());

        bankOP.withDraw(customerObj.getWithdrawn());
        bankOP.OffLogin();
        bankOP.shutdown();
        bankOP.flush();

    }



}
