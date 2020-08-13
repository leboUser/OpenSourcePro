package BankOP;

public class Customer {
    private String id;
    private String amount;
    private String deposited;
    private String withdrawn;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getAmount() { return amount; }
    public void setAmount(String value) { this.amount = value; }

    public String getDeposited() { return deposited; }
    public void setDeposited(String value) { this.deposited = value; }

    public String getWithdrawn() { return withdrawn; }
    public void setWithdrawn(String value) { this.withdrawn = value; }
}
