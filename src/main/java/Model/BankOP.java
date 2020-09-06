package Model;

import Controller.Selinum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class BankOP {

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

    public Boolean customerLogin(int user, WebDriver driver){
        Selinum selinum = new Selinum();
        selinum.Webdriver(driver);
        selinum.clickElement(By.xpath(customerlogin));
        selinum.selectElementByIndex(By.xpath(yourNamedropDown),user);
        selinum.clickElement(By.xpath(loginCustomer));
        return selinum.validateElementText(By.xpath(welcomeText),"Welcome");
    }

    public  Boolean OffLogin( WebDriver driver){
        Selinum selinum = new Selinum();
        selinum.Webdriver(driver);
        selinum.clickElement(By.xpath(Offlogin));
        selinum.clickElement(By.xpath(home));
        return (selinum.waitElement(By.xpath(customerlogin)));
    }

    public Boolean depsitAmount(String amount, WebDriver driver){
        Selinum selinum = new Selinum();
        selinum.Webdriver(driver);
        selinum.clickElement(By.xpath(despsit));
        selinum.enterText(By.xpath(enterAmount),amount);
        selinum.clickElement(By.xpath(buttonDeposit));
        return selinum.waitElement(By.xpath(textDepositSuccessful));
    }

    public  Boolean transactions(String amount, WebDriver driver){
        Selinum selinum = new Selinum();
        selinum.Webdriver(driver);
        selinum.clickElement(By.xpath(Transaction));
        selinum.clickElement(By.xpath(dateTimElement));
        selinum.GetlistElement(By.xpath(TransactionTable),amount);
        selinum.clickElement(By.xpath(back));
        return  selinum.waitElement(By.xpath(welcomeText));
    }

/*
    public  SelenideElement withDraw(String amount){
        $(new By.ByXPath(withdraw)).click();
        $(new By.ByXPath(enterAmount)).sendKeys(amount);
        $(new By.ByXPath(buttonWithdraw)).click();
        return $(new By.ByXPath(textTransactionSuccessful)).shouldHave(text("Transaction successful"));

    }*/


}
