package ir.dotin.main.logic.entities;

public class DefaultBankCustomer implements BankCustomer {
    private int customerNumber;
    private Deposit deposit;

    public DefaultBankCustomer(int customerNumber, Deposit deposit) {
        this.customerNumber = customerNumber;
        this.deposit = deposit;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public Deposit getDeposit() {
        return deposit;
    }

}
