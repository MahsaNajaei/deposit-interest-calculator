package ir.dotin.main.logic.entities;

public class CustomerDeposit{
    private BankCustomer bankCustomer;
    private Deposit bankDeposit;

    public CustomerDeposit(BankCustomer bankCustomer, Deposit deposit) {
        this.bankCustomer = bankCustomer;
        this.bankDeposit = deposit;
    }

    public BankCustomer getBankCustomer() {
        return bankCustomer;
    }

    public Deposit getBankDeposit() {
        return bankDeposit;
    }
}
