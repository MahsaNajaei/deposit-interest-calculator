package ir.dotin.main.logic.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BankDeposit implements Deposit {

    private BigDecimal depositBalance;
    private double interestRate;



    public BankDeposit(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
        this.interestRate = getInterestRate();
    }

    @Override
    public BigDecimal calculateInterestByDuration(int durationInDays) {
        BigDecimal payedInterest = (depositBalance.multiply(BigDecimal.valueOf(interestRate)).multiply(BigDecimal.valueOf(durationInDays))).divide(BigDecimal.valueOf(36500), RoundingMode.HALF_EVEN);
        return payedInterest;
    }

    @Override
    public BigDecimal getBalance() {
        return new BigDecimal(String.valueOf(depositBalance));
    }
}
