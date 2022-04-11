package ir.dotin.main.logic.entities;

public class DefaultBankCustomer implements BankCustomer {
    private int customerNumber;

    public DefaultBankCustomer(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

}
