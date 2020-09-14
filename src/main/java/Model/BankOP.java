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


     public BankOP(String browser, boolean windowsize,String url,String testSuite,String testcase) throws Exception {
         super(browser,windowsize,url,testSuite,testcase);

     }

    public void customerLogin(int user){
        clickElement(By.xpath(customerlogin),true,"Able to login page");
        selectElementByIndex(By.xpath(yourNamedropDown),user,true,"Able to select user");
        clickElement(By.xpath(loginCustomer),true,"Able to login customer page");
        validateElementText(By.xpath(welcomeText),"Welcome",true,"Able to validate text");
    }

    public  void OffLogin( ){

        clickElement(By.xpath(Offlogin),true,"Able to log Off?");
        clickElement(By.xpath(home),true,"Able to reach to home page");
    }

    public void depsitAmount(String amount){
        clickElement(By.xpath(despsit),true,"Able to click button deposit");
        enterText(By.xpath(enterAmount),amount,true,"Able to enter amount for desposit"+amount);
        clickElement(By.xpath(buttonDeposit),true,"Able to submit the amount");
        validateElementText(By.xpath(textDepositSuccessful),"Deposit Successful",true,"Able to receive Successfull notification");
    }

    public  void transactions(String amount){
        clickElement(By.xpath(Transaction),true,"Able to click Transaction button");
        clickElement(By.xpath(dateTimElement),true,"able to click date time");
        GetlistElement(By.xpath(TransactionTable),amount,true,"Able to search transaction amount");
        clickElement(By.xpath(back),true,"Able to click back button");
        validateElementText(By.xpath(welcomeText),"Welcome",true,"Able to go back to home page");
    }


    public  void withDraw(String amount){
        clickElement(By.xpath(withdraw),true,"Able to click withdraw button");
        enterText(By.xpath(enterAmount),amount,true,"Able to enter withdraw amount");
        clickElement(By.xpath(buttonWithdraw),true, "Able to submit the amount "+amount);
        validateElementText( By.xpath(textTransactionSuccessful),"Transaction successful",true,"Able to receive successful notification");

    }


}
