package ir.dotin.main.logic.entities;

import java.math.BigDecimal;

public class LongTerm extends BankDeposit {
    public static double interestRate;

    public LongTerm(BigDecimal depositBalance) {
        super(depositBalance);
    }

    @Override
    public String getType() {
        return "LongTerm";
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

}
