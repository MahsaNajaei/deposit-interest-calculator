package ir.dotin.main.logic.entities;

import java.math.BigDecimal;

public class Qarz extends BankDeposit {
    public static double interestRate;

    public Qarz(BigDecimal depositBalance) {
        super(depositBalance);
    }

    @Override
    public String getType() {
        return "Qarz";
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

}
