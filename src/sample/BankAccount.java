package sample;

/**
 * Created by Adebiyi Oluwatobi on 8/6/2015.
 */
public class BankAccount {
    private double balance = 1000;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public double getBalance(){
        return balance;
    }
}
