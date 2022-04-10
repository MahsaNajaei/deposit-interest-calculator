package ir.dotin.main.logic.entities;

import java.math.BigDecimal;

public class ShortTerm extends BankDeposit {
    public static double interestRate;

    public ShortTerm(BigDecimal depositBalance) {
        super(depositBalance);
    }

    @Override
    public String getType() {
        return "ShortTerm";
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}
