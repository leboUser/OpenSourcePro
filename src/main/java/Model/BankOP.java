package Model;

import Controller.Selinum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class BankOP extends Selinum {

     String  customerlogin ="//button[text()='Customer Login']";
     String Offlogin = "//button[text()='Logout']";
     String yourNamedropDown ="//select[@id='userSelect']";
     String loginCustomer = "//button[text()='Login']";
      String despsit ="//button[contains(text(),'Deposit')]";
     String welcomeText ="//strong[contains(text(),'Welcome')]";
     String Transaction ="//button[contains(text(),'Transactions')]";
     String withdraw ="//button[contains(text(),'Withdrawl')]";
     String enterAmount ="//input[@type='number']";
     String buttonDeposit = "//button[text()='Deposit']";
     String buttonWithdraw = "//button[text()='Withdraw']";
     String balance ="//strong[2]";
     String textTransactionSuccessful ="//span[contains(text(),'Transaction successful')]";
     String textDepositSuccessful ="//span[contains(text(),'Deposit Successful')]";
     String dateTimElement ="//a[@href='#']";
     String endDateTransactionTable ="//input[@name='end']";
     String TransactionTable ="//tbody";
     String home="//button[text()='Home']";
     String back ="//button[contains(text(),'Back')]";


     public BankOP(String broswer, boolean windowsize) throws Exception {
         super(broswer,windowsize);
     }

    public Boolean customerLogin(int user){
        clickElement(By.xpath(customerlogin));
        selectElementByIndex(By.xpath(yourNamedropDown),user);
        clickElement(By.xpath(loginCustomer));
        return validateElementText(By.xpath(welcomeText),"Welcome");
    }

    public  Boolean OffLogin( ){

        clickElement(By.xpath(Offlogin));
        clickElement(By.xpath(home));
        return (waitElement(By.xpath(customerlogin)));
    }

    public Boolean depsitAmount(String amount){
        clickElement(By.xpath(despsit));
        enterText(By.xpath(enterAmount),amount);
        clickElement(By.xpath(buttonDeposit));
        return waitElement(By.xpath(textDepositSuccessful));
    }

    public  Boolean transactions(String amount){
        clickElement(By.xpath(Transaction));
        clickElement(By.xpath(dateTimElement));
        GetlistElement(By.xpath(TransactionTable),amount);
        clickElement(By.xpath(back));
        return waitElement(By.xpath(welcomeText));
    }


    public  Boolean withDraw(String amount){
        clickElement(By.xpath(withdraw));
        enterText(By.xpath(enterAmount),amount);
        clickElement(By.xpath(buttonWithdraw));
        return validateElementText( By.xpath(textTransactionSuccessful),"Transaction successful");

    }


}
