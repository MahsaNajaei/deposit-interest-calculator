package ir.dotin.main.logic;

import ir.dotin.main.logic.entities.BankCustomer;
import ir.dotin.main.logic.entities.CustomerDeposit;
import ir.dotin.main.logic.entities.DefaultBankCustomer;
import ir.dotin.main.logic.entities.Deposit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class BankObjectsReflector {
    public CustomerDeposit reflectCustomerDeposit(InputDataObjectHandler.InputDataObject inputObject) {

        CustomerDeposit customerDeposit = null;
        try {
            Deposit deposit = reflectDeposit(inputObject.getDepositType(),inputObject.getDepositBalance());
            BankCustomer bankCustomer = reflectCustomer(inputObject.getCustomerNumber());
            customerDeposit = CustomerDeposit.class.getConstructor(BankCustomer.class, Deposit.class).newInstance(bankCustomer, deposit);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return customerDeposit;
    }

    private Deposit reflectDeposit(String depositType, BigDecimal depositBalance) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Deposit deposit = null;
        try {
            Class depositClass = Class.forName("ir.dotin.main.logic.entities." + depositType);
            deposit = (Deposit) depositClass.getConstructor(BigDecimal.class).newInstance(depositBalance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deposit;
    }

    private BankCustomer reflectCustomer(int customerNumber) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class customerClass = DefaultBankCustomer.class;
        BankCustomer customer = (BankCustomer) customerClass.getConstructor(int.class).newInstance(customerNumber);
        return customer;
    }
}
