package ir.dotin.main.logic;

import ir.dotin.main.logic.entities.BankCustomer;
import ir.dotin.main.logic.entities.DefaultBankCustomer;
import ir.dotin.main.logic.entities.Deposit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class BankObjectsReflector {
    public Deposit reflectDeposit(String depositType, BigDecimal depositBalance) {
        Deposit deposit = null;
        try {
            Class depositClass = Class.forName("ir.dotin.main.logic.entities." + depositType);
            deposit = (Deposit) depositClass.getConstructor(BigDecimal.class).newInstance(depositBalance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return deposit;
    }

    public BankCustomer reflectCustomer(int customerNumber, Deposit deposit) {
        Class customerClass = DefaultBankCustomer.class;
        BankCustomer customer = null;
        try {
            customer = (BankCustomer) customerClass.getConstructor(int.class, Deposit.class).newInstance(customerNumber, deposit);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
